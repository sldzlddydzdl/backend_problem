package com.example.backend_problem.service;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.Reply;
import com.example.backend_problem.domain.User;
import com.example.backend_problem.dto.PageRequestDTO;
import com.example.backend_problem.dto.PageResultDTO;
import com.example.backend_problem.dto.ReplyDTO;
import com.example.backend_problem.repository.BoardRepository;
import com.example.backend_problem.repository.ReplyRepository;
import com.example.backend_problem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final UserRepository userRepository;

    private final BoardRepository boardRepository;

    public Reply dtoToEntity(ReplyDTO dto){

        Reply reply = Reply.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .replyer(dto.getReplyer())
                .build();

        return reply;
    }

    public ReplyDTO entityToDto(Reply reply){

        User user = userRepository.findByNickname(reply.getReplyer());

        String role = user.getAccountType();

        if(role.equals("ROLE_REALTOR")){
            role = "(공인 중개사)";
        }else if(role.equals("ROLE_LESSOR")){
            role = "(임대인)";
        }else if(role.equals("ROLE_LESSEE")){
            role = "(임차인)";
        }

        ReplyDTO replyDTO = ReplyDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .replyer(reply.getReplyer()+role)
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return replyDTO;
    }

    public PageResultDTO<ReplyDTO , Reply> getList(PageRequestDTO requestDTO , Long boardId) {

        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());

        Page<Reply> result = replyRepository.findAllByBoard_Id(pageable, boardId);

        Function<Reply, ReplyDTO> fn = (en -> entityToDto(en));

        return new PageResultDTO<>(result, fn);

    }

    @Transactional
    public void save(String replyContent, Long userId, Long boardId) {

        User user = userRepository.findById(userId).get();

        Board board = boardRepository.findById(boardId).get();

        Reply reply = Reply.builder()
                .id(0L)
                .replyer(user.getNickname())
                .board(board)
                .content(replyContent)
                .build();

        replyRepository.save(reply);

    }

    public Long getReplyCount(Long boardId) {

        return replyRepository.countByBoardId(boardId);
    }
}

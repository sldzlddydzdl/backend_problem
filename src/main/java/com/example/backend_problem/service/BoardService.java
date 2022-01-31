package com.example.backend_problem.service;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.User;
import com.example.backend_problem.dto.BoardDTO;
import com.example.backend_problem.dto.PageRequestDTO;
import com.example.backend_problem.dto.PageResultDTO;
import com.example.backend_problem.repository.BoardRepository;
import com.example.backend_problem.repository.LikeRepository;
import com.example.backend_problem.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final LikeRepository likeRepository;

    private final ReplyRepository replyRepository;

    public Board dtoToEntity(BoardDTO dto){

        User user = User.builder()
                .nickname(dto.getWriterId())
                .build();

        Board board = Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .boardRemoved(dto.getBoardRemoved())
                .user(user)
                .build();

        return board;

    }

    public BoardDTO entityToDto(Board board){

        String role = board.getUser().getAccountType();

        if(role.equals("ROLE_REALTOR")){
            role = "(공인 중개사)";
        }else if(role.equals("ROLE_LESSOR")){
            role = "(임대인)";
        }else if(role.equals("ROLE_LESSEE")){
            role = "(임차인)";
        }

        Long likeCount = likeRepository.countByBoardId(board.getId());

        Long replyCount = replyRepository.countByBoardId(board.getId());

        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerId(board.getUser().getAccountId()+role)
                .boardRemoved(board.getBoardRemoved())
                .replyCount(replyCount)
                .likeCount(likeCount)
                .build();

        return boardDTO;

    }

    public PageResultDTO<BoardDTO , Board> getList(PageRequestDTO requestDTO){

        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());

        Page<Board> result = boardRepository.findAllByBoardRemovedIsFalse(pageable);

        Function<Board, BoardDTO> fn = (en -> entityToDto(en));

        return new PageResultDTO<>(result, fn);

    }


    public void save(Board board, User user) {

        Board insertBoard = Board.builder()
                .id(0L)
                .title(board.getTitle())
                .content(board.getContent())
                .boardRemoved(false)
                .user(user)
                .build();

        boardRepository.save(insertBoard);

    }

    public Board findById(Long id) {

        return boardRepository.findById(id).get();

    }

    public void update(Long boardId, String title, String content) {

        Board board = boardRepository.findById(boardId).get();

        board.setContent(content);
        board.setTitle(title);

        boardRepository.save(board);

    }

    public void deleteBoard(Long boardId) {

        Board board = boardRepository.findById(boardId).get();

        board.setBoardRemoved(true);

        boardRepository.save(board);

    }

}

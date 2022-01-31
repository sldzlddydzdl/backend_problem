package com.example.backend_problem.service;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.Like;
import com.example.backend_problem.domain.User;
import com.example.backend_problem.dto.LikeDTO;
import com.example.backend_problem.dto.PageRequestDTO;
import com.example.backend_problem.dto.PageResultDTO;
import com.example.backend_problem.repository.BoardRepository;
import com.example.backend_problem.repository.LikeRepository;
import com.example.backend_problem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    public LikeDTO entityToDto(Like like){

        Like like1 = likeRepository.findByIdAndBoard_Id(like.getId(), like.getBoard().getId());

        User user = userRepository.findById(like1.getUser().getId()).get();

        String role = user.getAccountType();

        if(role.equals("ROLE_REALTOR")){
            role = "(공인 중개사)";
        }else if(role.equals("ROLE_LESSOR")){
            role = "(임대인)";
        }else if(role.equals("ROLE_LESSEE")){
            role = "(임차인)";
        }else if(role.equals("ROLE_USER")){
            role = "";
        }

        LikeDTO likeDTO = LikeDTO.builder()
                .id(like.getId())
                .nickName(user.getNickname()+role)
                .build();

        return likeDTO;

    }

    public Boolean likeCheck(User user, Long boardId) {

        Optional<Like> like = likeRepository.findByUserIdAndBoard_Id(user.getId(), boardId);

        if (like.isPresent()) {
            likeRepository.deleteById(like.get().getId());
            return false;
        }else{
            Board board1 = boardRepository.findById(boardId).get();

            Like like1 = Like.builder()
                    .id(0L)
                    .user(user)
                    .board(board1)
                    .build();

            likeRepository.save(like1);

            return true;
        }
    }

    public List<Like> findByUserId(Long userId) {

        return likeRepository.findAllByUserId(userId);

    }

    public PageResultDTO<LikeDTO, Like> getList(PageRequestDTO pageRequestDTO, Long boardId) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Like> result = likeRepository.findAllByBoard_Id(pageable, boardId);

        Function<Like, LikeDTO> fn = (en -> entityToDto(en));

        return new PageResultDTO<>(result , fn);
    }
}

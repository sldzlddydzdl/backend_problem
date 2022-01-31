package com.example.backend_problem.service;

import com.example.backend_problem.domain.Like;
import com.example.backend_problem.domain.User;
import com.example.backend_problem.dto.PageRequestDTO;
import com.example.backend_problem.dto.PageResultDTO;
import com.example.backend_problem.dto.LikeBoardDTO;
import com.example.backend_problem.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final LikeRepository likeRepository;

    public LikeBoardDTO entityToDto(Like like){

        String role = like.getUser().getAccountType();

        if(role.equals("ROLE_REALTOR")){
            role = "(공인 중개사)";
        }else if(role.equals("ROLE_LESSOR")){
            role = "(임대인)";
        }else if(role.equals("ROLE_LESSEE")){
            role = "(임차인)";
        }

        LikeBoardDTO likeBoardDTO = LikeBoardDTO.builder()
                .id(like.getId())
                .board(like.getBoard())
                .writerId(like.getBoard().getUser().getNickname()+role)
                .build();

        return likeBoardDTO;
    }


    public PageResultDTO<LikeBoardDTO, Like> getList(PageRequestDTO pageRequestDTO, Long userId) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Like> result = likeRepository.findAllByUserId(pageable, userId);

        Function<Like , LikeBoardDTO> fn = (en -> entityToDto(en));

        return new PageResultDTO<>(result, fn);
    }

    public Long getAmountLike(Long userId) {

        return likeRepository.countByUserId(userId);

    }
}

package com.example.backend_problem.service;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.Like;
import com.example.backend_problem.domain.User;
import com.example.backend_problem.repository.BoardRepository;
import com.example.backend_problem.repository.LikeRepository;
import com.example.backend_problem.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikeServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Test
//    @DisplayName("insert user and like paging")
//    public void test_1(){
//
//        IntStream.rangeClosed(1, 100).forEach(i ->{
//
//            String rawPassword = "1234";
//            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//
//            User user = User.builder()
//                    .accountId("test"+i)
//                    .password(encPassword)
//                    .accountType("ROLE_REALTOR")
//                    .nickname("test"+i)
//                    .quit(false)
//                    .build();
//
//            userRepository.save(user);
//
//            Board board = boardRepository.findById(24L).get();
//
//            Like like = Like.builder()
//                    .user(user)
//                    .board(board)
//                    .build();
//
//            likeRepository.save(like);
//
//        });
//
//    }

}



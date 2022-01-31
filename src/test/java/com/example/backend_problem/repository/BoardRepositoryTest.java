package com.example.backend_problem.repository;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

//    @Test
//    @DisplayName("insert Test")
//    public void insertBoard(){
//
//        User user = userRepository.findById(314L).get();
//
//        IntStream.rangeClosed(1, 100).forEach(i ->{
//            Board board = Board.builder()
//                    .title("paging test")
//                    .content("paging test")
//                    .user(user)
//                    .boardRemoved(false)
//                    .build();
//
//            boardRepository.save(board);
//        });
//
//    }

}
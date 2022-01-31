package com.example.backend_problem.service;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.Reply;
import com.example.backend_problem.repository.BoardRepository;
import com.example.backend_problem.repository.ReplyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

//    @Test
//    @DisplayName("댓글 데이터 삽입 테스트")
//    public void insertReply(){
//
//        Board board = boardRepository.findById(5L).get();
//
//        Reply reply = Reply.builder()
//                .id(0L)
//                .content("wow")
//                .replyer("asd")
//                .board(board)
//                .build();
//
//        replyRepository.save(reply);
//    }


}
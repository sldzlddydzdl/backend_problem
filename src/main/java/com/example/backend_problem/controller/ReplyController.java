package com.example.backend_problem.controller;

import com.example.backend_problem.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/insertReply")
    public String insertReply(
            @RequestParam Long userId,
            @RequestParam Long boardId,
            @RequestParam String replyContent
    ){

        replyService.save(replyContent , userId , boardId);

        return "success";

    }

}

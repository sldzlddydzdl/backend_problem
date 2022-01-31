package com.example.backend_problem.controller;

import com.example.backend_problem.dto.PageRequestDTO;
import com.example.backend_problem.service.BoardHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BoardHistoryController {

    private final BoardHistoryService boardHistoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/boardHistory")
    public String showBoardHistory(
            PageRequestDTO pageRequestDTO,
            Model model
    ){

        model.addAttribute("result" , boardHistoryService.getList(pageRequestDTO));

        return "boardHistory";

    }

    @PostMapping("/insertHistory")
    public @ResponseBody String insertHistory(
            @RequestParam Long userId,
            @RequestParam(required = false) Long boardId,
            @RequestParam(required = false) String title,
            @RequestParam String historyKinds
    ){

        boardHistoryService.insertHistory(userId, boardId ,title ,historyKinds);

        return "insertHistory success";
    }

}

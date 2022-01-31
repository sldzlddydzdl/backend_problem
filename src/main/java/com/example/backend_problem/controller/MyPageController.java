package com.example.backend_problem.controller;

import com.example.backend_problem.domain.User;
import com.example.backend_problem.dto.PageRequestDTO;
import com.example.backend_problem.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    private final HttpSession httpSession;

    @GetMapping("/myPage")
    public String goMyPage(
            @RequestParam Long userId,
            Model model,
            PageRequestDTO pageRequestDTO
    ){

        User user = (User)httpSession.getAttribute("user");

        model.addAttribute("result" , myPageService.getList(pageRequestDTO, userId));
        model.addAttribute("userId" , user.getId());
        model.addAttribute("amountLike" , myPageService.getAmountLike(userId));

        return "myPage";
    }

}

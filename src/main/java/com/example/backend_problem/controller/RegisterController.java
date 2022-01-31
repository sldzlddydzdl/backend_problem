package com.example.backend_problem.controller;

import com.example.backend_problem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    
    private final UserService userService;
    
    @PostMapping("/idCheck")
    public Map<String, Boolean> idCheck(
            @RequestParam String userAccountId
    ){
        Boolean result = userService.accountIdCheck(userAccountId);
        Map<String, Boolean> send = new HashMap<>();
        send.put("idCheck" , result);
        
        return send;
    }

    @PostMapping("/nickCheck")
    public Map<String, Boolean> nickCheck(
            @RequestParam String userNickname
    ){
        Boolean result = userService.nicknameCheck(userNickname);
        Map<String, Boolean> send = new HashMap<>();
        send.put("nickCheck" , result);

        return send;
    }
}

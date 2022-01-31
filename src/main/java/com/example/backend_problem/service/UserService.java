package com.example.backend_problem.service;

import com.example.backend_problem.domain.User;
import com.example.backend_problem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void save(User user) {

        userRepository.save(user);
    }

    public void leaveMember(Long userId) {

        User user = userRepository.findById(userId).get();
        user.setQuit(true);

        userRepository.save(user);

    }

    public Boolean accountIdCheck(String userAccountId) {
        
        Optional<User> user = userRepository.findByAccountId(userAccountId);
        if(user.isPresent()){
            return false;
        }
        return true;
    }

    public Boolean nicknameCheck(String userNickname) {

        User user = userRepository.findByNickname(userNickname);
        if (user != null) {
            return false;
        }
        return true;

    }
}

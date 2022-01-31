package com.example.backend_problem.config.auth;

import com.example.backend_problem.domain.User;
import com.example.backend_problem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * // 시큐리티 설정에서 loginProcessingUrl("/login");
 * // login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadByUsername 함수가 실행
 */

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userEntity = userRepository.findByAccountIdAndQuitIsFalse(username);
        if(userEntity.isPresent()){
            httpSession.setAttribute("user" , userEntity.get());
            return new PrincipalDetails(userEntity.get());
        }
        return null;
    }
}

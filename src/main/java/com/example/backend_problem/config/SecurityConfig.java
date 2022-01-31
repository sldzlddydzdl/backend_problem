package com.example.backend_problem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePw(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/** , /js/** , /img/**");
    }

    /**
     *
     * PrincipalDetailsService 에서 loadUserByUsername 의 parameter 는 User 의 로그인 아이디 변수명이 같아야한다.
     *
     * User 의 로그인 아이디 변수명을 username 으로 해주어야하는데 불가피하게 변수명을 맞출수없을때
     *
     * .usernameParameter("쓰고싶은 변수명") 을 해주면된다.
     *
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/board/**").access("hasRole('ROLE_REALTOR') or hasRole('ROLE_LESSOR') or hasRole('ROLE_LESSEE') or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/myPage/**").access("hasRole('ROLE_REALTOR') or hasRole('ROLE_LESSOR') or hasRole('ROLE_LESSEE') or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .usernameParameter("accountId") // PrincipalDetailsService
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/board/list")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}

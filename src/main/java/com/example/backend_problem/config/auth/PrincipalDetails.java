package com.example.backend_problem.config.auth;

import com.example.backend_problem.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * // 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
 * // 로그인 진행이 완료가 되면 security session 을 만들어준다. ( Security ContextHolder)
 * // 오브젝트 타입 => Authentication 타입 객체
 * // Authentication 안에 User 정보가 있어야 됨.
 * // User 오브젝트타입 => UserDetails 타입 객체
 * //
 * // Security Session => Authentication => UserDetails(PrincipalDetails)
 */

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user){
        this.user = user;
    }

    // 해당 user 의 권한을 리턴하는곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return user.getAccountType();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccountId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

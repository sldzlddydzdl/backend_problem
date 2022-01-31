package com.example.backend_problem.repository;

import com.example.backend_problem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String nickname);

    Optional<User> findByAccountIdAndQuitIsFalse(String account_id);

    Optional<User> findByAccountId(String userAccountId);
}

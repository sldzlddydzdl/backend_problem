package com.example.backend_problem.repository;

import com.example.backend_problem.domain.BoardHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardHistoryRepository extends JpaRepository<BoardHistory, Long> {
}

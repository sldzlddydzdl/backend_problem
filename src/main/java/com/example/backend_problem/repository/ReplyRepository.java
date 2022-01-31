package com.example.backend_problem.repository;

import com.example.backend_problem.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Page<Reply> findAllByBoard_Id(Pageable pageable, Long boardId);

    Long countByBoardId(Long boardId);


}

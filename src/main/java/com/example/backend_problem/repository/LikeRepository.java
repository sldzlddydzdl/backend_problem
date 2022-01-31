package com.example.backend_problem.repository;

import com.example.backend_problem.domain.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndBoard_Id(Long userId, Long boardId);

    List<Like> findAllByUserId(Long userId);

    Page<Like> findAllByUserId(Pageable pageable, Long userId);

    Long countByBoardId(Long id);

    Like findByIdAndBoard_Id(Long likeId, Long boardId);

    Page<Like> findAllByBoard_Id(Pageable pageable, Long boardId);

    Long countByUserId(Long id);

}

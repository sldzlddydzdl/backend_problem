package com.example.backend_problem.dto;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardHistoryDTO {

    private Long id;

    private String nickName;

    private String historyContent;

    private Board board;

    private User user;

    private LocalDateTime regDate;

}

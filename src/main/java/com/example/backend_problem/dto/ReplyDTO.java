package com.example.backend_problem.dto;

import com.example.backend_problem.domain.Board;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Long id;

    private String content;

    private String replyer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}

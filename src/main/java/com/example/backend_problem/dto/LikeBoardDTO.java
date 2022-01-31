package com.example.backend_problem.dto;

import com.example.backend_problem.domain.Board;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeBoardDTO {

    private Long id;

    private Board board;

    private String writerId;

}

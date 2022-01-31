package com.example.backend_problem.dto;

import com.example.backend_problem.domain.Board;
import com.example.backend_problem.domain.User;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {

    private Long id;

    private String nickName;
}

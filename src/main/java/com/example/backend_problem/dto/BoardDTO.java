package com.example.backend_problem.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long id;

    private String title;

    private String content;

    private String writerId; // 작성자의 이름

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private Boolean boardRemoved;

    private Long replyCount; // 해당 게시글의 댓글 수

    private Long likeCount; // 해당 게시글의 좋아요 숫자

}

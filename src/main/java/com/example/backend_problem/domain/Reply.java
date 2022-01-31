package com.example.backend_problem.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Board board;

}

package com.example.backend_problem.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString(callSuper = true)
public class BoardHistory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private String historyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

}

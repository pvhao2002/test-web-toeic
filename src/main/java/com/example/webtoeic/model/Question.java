package com.example.webtoeic.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    private Integer number;
    private String text;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer part;
    private String image;
}

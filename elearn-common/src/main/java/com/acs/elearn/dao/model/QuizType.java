package com.acs.elearn.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz_type")
@Data
public class QuizType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "quiz_type_code", nullable = false)
    private String quizTypeCode;

    @OneToMany(mappedBy = "quiz_type")
    private List<Quiz> quizList;

    @Column(name = "quiz_type", nullable = false, length = 64)
    private String quizType;
}

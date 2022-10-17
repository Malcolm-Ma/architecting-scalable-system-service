package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz_type")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "quiz_type_code")
public class QuizType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "quiz_type_code", nullable = false)
    private String quizTypeCode;

    @OneToMany(mappedBy = "quiz_type")
    @JsonIgnore
    private List<Quiz> quizList;

    @Column(name = "quiz_type", nullable = false, length = 64)
    private String quizType;
}

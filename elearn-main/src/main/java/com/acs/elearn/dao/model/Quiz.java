package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz")
@Data
@EntityListeners(AuditingEntityListener.class) // date
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "quiz_id")
public class Quiz {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //generate 32length UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name = "quiz_id", nullable = false, length = 32)
    private String quizId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseInformation course;

    @ManyToOne
    @JoinColumn(name = "quiz_type_code", nullable = false)
    private QuizType quiz_type;

    @Column(name = "quiz_question_resource", nullable = false)
    private String quizQuestionResource;

    @Column(name = "quiz_answer_resource", nullable = false)
    private String quizAnswerResource;

    @CreatedDate
    @Column(name = "quiz_update_time")
    private LocalDateTime quizUpdateTime;

    @CreatedDate
    @Column(name = "quiz_create_time", nullable = false, updatable = false)
    private LocalDateTime quizCreateTime;

}

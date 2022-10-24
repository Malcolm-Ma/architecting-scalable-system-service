package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "course_information")
@Data
@EntityListeners(AuditingEntityListener.class) // date
public class CourseInformation implements Serializable {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //generate 32length UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name = "course_id", nullable = false, length = 32)
    private String courseId;

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    @JsonIgnoreProperties({"course_list"})
    private Commodity commodity;

    @OneToMany(mappedBy = "course")
    @JsonIgnoreProperties({"course"})
    private List<Quiz> quizList;

    @OneToMany(mappedBy = "course")
    @JsonIgnoreProperties({"course"})
    private List<CourseUserProgress> courseProgresses;

    @Column(name = "course_name", length = 64)
    private String courseName;

    @Column(name= "course_resource" , length = 255 )
    private String courseResource;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name= "course_sequence")
    private int courseSequence;

    @CreatedDate
    @Column(name= "course_create_time", nullable = false, updatable = false)
    private LocalDateTime courseCreateTime;

    @LastModifiedDate
    @Column(name = "course_update_time")
    private LocalDateTime courseUpdateTime;

}

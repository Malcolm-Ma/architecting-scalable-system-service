package com.acs.elearn.dao.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_information")
@Data
public class CourseInformation implements Serializable {

    // 之后需要查一下形式strategy = GenerationType.AUTO
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id", nullable = false)
    private String courseId;

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


//    @OneToOne
//    @JoinTable(
//            name = "commodity",
//            joinColumns = @JoinColumn(name = "commodity_id")
//    )
//    private Commodity commodity_id;

}

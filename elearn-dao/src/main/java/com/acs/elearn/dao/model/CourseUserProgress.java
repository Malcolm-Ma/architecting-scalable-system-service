package com.acs.elearn.dao.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_user_progress")
@Data
public class CourseUserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id", nullable = false, length = 32)
    private String courseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "record_is_finished", nullable = false)
    private int recordIsFinished;

    @Column(name = "record_course_display_time_record", nullable = false)
    private int recordCourseDisplayTimeRecord;

    @CreatedDate
    @Column(name = "record_update_time")
    private LocalDateTime recordUpdateTime;

    @CreatedDate
    @Column(name = "record_create_time", nullable = false, updatable = false)
    private LocalDateTime recordCreateTime;
}

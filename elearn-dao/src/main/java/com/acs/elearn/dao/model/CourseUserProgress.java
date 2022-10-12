package com.acs.elearn.dao.model;

import com.acs.elearn.dao.compositekeys.CourseProgressKey;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_user_progress")
@Data
@EntityListeners(AuditingEntityListener.class) // date
public class CourseUserProgress {
    @EmbeddedId
    private CourseProgressKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseInformation course;

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

package com.acs.elearn.dao.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topic_record")
@Data
public class TopicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "topic_id", nullable = false)
    private String topicId;

    @Column(name = "topic_content", length = 64)
    private String topicContent;

    @CreatedDate
    @Column(name = "topic_time", nullable = false, updatable = false)
    private LocalDateTime topicTime;

}

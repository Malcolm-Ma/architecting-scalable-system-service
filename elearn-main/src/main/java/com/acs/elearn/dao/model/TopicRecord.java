package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topic_record")
@Data
@EntityListeners(AuditingEntityListener.class) // date
public class TopicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "topic_id", nullable = false)
    private String topicId;

    @OneToMany(mappedBy = "topic")
    @JsonIgnoreProperties({"topic"})
    private List<ReplyRecord> replyRecordList;

    @Column(name = "topic_content", length = 64)
    private String topicContent;

    @CreatedDate
    @Column(name = "topic_time", nullable = false, updatable = false)
    private LocalDateTime topicTime;

}

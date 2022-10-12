package com.acs.elearn.dao.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reply_record")
@Data
@EntityListeners(AuditingEntityListener.class) // date

public class ReplyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id", nullable = false, length = 32)
    private String replyId;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicRecord topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reply_content", nullable = false, columnDefinition="TEXT")
    private String replyContent;

    @Column(name = "reply_sequence", nullable = false)
    private int replySequence;

    @CreatedDate
    @Column(name = "reply_time", nullable = false, updatable = false)
    private LocalDateTime replyTime;
}

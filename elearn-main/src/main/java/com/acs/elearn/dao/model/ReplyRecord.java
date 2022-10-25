package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
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
    @GenericGenerator(name="idGenerator", strategy="uuid") //generate 32length UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name = "reply_id", nullable = false, length = 32)
    private String replyId;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    @JsonIgnoreProperties({"reply_record_list"})
    private TopicRecord topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({
            "published_commodities",
            "purchased_commodities",
            "user_shopping_cart",
            "user_action_tracing_list",
            "reply_record_list",
            "transaction_list",
            "course_progresses",
            "review_list",
            "tag_list"
    })
    private User user;

    @Column(name = "reply_content", nullable = false, columnDefinition="TEXT")
    private String replyContent;

    @Column(name = "reply_sequence", nullable = false)
    private int replySequence;

    @CreatedDate
    @Column(name = "reply_time", nullable = false, updatable = false)
    private LocalDateTime replyTime;
}

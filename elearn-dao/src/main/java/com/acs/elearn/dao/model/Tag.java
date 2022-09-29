package com.acs.elearn.dao.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tag_id", nullable = false)
    private String tagId;

    @Column(name = "tag_name", length = 64)
    private String tagName;

    @Column(name = "tag_info")
    private String tagInfo;
}

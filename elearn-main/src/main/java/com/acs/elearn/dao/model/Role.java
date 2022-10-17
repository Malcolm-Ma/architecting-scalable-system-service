package com.acs.elearn.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "role_id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id", nullable = false)
    private Long roleId = Long.valueOf(1);

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "role_client", nullable = false)
    private int roleClient;

    @Column(name = "display_name", nullable = false)
    private String displayName;
}

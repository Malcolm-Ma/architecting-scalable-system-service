package com.acs.elearn.dao.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    private int roleId;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "userRole")
    private List<User> userList;

    @Column(name = "role_client", nullable = false)
    private int roleClient;

    @Column(name = "display_name", nullable = false)
    private int displayName;
}

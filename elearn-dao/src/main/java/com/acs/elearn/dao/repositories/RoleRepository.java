package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findRoleByRoleId(Long RoleId);
}
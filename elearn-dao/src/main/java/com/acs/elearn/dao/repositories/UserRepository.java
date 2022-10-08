package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUserId(String userId);
}
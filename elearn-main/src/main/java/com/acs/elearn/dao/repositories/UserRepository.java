package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    User findByUserId(String userId);

    User findUserByUserId(String userId);

    void deleteById(String userId);

    User findUserByUserUsername(String userUserName);

}
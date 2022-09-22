package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.UserActionTracing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActionTracingRepository extends JpaRepository<UserActionTracing, Integer> {
}

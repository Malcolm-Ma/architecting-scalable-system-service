package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.QuizType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizTypeRepository extends JpaRepository<QuizType, String> {
}

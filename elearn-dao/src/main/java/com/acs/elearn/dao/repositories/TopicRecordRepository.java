package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.TopicRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRecordRepository extends JpaRepository<TopicRecord, Integer> {
}

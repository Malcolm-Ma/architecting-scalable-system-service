package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.ReplyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRecordRepository extends JpaRepository<ReplyRecord, String> {
}
package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}

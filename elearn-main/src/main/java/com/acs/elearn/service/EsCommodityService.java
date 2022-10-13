package com.acs.elearn.service;

import com.acs.elearn.dao.es.model.EsCommodity;
import org.springframework.data.domain.Page;

/**
 * Elasticsearch Commodity Service
 *
 * @author Mingze Ma
 */
public interface EsCommodityService {

    /**
     * Import all commodity from mysql db
     */
    int importAll();

    Page<EsCommodity> searchByKeywords(String keywords);
}

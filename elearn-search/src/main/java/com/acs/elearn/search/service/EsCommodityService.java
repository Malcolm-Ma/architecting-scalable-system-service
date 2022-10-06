package com.acs.elearn.search.service;

import com.acs.elearn.search.domain.model.EsCommodity;
import org.springframework.data.domain.Page;

import java.util.List;

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

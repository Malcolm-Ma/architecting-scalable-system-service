package com.acs.elearn.search.service;

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
}

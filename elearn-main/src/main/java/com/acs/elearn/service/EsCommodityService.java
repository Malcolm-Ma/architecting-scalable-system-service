package com.acs.elearn.service;

import com.acs.elearn.dao.es.model.EsCommodity;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.CourseInformation;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
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

    boolean createEsCommodity(Commodity commodity);

    void updateEsCommodity(Commodity commodity) throws Exception;

    boolean deleteEsCommodity(Commodity commodity) throws Exception;
    Page<EsCommodity> searchByKeywords(String keywords);
}

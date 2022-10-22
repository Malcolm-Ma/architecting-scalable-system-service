package com.acs.elearn.dao.es.repository;

import com.acs.elearn.dao.es.model.EsCommodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Mingze Ma
 */
public interface EsCommodityRepository extends ElasticsearchRepository<EsCommodity, String> {

    Page<EsCommodity> findByCommodityNameOrCommodityIntroduction(String commodityName, String commodityIntroduction,
                                                                 Pageable pageable);

    EsCommodity findByCommodityId(String esCommodityId);

}

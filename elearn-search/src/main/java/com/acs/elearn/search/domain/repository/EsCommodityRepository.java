package com.acs.elearn.search.domain.repository;

import com.acs.elearn.search.domain.model.EsCommodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Mingze Ma
 */
public interface EsCommodityRepository extends ElasticsearchRepository<EsCommodity, String> {

    Page<EsCommodity> findByCommodityNameOrCommodityIntroduction(String commodityName, String commodityIntroduction,
                                                                 Pageable pageable);

}

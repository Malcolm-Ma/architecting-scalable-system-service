package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.es.model.EsCommodity;
import com.acs.elearn.dao.es.repository.EsCommodityRepository;
import com.acs.elearn.service.EsCommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Elasticsearch Commodity Service Implementation
 *
 * @author Mingze Ma
 */
@Slf4j
@Service
public class EsCommodityServiceImpl implements EsCommodityService {

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    EsCommodityRepository esCommodityRepository;

    @Override
    public int importAll() {
        // Delete existing data
        log.info("Delete all data in index:Commodity");
        deleteAll();
        // Get all data from mysql
        List<Commodity> allCommodity = commodityRepository.findAll();
        List<EsCommodity> esCommodities = new ArrayList<>();
        // Transfer mysql entity to es model
        allCommodity.forEach(commodity -> {
            EsCommodity esCommodity = new EsCommodity();
            BeanUtil.copyProperties(commodity, esCommodity, CopyOptions.create().setIgnoreError(true));
            esCommodities.add(esCommodity);
        });
        // save data
        Iterable<EsCommodity> esCommodityIterable = esCommodityRepository.saveAll(esCommodities);
        log.info("Import all data from db:mysql to index:Commodity");
        Iterator<EsCommodity> iterator = esCommodityIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    private void deleteAll() {
        esCommodityRepository.deleteAll();
    }

    @Override
    public Page<EsCommodity> searchByKeywords(String keywords) {
        Pageable pageable = PageRequest.of(1, 10);
        return esCommodityRepository.findByCommodityNameOrCommodityIntroduction(keywords, keywords, pageable);
    }
}

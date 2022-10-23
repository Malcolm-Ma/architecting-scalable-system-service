package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.CourseInformation;
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

    final CommodityRepository commodityRepository;

    final EsCommodityRepository esCommodityRepository;

    public EsCommodityServiceImpl(
            CommodityRepository commodityRepository,
            EsCommodityRepository esCommodityRepository
    ) {
        this.commodityRepository = commodityRepository;
        this.esCommodityRepository = esCommodityRepository;
    }

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
    public boolean createEsCommodity(Commodity commodity){
        List<String> courseIdList = new ArrayList<>();
        List<CourseInformation> courseList = commodity.getCourseList();
        int courseNum = courseList.size();
        for(int i=0; i<courseNum; i++){
            courseIdList.add(courseList.get(i).getCourseId());
        }
        EsCommodity esCommodity = new EsCommodity();
        esCommodity.setCommodityId(commodity.getCommodityId());
        esCommodity.setCourseIdList(courseIdList);
        esCommodity.setCommodityName(commodity.getCommodityName());
        esCommodity.setCommodityIntroduction(commodity.getCommodityIntroduction());
        esCommodity.setCommodityStar(commodity.getCommodityStar());
        esCommodity.setCommodityPrice(commodity.getCommodityPrice());
        esCommodity.setCommodityDiscount(commodity.getCommodityDiscount());
        esCommodity.setCommoditySoldCnt(commodity.getCommoditySoldCnt());
        esCommodity.setCommodityStatus(commodity.getCommodityStatus());
        esCommodityRepository.save(esCommodity);
        if(esCommodityRepository.findById(commodity.getCommodityId()) == null)
            return false;
        return true;
    }

    @Override
    public void updateEsCommodity(Commodity commodity) throws Exception {
        EsCommodity curEsCommodity = esCommodityRepository.findByCommodityId(commodity.getCommodityId());
        if (curEsCommodity != null) {
            curEsCommodity.setCommodityName(commodity.getCommodityName());
            curEsCommodity.setCommodityIntroduction(commodity.getCommodityIntroduction());
            curEsCommodity.setCommodityStar(commodity.getCommodityStar());
            curEsCommodity.setCommodityPrice(commodity.getCommodityPrice());
            curEsCommodity.setCommodityDiscount(commodity.getCommodityDiscount());
            curEsCommodity.setCommoditySoldCnt(commodity.getCommoditySoldCnt());
            curEsCommodity.setCommodityStatus(commodity.getCommodityStatus());
        }
        else {
            throw new Exception("esCommodity is not existing");
        }
    }

    @Override
    public boolean deleteEsCommodity(Commodity commodity) throws Exception {
        EsCommodity esCurCommodity = esCommodityRepository.findByCommodityId(commodity.getCommodityId());
        if(esCurCommodity != null){
            esCommodityRepository.delete(esCurCommodity);
            if(esCommodityRepository.findByCommodityId(commodity.getCommodityId()) != null)
                return false;
            return true;
        } else{
            throw new Exception("commodity is not existing");
        }
    }

    @Override
    public Page<EsCommodity> searchByKeywords(String keywords) {
        Pageable pageable = PageRequest.of(1, 10);
        return esCommodityRepository.findByCommodityNameOrCommodityIntroduction(keywords, keywords, pageable);
    }
}

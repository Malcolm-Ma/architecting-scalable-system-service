package com.acs.elearn.service.impl;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityRepository commodityRepository;
    public List<Commodity> searchCommodity(String keyWord, Integer page_size, Integer time, Integer price){
        return commodityRepository.findByCommodityName(keyWord);
    }

    public List<Commodity> showCommodityInHomePage(Integer limit){ // show top 10 sales
        return commodityRepository.findAll(
                Sort.by(Sort.Direction.DESC, "commodity_sold_cnt")
        ).subList(0,limit);
    }
}

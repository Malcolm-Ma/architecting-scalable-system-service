package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.service.CommodityService;
import com.acs.elearn.common.vo.CommoditySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityRepository commodityRepository;

    @Override
    public List<Commodity> searchCommodity(CommoditySearchRequest request){
        if (request.getPrice() == null && request.getStar() == null) {
            return commodityRepository.findByCommodityNameOrCommodityIntroduction(
                    request.getKeyword(),
                    request.getKeyword(),
                    Pageable.ofSize(request.getPageSize()));
        } else if (request.getStar() == null){
            return commodityRepository.findByCommodityNameOrCommodityIntroductionAndCommodityPrice(
                    request.getKeyword(),
                    request.getKeyword(),
                    request.getPrice(),
                    Pageable.ofSize(request.getPageSize()));
        } else {
            return commodityRepository.findByCommodityNameOrCommodityIntroductionAndCommodityPriceAndCommodityStar(
                    request.getKeyword(),
                    request.getKeyword(),
                    request.getPrice(),
                    request.getStar(),
                    Pageable.ofSize(request.getPageSize()));
        }
    }
    @Override
    public List<Commodity> showCommodityInHomePage(Integer limit){ // show top 10 sales
        return commodityRepository.findAll(
                Sort.by(Sort.Direction.DESC, "commodity_sold_cnt")
        ).subList(0,limit);
    }

    @Override
    public String addCommodity(){
        return null;
    }
    @Override
    public String updateCommodity(Commodity commodity) {
//        try {
        Commodity curCommodity = commodityRepository.findCommodityByCommodityId(commodity.getCommodityId());
        if (curCommodity == null) {
            BeanUtil.copyProperties(commodity, curCommodity, CopyOptions.create().setIgnoreNullValue(true));
            commodityRepository.save(curCommodity);
//        userRepository.save(commodity);
            return "Add successfully";
//        } catch(Exception e){
//            Asserts.fail(e.getMessage());
//            return null;
//        }
        }
        else {
            return "failed";
        }
    }
    @Override
    public String deleteCommodity(Commodity commodity){
        return null;
    }
    @Override
    public Commodity getCommodityInfo(String commodityID){ // show top 10 sales
        return commodityRepository.getReferenceById(commodityID);
    }
}

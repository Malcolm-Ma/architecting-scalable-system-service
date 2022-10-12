package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.common.vo.CommodityCreateRequest;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.UserRepository;
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
    UserRepository userRepository;

    @Override
    public List<Commodity> searchCommodity(CommoditySearchRequest request){
        if (request.getPrice() == null && request.getStar() == null) {
            return commodityRepository.findByCommodityNameOrCommodityIntroductionContains(
                    request.getKeyword(),
                    request.getKeyword(),
                    Pageable.ofSize(request.getPageSize()));
        } else if (request.getStar() == null){
            return commodityRepository.findByCommodityNameOrCommodityIntroductionAndCommodityPriceContains(
                    request.getKeyword(),
                    request.getKeyword(),
                    request.getPrice(),
                    Pageable.ofSize(request.getPageSize()));
        } else {
            return commodityRepository.findByCommodityNameOrCommodityIntroductionAndCommodityPriceAndCommodityStarContains(
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
                Sort.by(Sort.Direction.DESC, "commoditySoldCnt")
        ).subList(0,limit);
    }

    @Override
    public String createCommodity(CommodityCreateRequest request){
        User publishBy = userRepository.findByUserId(request.getUserId());
        Commodity commodity = new Commodity();
        //commodity.setPublishedBy(publishBy);
        return null;
    }
    @Override
    public String updateCommodity(Commodity commodity) {
        Commodity curCommodity = commodityRepository.findByCommodityId(commodity.getCommodityId());
        if (curCommodity != null) {
            BeanUtil.copyProperties(commodity, curCommodity, CopyOptions.create().setIgnoreNullValue(true));
            commodityRepository.save(curCommodity);
            return "Add successfully";
        }
        else {
            return "Fail to add";
        }
    }
    @Override
    public String deleteCommodity(Commodity commodity){
        Commodity curCommodity = commodityRepository.findByCommodityId(commodity.getCommodityId());
        if(curCommodity != null){
            commodityRepository.delete(curCommodity);
            return "Delete successfully";
        } else{
            return "Fail to delete";
        }
    }
    @Override
    public Commodity getCommodityInfo(String commodityID){ // show top 10 sales
        return commodityRepository.findByCommodityId(commodityID);
    }
}

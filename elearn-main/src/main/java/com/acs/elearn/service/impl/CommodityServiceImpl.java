package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.vo.CommodityCreateRequest;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.CourseInformation;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.service.CommodityService;
import com.acs.elearn.vo.CommoditySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityRepository commodityRepository;
    UserServiceImpl userServiceImpl;
    CourseServiceImpl courseServiceImpl;

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
    public Commodity createCommodity(CommodityCreateRequest request){
        User publishBy = userServiceImpl.getUserInfo(request.getUserId());
        List<CourseInformation> courseList = new ArrayList<>();
        int courseNum = request.getCourseId().toArray().length;
        for(int i=0; i<courseNum; i++){
            courseList.add( courseServiceImpl.getCourseInfo( request.getCourseId().get(i) ) );
        }
        Commodity commodity = new Commodity();
        commodity.setPublishedBy(publishBy);
        commodity.setCourseList(courseList);
        commodity.setCommodityName(request.getCommodityName());
        commodity.setCommodityIntroduction(request.getCommodityIntroduction());
        commodity.setCommodityPrice(request.getCommodityPrice());
        commodity.setCommodityDiscount(request.getCommodityDiscount());
        commodity.setCommoditySoldCnt(0);
        commodity.setCommodityStatus(request.getCommodityStatus());
        commodityRepository.save(commodity);
        return commodity;
    }
    
    @Override
    public String updateCommodity(Commodity commodity) throws Exception {
        Commodity curCommodity = commodityRepository.findByCommodityId(commodity.getCommodityId());
        if (curCommodity != null) {
            BeanUtil.copyProperties(commodity, curCommodity, CopyOptions.create().setIgnoreNullValue(true));
            commodityRepository.save(curCommodity);
            return "Add successfully";
        }
        else {
            throw new Exception("commodity is not existing");
        }
    }
    @Override
    public String deleteCommodity(Commodity commodity) throws Exception {
        Commodity curCommodity = commodityRepository.findByCommodityId(commodity.getCommodityId());
        if(curCommodity != null){
            commodityRepository.delete(curCommodity);
            return "Delete successfully";
        } else{
            throw new Exception("commodity is not existing");
        }
    }
    @Override
    public Commodity getCommodityInfo(String commodityID){ // show top 10 sales
        return commodityRepository.findByCommodityId(commodityID);
    }
}

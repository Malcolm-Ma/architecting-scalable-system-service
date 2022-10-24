package com.acs.elearn.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.CourseService;
import com.acs.elearn.service.EsCommodityService;
import com.acs.elearn.service.UserService;
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
    final CommodityRepository commodityRepository;
    final UserService userService;
    final CourseService courseService;
    final EsCommodityService esCommodityService;
    final UserRepository userRepository;

    @Autowired
    public CommodityServiceImpl(
            CommodityRepository commodityRepository,
            UserRepository userRepository,
            UserService userService,
            CourseService courseService,
            EsCommodityService esCommodityService
    ) {
        this.commodityRepository = commodityRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.courseService = courseService;
        this.esCommodityService = esCommodityService;
    }

    @Override
    public int importAll(){
        return esCommodityService.importAll();
    }
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
        List<Commodity> all = commodityRepository.findAll(
                Sort.by(Sort.Direction.DESC, "commoditySoldCnt"));

        return all.subList(0, limit > all.size() ? all.size() : limit);
    }

    @Override
    public String createCommodity(CommodityCreateRequest request) throws Exception {
        try{
            User publishBy = userService.getUserInfo(request.getUserId());
            List<CourseInformation> courseList = new ArrayList<>();
            int courseNum = request.getCourseId().toArray().length;
            for(int i=0; i<courseNum; i++){
                courseList.add( courseService.getCourseInfo( request.getCourseId().get(i) ) );
            }
            Commodity commodity = new Commodity();
            commodity.setPublishedBy(publishBy);
            commodity.setCourseList(courseList);
            commodity.setCommodityName(request.getCommodityName());
            commodity.setCommodityCover(request.getCommodityCover());
            commodity.setCommodityIntroduction(request.getCommodityIntroduction());
            commodity.setCommodityPrice(request.getCommodityPrice());
            commodity.setCommodityDiscount(request.getCommodityDiscount());
            commodity.setCommoditySoldCnt(0);
            commodity.setCommodityStatus(request.getCommodityStatus());
            commodityRepository.save(commodity);
            List<Commodity> currComList = publishBy.getPublishedCommodities();
            currComList.add(commodity);
            publishBy.setPublishedCommodities(currComList);
            userRepository.save(publishBy);
            if(!esCommodityService.createEsCommodity(commodity)) {
                commodityRepository.delete(commodity);
                return "Fail to create";
            }
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "Create successfully";
    }

    @Override
    public String updateCommodity(Commodity commodity) throws Exception {
        Commodity curCommodity = commodityRepository.findByCommodityId(commodity.getCommodityId());
        if (curCommodity != null) {
            BeanUtil.copyProperties(commodity, curCommodity, CopyOptions.create().setIgnoreNullValue(true));
            commodityRepository.save(curCommodity);
            esCommodityService.updateEsCommodity(commodity);
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
            if(!esCommodityService.deleteEsCommodity(commodity)) {
                commodityRepository.save(curCommodity);
                return "Fail to delete";
            }
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

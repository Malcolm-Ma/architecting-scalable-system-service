//package com.acs.elearn.service;
//
//import com.acs.elearn.dao.dto.CommodityDto;
//import com.acs.elearn.dao.model.Commodity;
//import com.acs.elearn.dao.repositories.CommodityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CommodityService {
//    @Autowired
//    CommodityRepository commodityRepository;
//
//    public Commodity addNewCommodity(CommodityDto commodityDto) {
//        Commodity commodity = new Commodity(commodityDto);
//        return commodityRepository.save(commodity);
//    }
//}

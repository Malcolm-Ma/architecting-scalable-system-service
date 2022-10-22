package com.acs.elearn.service;

import com.acs.elearn.vo.CommodityCreateRequest;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.vo.CommoditySearchRequest;

import java.util.List;

public interface CommodityService {
    int importAll();
    List<Commodity> searchCommodity(CommoditySearchRequest request);
    List<Commodity> showCommodityInHomePage(Integer limit);
    String createCommodity(CommodityCreateRequest request); // add new commodity
    String updateCommodity(Commodity commodity) throws Exception; // update commodity information
    String deleteCommodity(Commodity commodity) throws Exception;
    Commodity getCommodityInfo(String commodityID);
}

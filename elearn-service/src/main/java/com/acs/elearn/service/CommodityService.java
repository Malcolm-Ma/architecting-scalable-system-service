package com.acs.elearn.service;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.common.vo.CommoditySearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommodityService {
    List<Commodity> searchCommodity(CommoditySearchRequest request);
    List<Commodity> showCommodityInHomePage(Integer limit);
    String addCommodity(); // add new commodity
    String updateCommodity(Commodity commodity); // update commodity information
    String deleteCommodity(Commodity commodity);
    Commodity getCommodityInfo(String commodityID);
}

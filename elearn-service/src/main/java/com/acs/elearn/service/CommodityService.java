package com.acs.elearn.service;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.repositories.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommodityService {
    public List<Commodity> searchCommodity(String keyWord, Integer page_size, Integer time, Integer price);
    public List<Commodity> showCommodityInHomePage(Integer limit);
}

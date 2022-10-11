package com.acs.elearn.web.controllers;

import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.search.controller.vo.SearchCommodityRequest;
import com.acs.elearn.search.domain.model.EsCommodity;
import com.acs.elearn.service.CommodityService;
import com.acs.elearn.common.vo.CommoditySearchRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @ApiOperation("Search commodity by keywords")
    @PostMapping("/search_commodity")
    @ResponseBody
    public List<Commodity> searchCommodity(@RequestBody CommoditySearchRequest request) {
        return commodityService.searchCommodity(request);
    }


    @GetMapping(path = "/show_commodity_in_home_page", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Commodity> showCommodityInHomePage(Integer limit) {
        return commodityService.showCommodityInHomePage(limit);
    }

    @GetMapping(path = "/get_commodity_info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Commodity getCommodityInfo(String commodityID) {
        return commodityService.getCommodityInfo(commodityID);
    }
}

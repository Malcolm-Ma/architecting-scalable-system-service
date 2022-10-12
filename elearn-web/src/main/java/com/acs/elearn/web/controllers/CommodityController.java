package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.search.controller.vo.SearchCommodityRequest;
import com.acs.elearn.search.domain.model.EsCommodity;
import com.acs.elearn.service.CommodityService;
import com.acs.elearn.common.vo.CommoditySearchRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @ApiOperation("Search commodity by keywords")
    @PostMapping("/search_commodity")
    public ResponseEntity<Object> searchCommodity(@RequestBody CommoditySearchRequest request) {
        List<Commodity> res = commodityService.searchCommodity(request);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @GetMapping(path = "/show_commodity_in_home_page", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Commodity> showCommodityInHomePage(@RequestParam Integer limit) {
        return commodityService.showCommodityInHomePage(limit);
    }

    @GetMapping(path = "/add_commodity", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCommodity(String commodityID) {
        return commodityService.addCommodity();
    }

    @GetMapping(path = "/update_commodity", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCommodity(Commodity commodity) {
        return commodityService.updateCommodity(commodity);
    }

    @GetMapping(path = "/delete_commodity", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCommodity(Commodity commodity) {
        return commodityService.deleteCommodity(commodity);
    }

    @GetMapping(path = "/get_commodity_info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Commodity getCommodityInfo(String commodityID) {
        return commodityService.getCommodityInfo(commodityID);
    }
}

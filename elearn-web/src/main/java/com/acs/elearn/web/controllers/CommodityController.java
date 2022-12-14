package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.vo.CommodityCreateRequest;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.service.CommodityService;
import com.acs.elearn.vo.CommoditySearchRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/commodity")
public class CommodityController {
    final CommodityService commodityService;

    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @ApiOperation("Sync data with mysql")
    @GetMapping("/data/sync")
    @ResponseBody
    public ResponseEntity<ResponseModel<Integer>> syncData() {
        Integer res = commodityService.importAll();
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @ApiOperation("Search commodity by keywords")
    @PostMapping("/search_commodity")
    @ResponseBody
    ResponseEntity<ResponseModel<List<Commodity>>> searchCommodity(@Validated @RequestBody CommoditySearchRequest request) {
        List<Commodity> res = commodityService.searchCommodity(request);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @GetMapping(path = "/show_commodity_in_home_page", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ResponseModel<List<Commodity>>> showCommodityInHomePage(@RequestParam Integer limit) {
        List<Commodity> res = commodityService.showCommodityInHomePage(limit);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @PostMapping(path = "/create_commodity", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ResponseModel<String>> createCommodity(@RequestBody CommodityCreateRequest request) throws Exception {
        String res = commodityService.createCommodity(request);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @PostMapping(path = "/update_commodity", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResponseModel<String>> updateCommodity(@RequestBody Commodity commodity) throws Exception {
        String res = commodityService.updateCommodity(commodity);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @PostMapping(path = "/delete_commodity", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ResponseModel<String>> deleteCommodity(@RequestBody Commodity commodity) throws Exception {
        String res = commodityService.deleteCommodity(commodity);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @GetMapping(path = "/get_commodity_info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
     ResponseEntity<ResponseModel<Commodity>> getCommodityInfo(@RequestParam String commodityID) {
        Commodity res = commodityService.getCommodityInfo(commodityID);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }
}

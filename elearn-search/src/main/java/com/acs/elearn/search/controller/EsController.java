package com.acs.elearn.search.controller;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.search.vo.SearchCommodityRequest;
import com.acs.elearn.dao.es.model.EsCommodity;
import com.acs.elearn.service.EsCommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mingze Ma
 */
@RestController
@Api(tags = "Search Controller")
@RequestMapping("/search")
public class EsController {

    @Autowired
    EsCommodityService esCommodityService;

    @ApiOperation("Sync data with mysql")
    @GetMapping("/data/sync")
    @ResponseBody
    public ResponseEntity<ResponseModel<Integer>> syncData() {
        Integer res = esCommodityService.importAll();
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @ApiOperation("Search commodity by keywords")
    @PostMapping("/commodity")
    @ResponseBody
    public ResponseEntity<ResponseModel<Page<EsCommodity>>> searchCommodity(SearchCommodityRequest request) {
        Page<EsCommodity> res = esCommodityService.searchByKeywords(request.getKeyword());
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

}

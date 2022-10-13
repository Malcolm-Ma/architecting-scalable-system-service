package com.acs.elearn.search.controller;

import com.acs.elearn.search.vo.SearchCommodityRequest;
import com.acs.elearn.dao.es.model.EsCommodity;
import com.acs.elearn.service.EsCommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    public Integer syncData() {
        return esCommodityService.importAll();
    }

    @ApiOperation("Search commodity by keywords")
    @PostMapping("/commodity")
    @ResponseBody
    public Page<EsCommodity> searchCommodity(SearchCommodityRequest request) {
        return esCommodityService.searchByKeywords(request.getKeyword());
    }

}

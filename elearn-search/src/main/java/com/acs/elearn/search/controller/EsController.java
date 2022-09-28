package com.acs.elearn.search.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mingze Ma
 */
@RestController
@Api(tags = "Search Controller")
@RequestMapping("/search")
public class EsController {

    @ApiOperation("Sync data with mysql")
    @GetMapping("/data/sync")
    @ResponseBody
    public Integer syncData() {
        return null;
    }

}

package com.acs.elearn.web.controllers;

import com.acs.elearn.dao.dto.CommodityDto;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Commodity> addCommodity(@RequestBody CommodityDto commodityDto) {
        Commodity c = commodityService.addNewCommodity(commodityDto);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
}

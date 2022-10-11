package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Commodity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {

    Commodity findCommodityByCommodityId(String commodityId);
    List<Commodity> findByCommodityNameOrCommodityIntroduction(String name, String introduction, Pageable page);
    List<Commodity> findByCommodityNameOrCommodityIntroductionAndCommodityPrice(String name, String introduction, Integer price, Pageable page);
    List<Commodity> findByCommodityNameOrCommodityIntroductionAndCommodityPriceAndCommodityStar(String name, String introduction, Integer price, Double star, Pageable page);
}

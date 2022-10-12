package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Commodity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {

    Commodity findByCommodityId(String commodityId);
    List<Commodity> findByCommodityNameOrCommodityIntroductionContains(String name, String introduction, Pageable page);
    List<Commodity> findByCommodityNameOrCommodityIntroductionAndCommodityPriceContains(String name, String introduction, Double price, Pageable page);
    List<Commodity> findByCommodityNameOrCommodityIntroductionAndCommodityPriceAndCommodityStarContains(String name, String introduction, Double price, Double star, Pageable page);

}

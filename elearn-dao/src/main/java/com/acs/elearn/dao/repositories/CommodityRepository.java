package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {
    List<Commodity> findByCommodityName(String keyWord);
    Commodity findCommodityByCommodityId(String commodityId);
}

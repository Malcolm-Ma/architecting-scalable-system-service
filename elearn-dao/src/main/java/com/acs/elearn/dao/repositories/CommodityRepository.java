package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, String> {
    public List<Commodity> findByCommodityName(String keyWord);
}

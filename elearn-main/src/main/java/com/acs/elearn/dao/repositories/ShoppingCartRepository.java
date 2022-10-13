package com.acs.elearn.dao.repositories;

import com.acs.elearn.dao.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {
    @Override
    List<ShoppingCart> findAllById(Iterable<String> strings);
    ShoppingCart findShoppingCartByCartId(String CartId);
}
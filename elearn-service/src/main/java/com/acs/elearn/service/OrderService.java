package com.acs.elearn.service;

import com.acs.elearn.dao.dto.OrderDto;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.Order;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CommodityRepository commodityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public String placeOrder() {

        // 这里是hardcode，
        // 正常来说应该提取购物车里的所有商品来order，不过在demo里还没有购物车类，
        // 为了demo我只是简单地order商品列表里的所有商品。
        List<Commodity> selectedProducts = commodityRepository.findAll();

        // 正常来说totalPrice是直接从购物车里获取的，这里我是简单暴力地把所有商品的price相加
        float totalPrice = 0.0f;
        for(Commodity product: selectedProducts) {
            totalPrice += product.getPrice();
        }

        // 创建新的Order
        OrderDto orderDto = new OrderDto(totalPrice);
        Order newOrder = new Order(orderDto);
        newOrder.setCommodityList(selectedProducts);

        entityManager.persist(newOrder);

        return "Saved";
    }

    public Iterable<Order> allOrders() {
        return orderRepository.findAll();
    }
}

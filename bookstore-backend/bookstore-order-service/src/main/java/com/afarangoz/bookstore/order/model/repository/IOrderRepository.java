package com.afarangoz.bookstore.order.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.afarangoz.bookstore.api.common.model.entity.Order;

public interface IOrderRepository extends JpaRepository<Order, Long>{

}

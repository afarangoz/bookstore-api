package com.afarangoz.bookstore.order.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afarangoz.bookstore.api.common.model.entity.Order;
import com.afarangoz.bookstore.order.model.repository.IOrderRepository;

@Service
@Transactional
public class OrderService implements IOrderService{
	
	@Autowired
	IOrderRepository orderRepository;

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	@Override
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public void delete(Order entity) {
		orderRepository.delete(entity);
	}

	@Override
	public Order createOrUpdate(Order entity) {
		return orderRepository.save(entity);
	}

}

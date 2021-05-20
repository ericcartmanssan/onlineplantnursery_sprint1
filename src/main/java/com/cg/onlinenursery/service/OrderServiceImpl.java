package com.cg.onlinenursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinenursery.entity.Order;
import com.cg.onlinenursery.entity.Planters;
import com.cg.onlinenursery.repository.OrderRepository;
import com.cg.onlinenursery.repository.PlantersRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PlantersRepository plantersRepository;

	@Override
	public List<Order> getOrder() {
		List<Order> orderList = orderRepository.findAll();
		return orderList;
		// TODO Auto-generated method stub
		// return null;
	}

	@Override
	public Order viewOrder(int bookingOrderId) {
//		Optional<Order> optional = orderRepository.findById(orderId);
//		return optional.get();
		Optional<Order> message = orderRepository.findById(bookingOrderId);
		return (message.isPresent()) ? message.get() : null;
		// TODO Auto-generated method stub
		// return null;
	}

	@Override
	public List<Order> viewAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public Order getOrder(int bookingOrderId) {
		Optional<Order> optional = orderRepository.findById(bookingOrderId);
		return optional.get();
		// TODO Auto-generated method stub
		// return null;
	}

	public List<Planters> viewPlanters(int bookingOrderId) {
		Order order = getOrder(bookingOrderId);
		System.out.println(order + "*****");
		List<Planters> planters = null;
		if (order != null) {
			planters = order.getPlanters();
		}
		return planters;
	}

	@Override
	public Order addOrder(Order order) {
		// TODO Auto-generated method stub
		Order order1 = orderRepository.save(order);
		return order1;

	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
//		Order orderexist = viewOrder(order.getBookingOrderId());
//		if (orderexist == null) {
//			order =orderRepository.save(order);
//		}
//		return order;
		if (orderRepository.existsById(order.getBookingOrderId())) {
			orderRepository.save(order);
			return order;
		}
		return order;
	}

	@Override
	public void deleteOrderById(int bookingOrderId) {
		// orderRepository.deleteById(orderId);

		orderRepository.deleteById(bookingOrderId);
		// TODO Auto-generated method stub
		// return null;
	}

//	public List<Order> viewOrderByDate(LocalDate orderDate) {
//		// TODO Auto-generated method stub
//		return null;
}
//
//	public List<Order> viewOrderList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
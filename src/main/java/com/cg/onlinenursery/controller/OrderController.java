package com.cg.onlinenursery.controller;

import java.net.URI;
import java.util.List;

//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.onlinenursery.entity.Order;
import com.cg.onlinenursery.entity.Planters;
import com.cg.onlinenursery.exception.OrderIdNotFoundException;
import com.cg.onlinenursery.exception.PlantersIdNotFoundException;
import com.cg.onlinenursery.service.OrderServiceImpl;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderservice;
	Logger logger = LoggerFactory.getLogger(OrderController.class);

	// 1.Get All orderList

	@GetMapping
	public ResponseEntity<List<Order>> viewAllOrder() {
		logger.info("Inside viewAllOrder method");
		List<Order> OrderList = orderservice.viewAllOrders();
		// Creating an error response.
		ResponseEntity<List<Order>> response = new ResponseEntity<>(OrderList, HttpStatus.NOT_FOUND);

		if (!OrderList.isEmpty()) {
			response = new ResponseEntity<>(OrderList, HttpStatus.OK);
		}

		return response;
	}

	// 2.Get Order By Booking id

	@GetMapping("/{bookingOrderId}")
	public ResponseEntity<Object> viewOrder(@PathVariable("bookingOrderId") int bookingOrderId)
			throws OrderIdNotFoundException {
		logger.info("Inside viewAllOrder method");
		Order order = orderservice.viewOrder(bookingOrderId);
		logger.info("View Order" + order);

		if (order == null) {
			// response= new ResponseEntity<>(pizzaOrderList, HttpStatus.OK);
			throw new OrderIdNotFoundException(bookingOrderId + " not found");
		}
		return new ResponseEntity<>(order, HttpStatus.ACCEPTED);

	}

	@GetMapping("/planters/{bookingOrderId}")
	public ResponseEntity<List<Planters>> viewPlanters(@PathVariable("bookingOrderId") int bookingOrderId)
			throws PlantersIdNotFoundException {
		logger.info("Inside viewPlanters method" + bookingOrderId);
		List<Planters> planters = orderservice.viewPlanters(bookingOrderId);
		logger.info("View planters" + planters);

		if (planters == null) {
			throw new PlantersIdNotFoundException("PlantersId Not found ");
		}

		return new ResponseEntity<>(planters, HttpStatus.ACCEPTED);
	}

	// 3 Delete Order By Booking ID

	@DeleteMapping(value = "/{bookingOrderId}")
	public ResponseEntity<Object> deleteOrder(@PathVariable("bookingOrderId") int bookingOrderId)
			throws OrderIdNotFoundException {
		logger.info("Inside deleteOrder method");
		Order orderPresent = orderservice.viewOrder(bookingOrderId);
		logger.info("Delete Order" + orderPresent);
		if (orderPresent == null) {
			throw new OrderIdNotFoundException(bookingOrderId + " not found");
		}
		orderservice.deleteOrderById(bookingOrderId);
		return ResponseEntity.status(HttpStatus.OK).body("Order with order id  " + bookingOrderId + " deleted");

	}

//4 update order	
	@PutMapping("/{bookingOrderId}")
	public ResponseEntity<Object> updateOrder(@PathVariable("bookingOrderId") int bookingOrderId,
			@RequestBody Order order) {
		logger.info("Inside updateOrder method");
		order.setBookingOrderId(bookingOrderId);
		Order updateOrder = orderservice.updateOrder(order);
		logger.info("Update Order" + updateOrder);
		if (updateOrder == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order" + bookingOrderId + "Not found");
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(order.getBookingOrderId()).toUri();
			return ResponseEntity.created(location).build();
		}

	}

	// 5 Add the order

	@PostMapping
	public ResponseEntity<String> addOrder(@RequestBody Order order) throws OrderIdNotFoundException {
		// logger.debug("Planters no is coming here "+ planters.getPlanterId());
		// if(planters.getPlanterheight()<0|| planters.getPlanterCost()<0||
		// planters.getPlanterCapacity()<0|| planters.getPlanterColor().isEmpty()) {
		// logger.error("planters name is not here");
		// throw new PlantersIdNotFoundException(planters + "again enter planters name
		// ");
		if (order.getBookingOrderId() < 0) {
			// logger.error("planters id is coming here");
			throw new OrderIdNotFoundException("order id can not be negative");
		} else if (order.getTotalCost() < 0) {
			throw new OrderIdNotFoundException("order cost can not be negative");
			// Planters newPlanters = plantersService.addPlanters(planters);
		} else if (order.getTransactionMode().isEmpty()) {
			throw new OrderIdNotFoundException("transactionmode not be null");
		}
//		else {URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPlanters.getPlanterId()).toUri();
//		return ResponseEntity.created(location).build();
		else
			orderservice.addOrder(order);
		return ResponseEntity.status(HttpStatus.OK).body("order added successfully");
	}

//	@PostMapping
//	public ResponseEntity<Object> addOrder(@RequestBody Order Order) 
//	{logger.info("Inside addOrder method");
//		
//		Order orderList = orderservice.addOrder(Order);
//		logger.info("New Order" + Order);
//		if (orderList == null)
//
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
//	
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand((orderList).getBookingOrderId()).toUri();
//		return ResponseEntity.created(location).build();
//	}

}
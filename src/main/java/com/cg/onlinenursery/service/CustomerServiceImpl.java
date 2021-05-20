package com.cg.onlinenursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinenursery.entity.Customer;
import com.cg.onlinenursery.entity.Order;
import com.cg.onlinenursery.repository.CustomerRepository;
import com.cg.onlinenursery.repository.PlantersRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PlantersRepository plantersRepository;

	@Override
	public Customer addCustomer(Customer addcustomer) {
		Customer c1 = customerRepository.save(addcustomer);

		return c1;
	}
//		public List<Order> viewOrder(int customerId) {
//			Customer customer = viewCustomer(customerId);
//			System.out.println(customer+"*");List<Order> orders = null;
//			if (customer != null) {
//			List<Order> orders = customer.getOrder();
//			}
//			return orders;
//		}

//		public List<Planters> viewPlanters(int customerId) {
//			Planters planters = viewPlanters(plantersId);
//			System.out.println(planters+"*");List<Plants> plants = null;
//			if (planters != null) {
//				plants = planters.getPlants();
//			}
//			return plants;
//		}
	@Override
	public List<Customer> viewAllCustomers() {

		return customerRepository.findAll();
	}

	public List<Order> viewOrder(int customerId) {
		Customer customer = viewCustomer(customerId);
		System.out.println(customer + "*****");
		List<Order> order = null;
		if (customer != null) {
			order = customer.getOrder();
		}
		return order;
	}

	@Override
	public void deleteCustomerById(int customerid) {
		customerRepository.deleteById(customerid);

	}

	@Override
	public Customer updateCustomer(Customer customer) {

		if (customerRepository.existsById(customer.getCustomerId())) {
			customerRepository.save(customer);
			return customer;
		}
		return customer;
	}

	public boolean validateUser(int customerId) {
		Optional<Customer> cust1 = customerRepository.findById(customerId);

		if (cust1.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Customer viewCustomer(int customerId) {

		Optional<Customer> message = customerRepository.findById(customerId);
		return (message.isPresent()) ? message.get() : null;
	}

}
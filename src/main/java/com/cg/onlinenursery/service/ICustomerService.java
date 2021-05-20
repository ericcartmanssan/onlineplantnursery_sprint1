package com.cg.onlinenursery.service;

import java.util.List;

import com.cg.onlinenursery.entity.Customer;

public interface ICustomerService {

	public Customer addCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public void deleteCustomerById(int customerid);

	public Customer viewCustomer(int customerId);

	public List<Customer> viewAllCustomers();

	public boolean validateUser(int customerId);

}
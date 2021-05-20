package com.cg.onlinenursery.service;

import java.util.List;

import com.cg.onlinenursery.entity.Admin;
import com.cg.onlinenursery.entity.Customer;

public interface IAdminService {
	public Admin addNewAdmin(Admin admin);

//	public Admin signIn(Admin admin);
	public Admin signOut(Admin admin);

	public List<Admin> getAll();

	public Admin adminLogin(String adminName, String password);

	public void deleteAdmin(int adminId);

//	public List<Employee> fetchEmployeeTable(Employee employee);
//	public List<Employee> viewAllEmployeesByEmployeeId(int employeeId) throws EmployeeNotFoundException;
	public List<Customer> showAll(int adminId);
}

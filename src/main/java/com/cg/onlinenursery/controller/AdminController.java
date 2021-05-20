package com.cg.onlinenursery.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinenursery.entity.Admin;
import com.cg.onlinenursery.entity.Customer;
import com.cg.onlinenursery.exception.CustomerIdNotFoundException;
import com.cg.onlinenursery.service.IAdminService;

@RestController
@RequestMapping("/cv/admin")
public class AdminController {

	@Autowired
	IAdminService service;

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@PostMapping("/addAdmin")
	public ResponseEntity<String> addAdmin(@RequestBody Admin admin, HttpServletRequest request) {
		logger.info("Inside addAdmin method");
		// HttpSession session = request.getSession();
		// Admin sessionUser = (Admin) session.getAttribute("UserExists");
		// logger.info("Session User" + sessionUser);
		// if (sessionUser != null) {
		Admin adminData = service.addNewAdmin(admin);
		if (adminData != null)
			return new ResponseEntity<>("Added successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("User not added", HttpStatus.OK);
	}

	// else {
	// return new ResponseEntity<>("Login to add user", HttpStatus.BAD_REQUEST);
	// }
	// }

	@DeleteMapping("/deleteAdmin")
	public ResponseEntity<Object> deleteAdmin(@Valid @RequestParam int admin, HttpServletRequest request) {
		logger.info("Inside deleteAdmin method");
		HttpSession session = request.getSession();
		Admin sessionUser = (Admin) session.getAttribute("UserExists");
		logger.info("Session User" + sessionUser);
		if (sessionUser != null) {
			service.deleteAdmin(admin);
			return new ResponseEntity<Object>("Admin deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Admin with Admin Id Not Found", HttpStatus.BAD_REQUEST);
		}
	}

//	@GetMapping(value = "/{adminName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Admin> adminLogin(@PathVariable(value = "adminName") String adminName,
//			@PathVariable(value = "password") String password, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		Admin user = service.adminLogin(adminName, password);
//		if (user != null) {
//			session.setAttribute("UserExists", user);
//
//			logger.info("Login successfully!" + user);
//			return new ResponseEntity<>(user, HttpStatus.CREATED);
//		} else {
//			logger.info("Login Failed!!!");
//
//			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
//		}
//	}	

	@GetMapping(value = "/{adminName}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Admin> userAdminLogin(@PathVariable(value = "adminName") String adminName,
			@PathVariable(value = "password") String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin user = service.adminLogin(adminName, password);
		if (user != null) {
			session.setAttribute("UserExists", user);

			logger.info("Login successfully!" + user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			logger.info("Login Failed!!!");

			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}
	}

//	@PostMapping("/signOut")
//	public ResponseEntity<String> adminSignOut(@Valid @RequestBody Admin admin, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		session.removeAttribute("UserExists");
//		session.invalidate();
//		logger.info("Inside adminSignOut method");
//		Admin adminData = service.signOut(admin);
//
//		return new ResponseEntity<String>("Sign Out succesfull, Bye admin: " + adminData.getAdminId(), HttpStatus.OK);
//	}

	@GetMapping("/getAllAdmin")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		logger.info("Inside getAllAdmins method");
		List<Admin> adminList = service.getAll();
		return new ResponseEntity<List<Admin>>(adminList, HttpStatus.OK);
	}

	@GetMapping("/getAllCustomer/{adminId}")
	public ResponseEntity<Object> getAllCustomer(@PathVariable(value = "adminId") int adminId) {
		logger.info("Inside getAllCustomer method");
		List<Customer> customerList;
		try {
			customerList = service.showAll(adminId);
			return new ResponseEntity<Object>(customerList, HttpStatus.OK);
		} catch (CustomerIdNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return new ResponseEntity<Object>("Customer not found", HttpStatus.UNAUTHORIZED);
	}

}
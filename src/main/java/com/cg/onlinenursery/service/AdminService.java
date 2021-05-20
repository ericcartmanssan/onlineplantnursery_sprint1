package com.cg.onlinenursery.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlinenursery.entity.Admin;
import com.cg.onlinenursery.entity.Customer;
import com.cg.onlinenursery.exception.AdminExistsException;
import com.cg.onlinenursery.exception.AdminNotFoundException;
import com.cg.onlinenursery.exception.CustomerIdNotFoundException;
import com.cg.onlinenursery.exception.InvalidDataException;
import com.cg.onlinenursery.repository.IAdminRepository;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Autowired
	IAdminRepository repository;

	Logger logger = LoggerFactory.getLogger(AdminService.class);

	@Override
	public List<Admin> getAll() {
		// TODO Auto-generated method stub
		List<Admin> adminList = repository.findAll();
		if (adminList.isEmpty()) {
			logger.error("AdminNotFoundException in getAlladmin method");
			throw new AdminNotFoundException("No Admin found");
		} else
			return adminList;
	}

	@Override
	public List<Customer> showAll(int adminId) {
		// TODO Auto-generated method stub
		logger.info("Inside showAll method");
		Admin admin = repository.findById(adminId).get();
		if (admin == null) {
			logger.error("AdminNotFoundException in getAllCustomer method");
			throw new AdminNotFoundException("No Admin found");
		} else {
			List<Customer> empList = admin.getCustomer();
			if (empList == null) {
				throw new CustomerIdNotFoundException("No Customer Found");
			}
			return empList;
		}
	}

	@Override
	public Admin addNewAdmin(Admin admin) {
		logger.info("Inside addNewAdmin method");
//		Admin newAdmin = repository.save(admin);
//		return newAdmin;
		if (repository.existsByAdminId(admin.getAdminId())) {
			logger.error("Admin already exists");
			throw new AdminExistsException();
		} else {
			Admin adminObj = repository.save(admin);
			logger.info("Admin added");
			return adminObj;
		}
	}

	@Override
	public void deleteAdmin(int adminId) throws AdminNotFoundException {
		logger.info("Inside deleteAdmin method");
		if (repository.existsByAdminId(adminId)) {
			repository.deleteByAdminId(adminId);
		} else {
			logger.error("No admin with given id found");
			throw new AdminNotFoundException("No Admin found");
		}
	}

//	@Override
//	public String signIn(Admin admin) {
//		logger.info("Inside signIn method");
//		int id = admin.getAdminId();
//		String password = admin.getPassword();
//		String role = admin.getAdminName();
//		Admin adminData = repository.findByAdminIdAndPasswordAndAdminName(id, password, role);
//		if (adminData == null) {
//			logger.error("AdminNotFoundException in adminSignIn method");
//			throw new AdminNotFoundException("No admin present");
//		} else {
//
//			return adminData;
//		}
//
//	}

	@Override
	public Admin adminLogin(String adminName, String password) {
		Optional<Admin> opuserEntity = repository.findByAdminNameAndPassword(adminName, password);
		if (opuserEntity.isPresent()) {
			Admin userEntity = opuserEntity.get();
			logger.debug("user login");
			int n = userEntity.getAttempts();
			if (n < 3) {
				if (userEntity.getPassword().equals(password)) {
					return new Admin(userEntity.getAdminId(), userEntity.getAdminName(), userEntity.getPassword(),
							userEntity.getAttempts());
				} else {
					userEntity.setAttempts(n + 1);
					userEntity = repository.save(userEntity);
					logger.info("Invalid Password");
					throw new InvalidDataException(); // "Invalid Password"
				}
			} else {
				logger.info("Reached maximum limit");
				throw new InvalidDataException(); // "Reached maximum limit"
			}
		} else {
			logger.info("Login Failed");
			throw new InvalidDataException(); // "Login Failed"

		}
	}

	@Override
	public Admin signOut(Admin admin) {
		return admin;
	}

}
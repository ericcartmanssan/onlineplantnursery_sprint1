package com.cg.onlinenursery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinenursery.entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByAdminIdAndPasswordAndAdminName(int adminId, String password, String adminName);

	public boolean existsByAdminId(int adminId);

	public Admin findByAdminId(int adminId);

	Optional<Admin> findByAdminNameAndPassword(String adminName, String password);

	public void deleteByAdminId(int adminId);

//	public List<EmployeeDetails> findAllByEmployeeId(int employeeId);
}
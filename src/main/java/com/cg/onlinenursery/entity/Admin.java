package com.cg.onlinenursery.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "admin_master")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(Include.NON_NULL)
public class Admin {
	@Id
	/// @NotEmpty
	// @Size(min=3, max=6, message = "Admin Id should be between 3 to 6 characters")
	// @GeneratedValue
	private int adminId;
	// @NotEmpty(message = "Password must not be empty")
	// @Size(min=8, max=20, message="Password should be between 2 and 8 characters")
	// @Pattern(regexp="^(?=.[a-z])(?=.\\d)(?=.[A-Z])(?=.[@#$%!]).{8,20}",
	// message="Password must contain atleast one upper case, one lower case a digit
	// and a special character")
	private String password;
	// @NotEmpty(message = "AdminName must not be empty")
	private String adminName;

	// @Min(value = 0)
	private int attempts;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Customer.class)
	// @JsonIgnore
	@JoinColumn(name = "adminId")
	List<Customer> customer;
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Planters.class)
//	//@JsonIgnore
//	@JoinColumn(name="adminId")
//	List<Planters> planters;

//	private LoginStatus loginstatus;

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

//	public LoginStatus getLoginstatus() {
//		return loginstatus;
//	}
//	public void setLoginstatus(LoginStatus loginstatus) {
//		this.loginstatus = loginstatus;
//	}

	public Admin() {
		super();
	}

	public Admin(String password, String adminName) {
		super();
		this.password = password;
		this.adminName = adminName;
	}

	public Admin(int adminId, String password, String adminName) {
		super();
		this.adminId = adminId;
		this.password = password;
		this.adminName = adminName;
	}

	public Admin(int adminId, String password, String adminName, int attempts) {
		super();
		this.adminId = adminId;
		this.password = password;
		this.adminName = adminName;
		this.attempts = attempts;
	}

	public Admin(
			@NotEmpty @Size(min = 3, max = 6, message = "Admin Id should be between 3 to 6 characters") int adminId,
			@NotEmpty(message = "Password must not be empty") @Size(min = 8, max = 20, message = "Password should be between 2 and 8 characters") @Pattern(regexp = "^(?=.[a-z])(?=.\\d)(?=.[A-Z])(?=.[@#$%!]).{8,20}", message = "Password must contain atleast one upper case, one lower case a digit and a special character") String password,
			@NotEmpty(message = "AdminName must not be empty") String adminName, @Min(0) int attempts,
			List<Customer> customer) {
		super();
		this.adminId = adminId;
		this.password = password;
		this.adminName = adminName;
		this.attempts = attempts;
		this.customer = customer;
	}

//	public Admin(int adminId, String password, String adminName, int attempts, LoginStatus loginstatus) {
//		super();
//		this.adminId = adminId;
//		this.password = password;
//		this.adminName = adminName;
//		this.attempts = attempts;
//		this.loginstatus = loginstatus;
//	}
}
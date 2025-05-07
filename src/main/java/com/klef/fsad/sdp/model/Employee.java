package com.klef.fsad.sdp.model;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_table")
public class Employee {
	@Id
	@Column(name = "emp_id")
	private Long id;
	@Column(name="emp_name",nullable = false)
	private String name;
	@Column(name="emp_gender",nullable = false)
	private String gender;
	@Column(name="emp_age",nullable = false)
	private int age;
	@Column(name="emp_designation",nullable = false)
	private String designation;
	@Column(name="emp_dept",nullable = false)
	private String department;
	@Column(name="emp_salary",nullable = false)
	private double salary;
	@Column(name="emp_username",nullable = false,unique = true)
	private String username;
	@Column(name="emp_email",nullable = false,unique=true)
	private String email;
	@Column(name="emp_password",nullable = false)
	private String password;
	@Column(name="emp_contact",nullable = false,unique = true)
	private String contact;
	@Column(name="acc_status", nullable = false)
	private String accountstatus;
	@Column(nullable = false)
	private String role;
	@Column(nullable = false, name="emp_photo", columnDefinition = "LONGBLOB")
	private byte[] photo; 
	
	@OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
	private List<Leave> leave;
	
	@OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
	private List<Duty> duty;
	
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager manager;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public List<Leave> getLeave() {
		return leave;
	}
	public void setLeave(List<Leave> leave) {
		this.leave = leave;
	}
	public List<Duty> getDuty() {
		return duty;
	}
	public void setDuty(List<Duty> duty) {
		this.duty = duty;
	}
	
	public String getAccountstatus() {
		return accountstatus;
	}
	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", designation="
				+ designation + ", department=" + department + ", salary=" + salary + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", contact=" + contact + ", accountstatus="
				+ accountstatus + ", role=" + role + ", photo=" + Arrays.toString(photo) + ", leave=" + leave
				+ ", duty=" + duty + ", manager=" + manager + "]";
	}
}

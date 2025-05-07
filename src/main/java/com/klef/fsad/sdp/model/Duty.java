package com.klef.fsad.sdp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="duty_table")
public class Duty {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false,length=3000)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="emp_id")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager manager;
	
	@ManyToOne
	@JoinColumn(name = "assingedByManager")
	private Manager assingedByManager;
	
	@ManyToOne
	@JoinColumn(name = "assignedByAdmin")
	private Admin assingedByAdmin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Manager getAssingedByManager() {
		return assingedByManager;
	}

	public void setAssingedByManager(Manager assingedByManager) {
		this.assingedByManager = assingedByManager;
	}

	public Admin getAssingedByAdmin() {
		return assingedByAdmin;
	}

	public void setAssingedByAdmin(Admin assingedByAdmin) {
		this.assingedByAdmin = assingedByAdmin;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Duty [id=" + id + ", title=" + title + ", description=" + description + ", employee=" + employee
				+ ", manager=" + manager + ", assingedByManager=" + assingedByManager + ", assingedByAdmin="
				+ assingedByAdmin + "]";
	}
	
	
}

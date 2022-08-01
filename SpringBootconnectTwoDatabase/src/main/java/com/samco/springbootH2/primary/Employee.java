package com.samco.springbootH2.primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int empid;
	private String empname;
	private String empsalary;

	public Employee() {
		super();
	}

	public Employee(int empid, String empname, String empsalary) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.empsalary = empsalary;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getEmpsalary() {
		return empsalary;
	}

	public void setEmpsalary(String empsalary) {
		this.empsalary = empsalary;
	}

}

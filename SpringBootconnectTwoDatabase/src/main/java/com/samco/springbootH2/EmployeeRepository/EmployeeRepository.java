package com.samco.springbootH2.EmployeeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samco.springbootH2.primary.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}

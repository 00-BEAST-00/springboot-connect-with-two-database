package com.samco.springbootH2.ManagerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samco.springbootH2.secondary.Manager;
@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {

}

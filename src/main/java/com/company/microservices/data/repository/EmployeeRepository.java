package com.company.microservices.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.microservices.data.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

}

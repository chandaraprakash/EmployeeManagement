package com.company.microservices.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.microservices.data.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

	List<Employee> findEmployeeByDepartment(@Param("department") String department);

}

package com.example.jdbctemplate.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.jdbctemplate.model.Employee;
@Repository
public interface EmployeeReps {
    int count();
    Employee getById(long id);
    List<Employee>findAll();
    int insertEmployee(Employee employee);
    int updateEmployee(Employee employee);
    int deleteEmployee(Employee employee);

}

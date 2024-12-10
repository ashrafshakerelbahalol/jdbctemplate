package com.example.jdbctemplate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jdbctemplate.model.Employee;
import com.example.jdbctemplate.repository.EmployeeReps;
@RestController
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    @Qualifier("EmployeeRepParameteriziedJdbcTemplateImp")
    EmployeeReps employeeReps;
    
    @GetMapping("/count")
    public int countEmployee(){
        return employeeReps.count();
    }
    @GetMapping("/findAll")
    public List findAllEmployee(){
        return employeeReps.findAll();
    }
    @GetMapping("/find")
    public Employee findbyid(@RequestParam long id){
        return employeeReps.getById(id);
    }
    @PostMapping("/addemployee")
    public int addEmployee(@RequestBody Employee employee){
        return employeeReps.insertEmployee(employee);
    }
    @PostMapping("/updateemployee")
    public int updateEmployee(@RequestBody Employee employee){
        return employeeReps.updateEmployee(employee);
    }
    @PostMapping("/deleteemployee")
    public int deleteEmployee(@RequestBody Employee employee){
        return employeeReps.deleteEmployee(employee);
    }
}

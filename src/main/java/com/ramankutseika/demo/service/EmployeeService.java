package com.ramankutseika.demo.service;

import com.ramankutseika.demo.entity.Employee;
import com.ramankutseika.demo.entity.EmployeeStatus;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    public Optional<Employee> findById(Long id);

    public List<Employee> findAll();

    public Employee createEmployee(Employee employee);

    public void updateEmployeeStatus(Long id, EmployeeStatus status);
}

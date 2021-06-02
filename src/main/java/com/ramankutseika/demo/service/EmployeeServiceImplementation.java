package com.ramankutseika.demo.service;

import com.ramankutseika.demo.entity.Employee;
import com.ramankutseika.demo.entity.EmployeeStatus;
import com.ramankutseika.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void updateEmployeeStatus(Long id, EmployeeStatus status) {
        employeeRepository.updateEmployeeStatus(id, status);
    }

}

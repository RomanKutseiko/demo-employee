package com.ramankutseika.demo.controller;

import com.ramankutseika.demo.entity.Employee;
import com.ramankutseika.demo.entity.EmployeeStatus;
import com.ramankutseika.demo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1/employees")
public class EmployeeController {

    public static final String employeeFirstNameWarning = "Employee First Name is required";
    public static final String employeeNotFoundWarning = "No Employee with such Id found";

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        logger.info(String.format("Attempt to create employee: %s", employee.toString()));
        Optional.ofNullable(employee.getFirstName()).orElseThrow(() -> {
            logger.warn(employeeFirstNameWarning);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, employeeFirstNameWarning);
        });
        return employeeService.createEmployee(employee);
    }

    @PatchMapping(path = "/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeStatus(@PathVariable("id") Long id, @RequestParam("status") EmployeeStatus status) {
        logger.info(String.format("Attempt to update employee status: %s", status.toString()));
        Employee employee = employeeService.findById(id).orElseThrow(() -> {
            logger.warn(employeeNotFoundWarning);
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, employeeNotFoundWarning);
        });
        if (employee.getStatus().isStatusTransitionAvailable(status)) {
            employeeService.updateEmployeeStatus(id, status);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, String.format("You only allowed to switch status from %s to %s",
                    employee.getStatus().name(), employee.getStatus().availableStatusTransitionsToString()));
        }
    }
}

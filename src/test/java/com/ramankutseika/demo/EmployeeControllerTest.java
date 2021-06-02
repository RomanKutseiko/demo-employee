package com.ramankutseika.demo;

import com.ramankutseika.demo.controller.EmployeeController;
import com.ramankutseika.demo.entity.Employee;
import com.ramankutseika.demo.entity.EmployeeStatus;
import com.ramankutseika.demo.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;
    private EmployeeController employeeController;

    @Before
    public void setUp() {
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    public void getEmployeeList() {
        Mockito.when(employeeService.findAll()).thenReturn(List.of(getEmployee(), getEmployee()));
        Assertions.assertEquals(2, employeeController.findAll().size());
    }

    @Test
    public void createEmployeeWithoutName() {
        Employee employee = getEmployee();
        employee.setFirstName(null);
        Assertions.assertThrows(ResponseStatusException.class, () -> employeeController.createEmployee(employee));
    }

    @Test
    public void createEmployeeSuccess() {
        Assertions.assertDoesNotThrow(() -> employeeController.createEmployee(getEmployee()));
    }

    @Test
    public void updateEmployeeStatusEmployeeNotFound() {
        Mockito.when(employeeService.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResponseStatusException.class,
                () -> employeeController.updateEmployeeStatus(1L, EmployeeStatus.INCHECK));
    }

    @Test
    public void updateEmployeeStatusSwitchNotAllowed() {
        Mockito.when(employeeService.findById(Mockito.anyLong())).thenReturn(Optional.of(getEmployee()));
        Assertions.assertThrows(ResponseStatusException.class,
                () -> employeeController.updateEmployeeStatus(1L, EmployeeStatus.APPROVED));
    }

    @Test
    public void updateEmployeeStatusSuccess() {
        Mockito.when(employeeService.findById(Mockito.anyLong())).thenReturn(Optional.of(getEmployee()));
        Assertions.assertDoesNotThrow(() -> employeeController.updateEmployeeStatus(1L, EmployeeStatus.INCHECK));
    }

    private static Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(3L);
        employee.setFirstName("Alex");
        employee.setLastName("Alexandrov");
        employee.setStatus(EmployeeStatus.ADDED);
        employee.setContractInfo("Contract Info");
        employee.setDateOfBirth(Timestamp.valueOf("1980-01-01 00:00:00.000"));
        return employee;
    }
}

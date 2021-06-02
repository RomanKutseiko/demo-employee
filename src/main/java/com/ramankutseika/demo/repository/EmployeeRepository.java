package com.ramankutseika.demo.repository;

import com.ramankutseika.demo.entity.Employee;
import com.ramankutseika.demo.entity.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("update Employee e set e.status = :status where e.id = :id")
    void updateEmployeeStatus(Long id, EmployeeStatus status);
}

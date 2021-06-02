package com.ramankutseika.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contract_info")
    private String contractInfo;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;

    @Column(name = "status", nullable = false ,columnDefinition = "varchar(15) default 'ADDED' CHECK (status in ('ADDED', 'INCHECK', 'APPROVED', 'ACTIVE')) ")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status = EmployeeStatus.ADDED;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContractInfo() {
        return contractInfo;
    }

    public void setContractInfo(String contractInfo) {
        this.contractInfo = contractInfo;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
}

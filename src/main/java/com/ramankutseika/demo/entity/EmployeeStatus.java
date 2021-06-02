package com.ramankutseika.demo.entity;

import java.util.List;

public enum EmployeeStatus {

    ADDED("ADDED", null),
    INCHECK("IN-CHECK", null),
    APPROVED("APPROVED",null),
    ACTIVE("ACTIVE", null);

    private final String status;
    private List<EmployeeStatus> availableStatusChange;

    static {
        ADDED.availableStatusChange = List.of(INCHECK);
        INCHECK.availableStatusChange = List.of(ADDED, APPROVED);
        APPROVED.availableStatusChange = List.of(INCHECK, ACTIVE);
        ACTIVE.availableStatusChange = List.of(APPROVED);
    }

    EmployeeStatus(String status, List<EmployeeStatus> availableStatusChange) {
        this.status = status;
        this.availableStatusChange = availableStatusChange;
    }

    public Boolean isStatusTransitionAvailable(EmployeeStatus newStatus) {
        return this.equals(newStatus) ||this.availableStatusChange.stream().anyMatch(newStatus::equals);
    }

    public String availableStatusTransitionsToString() {
        return this.availableStatusChange.toString();
    }

}

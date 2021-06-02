package com.ramankutseika.demo;

import com.ramankutseika.demo.entity.EmployeeStatus;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static com.ramankutseika.demo.entity.EmployeeStatus.*;

@RunWith(Parameterized.class)
public class EmployeeStatusSwitcherTest {

    private final EmployeeStatus currentStatus;
    private final EmployeeStatus newStatus;

    public EmployeeStatusSwitcherTest(EmployeeStatus currentStatus, EmployeeStatus newStatus) {
        this.currentStatus = currentStatus;
        this.newStatus = newStatus;
    }

    @Parameterized.Parameters(name = "using currentStatus {0} and trying to switch to newStatus {1}")
    public static Collection<Object[]> statusInputs() {

        return List.of( new Object[][]{
                {ADDED, ADDED},
                {ADDED, INCHECK},
                {INCHECK, ADDED},
                {INCHECK, INCHECK},
                {INCHECK, APPROVED},
                {APPROVED, INCHECK},
                {APPROVED, APPROVED},
                {APPROVED, ACTIVE},
                {ACTIVE, APPROVED},
                {ACTIVE, ACTIVE}
        });
    }


    @Test
    public void successfulStatusSwitchesTest(){
        Assertions.assertTrue(currentStatus.isStatusTransitionAvailable(newStatus));
    }
}

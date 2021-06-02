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
public class EmployeeStatusSwitcherFailureTest {

    private final EmployeeStatus currentStatus;
    private final EmployeeStatus newStatus;

    public EmployeeStatusSwitcherFailureTest(EmployeeStatus currentStatus, EmployeeStatus newStatus) {
        this.currentStatus = currentStatus;
        this.newStatus = newStatus;
    }

    @Parameterized.Parameters(name = "using currentStatus {0} and trying to switch to newStatus {1}, should not allow")
    public static Collection<Object[]> badStatusInputs() {

        return List.of( new Object[][]{
                {ADDED, APPROVED},
                {ADDED, ACTIVE},
                {INCHECK, ACTIVE},
                {APPROVED, ADDED},
                {ACTIVE, ADDED},
                {ACTIVE, INCHECK}
        });
    }


    @Test
    public void failStatusSwitchesTest(){
        Assertions.assertFalse(currentStatus.isStatusTransitionAvailable(newStatus));
    }
}

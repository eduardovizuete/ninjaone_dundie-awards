package com.ninjaone.dundie_awards;

import com.ninjaone.dundie_awards.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class AwardsCache {

    @Autowired
    private EmployeeService employeeService;

    private int totalAwards;

    public void setTotalAwards(int totalAwards) {
        this.totalAwards = totalAwards;
    }

    public int getTotalAwards() {
        int total = employeeService.getAllEmployees()
                .stream()
                .mapToInt(employee -> Objects.requireNonNullElse(employee.getDundieAwards(), 0))
                .sum();
        this.totalAwards = total;
        return totalAwards;
    }

    public void addOneAward() {
        this.totalAwards += 1;
    }
}

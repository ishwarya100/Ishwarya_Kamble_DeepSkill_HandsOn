package com.example.ems.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;

// class based projection, populated via a constructor expression
@Getter
@AllArgsConstructor
public class EmployeeSummaryDTO {

    private String name;
    private String departmentName;

}

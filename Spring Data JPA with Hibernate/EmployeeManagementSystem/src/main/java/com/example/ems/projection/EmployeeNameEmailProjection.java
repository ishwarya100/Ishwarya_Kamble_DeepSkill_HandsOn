package com.example.ems.projection;

import org.springframework.beans.factory.annotation.Value;

// interface based projection, exposes only the fields it declares
public interface EmployeeNameEmailProjection {

    String getName();

    String getEmail();

    @Value("#{target.name + ' <' + target.email + '>'}")
    String getDisplayLabel();

}

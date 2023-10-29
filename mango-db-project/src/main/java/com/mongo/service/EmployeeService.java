package com.mongo.service;

import com.mongo.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(String employeeId);

    List<EmployeeDto> getAllEmployee();

    String deleteEmployeeById(String employeeId);

    EmployeeDto updateEmployee(String employeeId, EmployeeDto employeeDto);

    EmployeeDto getByEmployeeName(String employeeName);

}

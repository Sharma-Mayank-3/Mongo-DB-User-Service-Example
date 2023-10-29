package com.mongo.controller;

import com.mongo.dto.EmployeeDto;
import com.mongo.payload.ApiResponse;
import com.mongo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mongo/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createemployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employee = this.employeeService.createEmployee(employeeDto);
        ApiResponse build = ApiResponse.builder().message("employee-created").status(true).data(employee).build();
        return new ResponseEntity<>(build, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllEmployees(){
        List<EmployeeDto> allEmployee = this.employeeService.getAllEmployee();
        ApiResponse build = ApiResponse.builder().message("all employees").status(true).data(allEmployee).build();
        return new ResponseEntity<>(build, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> getEmployeeById(@PathVariable("employeeId") String employeeId){
        EmployeeDto employeeById = this.employeeService.getEmployeeById(employeeId);
        ApiResponse build = ApiResponse.builder().message("employee by id").status(true).data(employeeById).build();
        return new ResponseEntity<>(build, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("employeeId") String employeeId){
        String s = this.employeeService.deleteEmployeeById(employeeId);
        ApiResponse build = ApiResponse.builder().message("delete employee").status(true).data(s).build();
        return new ResponseEntity<>(build, HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable("employeeId") String employeeId,
                                                      @RequestBody EmployeeDto employeeDto
                                                      ){
        EmployeeDto employeeDto1 = this.employeeService.updateEmployee(employeeId, employeeDto);
        ApiResponse build = ApiResponse.builder().message("update employee").status(true).data(employeeDto1).build();
        return new ResponseEntity<>(build, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<ApiResponse> getByEmployeeName(
            @RequestParam(name = "employeeName", required = false) String employeeName
    ){
        EmployeeDto byEmployeeName = this.employeeService.getByEmployeeName(employeeName);
        ApiResponse build = ApiResponse.builder().message("search by employee Name").status(true).data(byEmployeeName).build();
        return new ResponseEntity<>(build, HttpStatus.OK);
    }

}

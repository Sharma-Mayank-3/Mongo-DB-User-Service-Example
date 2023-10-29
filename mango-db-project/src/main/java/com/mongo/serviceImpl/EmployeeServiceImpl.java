package com.mongo.serviceImpl;

import com.mongo.dto.EmployeeDto;
import com.mongo.exception.ResourceNotFoundException;
import com.mongo.model.Employee;
import com.mongo.repo.EmployeeRepository;
import com.mongo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee map = this.modelMapper.map(employeeDto, Employee.class);
        Employee save = this.employeeRepository.save(map);
        return this.modelMapper.map(save, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(String employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("employee", "employeeId", employeeId));
        return this.modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> all = this.employeeRepository.findAll();
        return all.stream().map(employee-> this.modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @Override
    public String deleteEmployeeById(String employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).get();
        this.employeeRepository.delete(employee);
        return "employee-deleted";
    }

    @Override
    public EmployeeDto updateEmployee(String employeeId, EmployeeDto employeeDto) {
        Employee employee = this.employeeRepository.findById(employeeId).get();
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmployeeAge(employeeDto.getEmployeeAge());
        Employee save = this.employeeRepository.save(employee);
        return this.modelMapper.map(save, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getByEmployeeName(String employeeName) {
        Employee byEmployeeName = this.employeeRepository.findByEmployeeName(employeeName);
        return this.modelMapper.map(byEmployeeName, EmployeeDto.class);
    }
}

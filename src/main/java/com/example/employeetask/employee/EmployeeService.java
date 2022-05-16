package com.example.employeetask.employee;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    Employee addEmployee(Employee employee){
        return employeeRepository.insert(employee);
    }

    List<Employee> getAllEmployees(){
        return employeeRepository.loadAllEmployees();
    }

    Optional<Employee> getEmployeeById(Long id){
        Employee employee = employeeRepository.loadEmployeeById(id);
        if(employee == null){
            return Optional.empty();
        }
        return Optional.of(employee);
    }

    void replaceEmployee(Long id, Employee employee) {
        if(employeeRepository.loadEmployeeById(id)==null){
            employeeRepository.insert(employee);
        } else{
            employeeRepository.update(id, employee);
        }
    }
}

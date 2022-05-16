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

    public Employee addEmployee(Employee employee){
        return employeeRepository.insert(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.loadAllEmployees();
    }

    public Optional<Employee> getEmployeeById(Long id){
        Employee employee = employeeRepository.loadEmployeeById(id);
        if(employee == null){
            return Optional.empty();
        }
        return Optional.of(employee);
    }

    public void replaceEmployee(Long id, Employee employee) {
        if(employeeRepository.loadEmployeeById(id) == null){
            employeeRepository.insert(employee);
        } else{
            employeeRepository.update(id, employee);
        }
    }

    public Optional<Employee> updateEmployee(Long id, Employee employee){
        Employee employeeToUpdate = employeeRepository.loadEmployeeById(id);
        if(employeeToUpdate == null){
            return Optional.empty();
        }

        if(employee.getDepartmentId() != null){
            employeeToUpdate.setDepartmentId(employee.getDepartmentId());
        }

        if(employee.getLastName() != null){
            employeeToUpdate.setLastName(employee.getLastName());
        }

        if(employee.getFirstName() != null){
            employeeToUpdate.setFirstName(employee.getFirstName());
        }

        if(employee.getJobTitle() != null){
            employeeToUpdate.setJobTitle(employee.getJobTitle());
        }

        employeeToUpdate.setId(id);
        employeeRepository.update(id, employeeToUpdate);
        return Optional.of(employeeToUpdate);
    }

    public void deleteById(Long id){
        employeeRepository.deleteById(id);
    }
}

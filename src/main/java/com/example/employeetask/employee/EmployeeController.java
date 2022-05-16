package com.example.employeetask.employee;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if(employee.isEmpty()){
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok(employee.get());
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @RequestBody Employee employee){
        employeeService.replaceEmployee(id, employee);
        return ResponseEntity.ok().build();

    }
}

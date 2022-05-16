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
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if(employee.isEmpty()){
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok(employee.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceEmployeeById(@PathVariable Long id, @RequestBody Employee employee){
        employeeService.replaceEmployee(id, employee);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEmployeeDetailsById(@PathVariable Long id, @RequestBody Employee employee){
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, employee);
        if(updatedEmployee.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

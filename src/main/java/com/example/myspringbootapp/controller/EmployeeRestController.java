package com.example.myspringbootapp.controller;

import com.example.myspringbootapp.entity.Employee;
import com.example.myspringbootapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.selectAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee selectEmployee(@PathVariable long id){
        return employeeService.selectEmployee(id);
    }

    @PostMapping
    public Employee insertEmployee(@RequestBody Employee employee){
        return employeeService.insertEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id,employee);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id){
        return employeeService.deleteEmployee(id);
    }

}

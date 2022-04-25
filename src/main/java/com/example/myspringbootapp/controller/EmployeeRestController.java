package com.example.myspringbootapp.controller;

import com.example.myspringbootapp.entity.Employee;
import com.example.myspringbootapp.exception.ResourceNotFoundException;
import com.example.myspringbootapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee selectEmployee(@PathVariable long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return employee;
    }

    @PostMapping
    public Employee insertEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employee){
        Employee optionalE = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee", "id", id));
        optionalE.setFirstName(employee.getFirstName());
        optionalE.setLastName(employee.getLastName());
        optionalE.setEmailId(employee.getEmailId());
        return employeeRepository.save(optionalE);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id+"Not Found");
        }
        Employee exist = employee.get();
        employeeRepository.delete(exist);
        return ResponseEntity.ok().build();
    }

}

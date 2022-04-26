package com.example.myspringbootapp.service;

import com.example.myspringbootapp.entity.Employee;
import com.example.myspringbootapp.exception.ResourceNotFoundException;
import com.example.myspringbootapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee insertEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public List<Employee> selectAllEmployee(){
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee selectEmployee(Long id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee existEmployee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
        return existEmployee;
    }

    public Employee updateEmployee(Long id, Employee employeeDetail){
        Employee existEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        existEmployee.setFirstName(employeeDetail.getFirstName());
        existEmployee.setLastName(employeeDetail.getLastName());
        existEmployee.setEmailId(employeeDetail.getEmailId());
        return existEmployee;
    }

    public ResponseEntity<?> deleteEmployee(Long id){
        Optional<Employee> optionalUser = employeeRepository.findById(id);
        if(!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " Employee Not Found ");
        }
        Employee existUser = optionalUser.get();
        employeeRepository.delete(existUser);
        return ResponseEntity.ok("Employee Delete OK");
    }

}

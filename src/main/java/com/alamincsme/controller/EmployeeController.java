package com.alamincsme.controller;

import com.alamincsme.domain.EmployeeDTO;
import com.alamincsme.exception.EmployeeNotFoundException;
import com.alamincsme.model.Employee;
import com.alamincsme.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService ;

    @GetMapping("/admin/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        var employeeDTOs = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeDTOs, HttpStatus.FOUND);
    }

    @GetMapping("/admin/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        var employeeDTO = employeeService.getEmployee(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.FOUND);
    }

    @PostMapping("/admin/employee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody Employee employee){
        var employeeDTO = employeeService.addEmployee(employee);
        return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/admin/employee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody Employee employee,@PathVariable Long id) throws EmployeeNotFoundException {
        var employeDTO = employeeService.updateEmployee(id , employee);
        return new ResponseEntity<>(employeDTO, HttpStatus.OK);
    }


    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        var status = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(status, HttpStatus.OK);

    }
}

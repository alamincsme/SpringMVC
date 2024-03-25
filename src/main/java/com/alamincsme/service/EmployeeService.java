package com.alamincsme.service;

import com.alamincsme.exception.EmployeeNotFoundException;
import com.alamincsme.domain.EmployeeDTO;
import com.alamincsme.model.Employee;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDTO> getAllEmployee();
    public EmployeeDTO getEmployee(Long id) throws EmployeeNotFoundException;
    public EmployeeDTO addEmployee(Employee employee);
    public EmployeeDTO updateEmployee(Long id, Employee employee) throws EmployeeNotFoundException;
    public String deleteEmployee(Long id) throws EmployeeNotFoundException;
}

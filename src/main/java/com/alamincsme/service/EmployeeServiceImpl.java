package com.alamincsme.service;

import com.alamincsme.exception.EmployeeNotFoundException;
import com.alamincsme.model.Employee;
import com.alamincsme.repository.EmployeeRepo;
import com.alamincsme.domain.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo ;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDTO> employeeDTOs =  employees
                .stream()
                .map(employee->modelMapper.map(employee, EmployeeDTO.class))
                .toList();
        return employeeDTOs ;
    }

    @Override
    public EmployeeDTO getEmployee(Long id) throws EmployeeNotFoundException {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee does not exist!!"));
        var employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return employeeDTO ;

    }

    @Override
    public EmployeeDTO addEmployee(Employee employee) {
        Employee saveEmployee = employeeRepo.save(employee);
        return modelMapper.map(saveEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, Employee employee) throws EmployeeNotFoundException {
        Employee employeeDB = employeeRepo
                .findById(id).orElseThrow(
                        () -> new EmployeeNotFoundException("Employee does not exist with " + id ));

        if (employeeDB == null) {
            throw  new RuntimeException("Employee must not be null");
        }

        employee.setId(employeeDB.getId());
        Employee saveEmplyee = employeeRepo.save(employee);
        return modelMapper.map(saveEmplyee, EmployeeDTO.class);


    }

    @Override
    public String deleteEmployee(Long id) throws EmployeeNotFoundException {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee does not exist !!"));

        employeeRepo.deleteById(id);
        return "Employee deleted successfully with id " + id ;
    }
}

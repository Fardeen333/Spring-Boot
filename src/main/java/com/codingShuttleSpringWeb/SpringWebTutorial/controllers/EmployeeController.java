package com.codingShuttleSpringWeb.SpringWebTutorial.controllers;

import com.codingShuttleSpringWeb.SpringWebTutorial.dto.EmployeeDTO;
import com.codingShuttleSpringWeb.SpringWebTutorial.entities.EmployeeEntity;
import com.codingShuttleSpringWeb.SpringWebTutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")

public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(path = "")
    public List<EmployeeDTO> getEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return employeeService.getEmployees();
    }

    @PostMapping(path = "")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO payload){
        return employeeService.createEmployee(payload);
    }
}

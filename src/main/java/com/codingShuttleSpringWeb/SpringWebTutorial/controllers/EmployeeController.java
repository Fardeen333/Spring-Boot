package com.codingShuttleSpringWeb.SpringWebTutorial.controllers;

import com.codingShuttleSpringWeb.SpringWebTutorial.dto.EmployeeDTO;
import com.codingShuttleSpringWeb.SpringWebTutorial.entities.EmployeeEntity;
import com.codingShuttleSpringWeb.SpringWebTutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")

public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeBtId(@PathVariable(name = "employeeId") Long id) {
        // return new EmployeeDTO(id, "Fardeen", "fardeen@gmail.com", 26, LocalDate.of(2025, 12, 31), true);
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping(path = "")
    public List<EmployeeEntity> getEmployeeByFilters(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
//        return "This is the filters : " + age + " " + sortBy;
        return this.employeeRepository.findAll();
    }

    @PostMapping(path = "")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity payload){
//        payload.setId(100L);
//        return payload;
        return this.employeeRepository.save(payload);
    }
}

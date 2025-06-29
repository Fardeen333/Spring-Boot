package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeBtId(@PathVariable(name = "employeeId") Long id) {
        return new EmployeeDTO(id, "Fardeen", "fardeen@gmail.com", 26, LocalDate.of(2025, 12, 31), true);
    }

    @GetMapping(path = "")
    public String getEmployeeByFilters(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return "This is the filters : " + age + " " + sortBy;
    }

    @PostMapping(path = "")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO payload){
        payload.setId(100L);
        return payload;
    }
}

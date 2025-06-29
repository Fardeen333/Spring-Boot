package com.codingShuttleSpringWeb.SpringWebTutorial.controllers;

import com.codingShuttleSpringWeb.SpringWebTutorial.dto.EmployeeDTO;
import com.codingShuttleSpringWeb.SpringWebTutorial.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")

public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(item -> ResponseEntity.ok(item))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping(path = "")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO payload){
        EmployeeDTO savedEmployee = employeeService.createEmployee(payload);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatedEmployeeById(@PathVariable Long employeeId ,@RequestBody EmployeeDTO payload){
        return ResponseEntity.ok(employeeService.updatedEmployeeById(employeeId, payload));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean isDeleted = employeeService.deleteEmployeeById(employeeId);
        if(isDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatedPartialEmployeeById(@PathVariable Long employeeId ,@RequestBody Map<String, Object> payload){
        EmployeeDTO patchedEmployee =  employeeService.updatedPartialEmployeeById(employeeId, payload);
        if(patchedEmployee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(patchedEmployee);
    }
}

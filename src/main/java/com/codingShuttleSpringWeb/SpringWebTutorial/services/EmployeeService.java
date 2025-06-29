package com.codingShuttleSpringWeb.SpringWebTutorial.services;

import com.codingShuttleSpringWeb.SpringWebTutorial.dto.EmployeeDTO;
import com.codingShuttleSpringWeb.SpringWebTutorial.entities.EmployeeEntity;
import com.codingShuttleSpringWeb.SpringWebTutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if(employeeEntity == null){
            throw new RuntimeException("Employee not found with id: " + id);
        }
        // one way
        //return new EmployeeDTO(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getEmail(), employeeEntity.getAge(), employeeEntity.getDateOfJoining(),employeeEntity.getIsActive());

        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getEmployees() {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map((employeeEntity) -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO createEmployee(EmployeeDTO payload) {
        EmployeeEntity toSaveEntity = modelMapper.map(payload, EmployeeEntity.class);
        EmployeeEntity employeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }
}

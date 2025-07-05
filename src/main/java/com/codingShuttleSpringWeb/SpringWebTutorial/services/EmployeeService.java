package com.codingShuttleSpringWeb.SpringWebTutorial.services;

import com.codingShuttleSpringWeb.SpringWebTutorial.dto.EmployeeDTO;
import com.codingShuttleSpringWeb.SpringWebTutorial.entities.EmployeeEntity;
import com.codingShuttleSpringWeb.SpringWebTutorial.exceptions.MyResourceNotFoundException;
import com.codingShuttleSpringWeb.SpringWebTutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);

        return employeeEntity.map((item) -> modelMapper.map(item, EmployeeDTO.class));
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

    public EmployeeDTO updatedEmployeeById(Long employeeId, EmployeeDTO payload) {
        boolean isExists = isExistsByEmployeeId(employeeId);
        if(!isExists) throw new MyResourceNotFoundException("Employee With id : " + employeeId + " does not exists.");
        EmployeeEntity employeeEntity = modelMapper.map(payload, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedResult = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedResult, EmployeeDTO.class);

    }

    public boolean isExistsByEmployeeId(Long id){
        return employeeRepository.existsById(id);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean isExist = employeeRepository.existsById(employeeId);
        if(!isExist) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatedPartialEmployeeById(Long employeeId, Map<String, Object> payload) {
        boolean isExists = employeeRepository.existsById(employeeId);
        if(!isExists) return null;

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        payload.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}

package com.codingShuttleSpringWeb.SpringWebTutorial.repositories;

import com.codingShuttleSpringWeb.SpringWebTutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}

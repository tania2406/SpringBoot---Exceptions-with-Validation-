package com.example.Springboot_ExceptionwithValidation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	List<Employee> findByCity(@Size(min = 3, message = "city must be more than 3 letter") String city);

	List<Employee> findByAge(@Valid @Min(30) int age);

}

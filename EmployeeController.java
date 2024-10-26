package com.example.Springboot_ExceptionwithValidation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Validated // for method level validation 
@RestController
public class EmployeeController
{
	@Autowired
	EmployeeRepository erepo;
	@RequestMapping("/test")
	public String test()
	{
		return " this is test for exception with validation";
	}
	@RequestMapping("/save")
	public String save (@Valid @RequestBody Employee employee) // @valid for object validation
	{ // without @Valid it gives bean validation error ConstrainViolationException
		//with @valid MethodArgumentNotValidException in postman
		
		erepo.save(employee);
		return "data is saved";
	}
	@RequestMapping("/all")
	public List<Employee> show()
	{
		return erepo.findAll();
	}
	@RequestMapping("/{id}")
	public Optional<Employee>byid(@PathVariable int id)
	{
		return erepo.findById(id);
	}
	@RequestMapping("/city/{city}")
	public List<Employee> bycity(@PathVariable  @Size(min=3,message="city must be more than 3 letter") String city)
	{     // there is no entity level constraint 
		//ConstraintViolationException with @Validated check at the top 
		return erepo.findByCity(city);
	}
	@RequestMapping("/by/{age}")// ConstraintViolationException: into postman if age<30 OK running 
	public List<Employee> byage(@Valid @PathVariable  @Min(30) int age)
	{     // there is no entity level constraint 
		//ConstraintViolationException with @Validated check at the top 
		return erepo.findByAge(age);
	}
	@RequestMapping("/del/{id}")
	public String delById(@PathVariable int id)
	{
		erepo.deleteById(id);
		return "data has been deleted";
		
	}
	
}

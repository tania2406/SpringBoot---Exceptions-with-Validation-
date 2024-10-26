package com.example.Springboot_ExceptionwithValidation;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity(name="employee_info")
public class Employee 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@NotNull(message="name Can't be null")
	@NotBlank(message="name can't be blank")
	String name;
	@Min(18)
	@Max(60)
	@NotNull(message="age must be verified")
	@Digits(message="only digits can be",fraction=0,integer=2)
	int age;
	@NotNull
	@NotBlank
	String city;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

}

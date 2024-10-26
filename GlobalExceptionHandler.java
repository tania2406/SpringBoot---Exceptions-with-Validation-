package com.example.Springboot_ExceptionwithValidation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex)
	{
		Map<String,String> errors =new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error ->
		{
			String fieldName=((FieldError) error).getField();
			String errorMessage= error.getDefaultMessage();
			errors.put(fieldName,errorMessage);
		});
		return new ResponseEntity<>(errors ,HttpStatus.BAD_REQUEST);
	}
	 @ExceptionHandler(DataIntegrityViolationException.class)
	    public ResponseEntity<?> handleUniqueDataEntry(DataIntegrityViolationException exception, WebRequest request){
	        String errorMessage="City name not allowed";
	        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	    }
	 @ExceptionHandler(ConstraintViolationException.class)
	 public ResponseEntity<Object> handleConstraintVoilationException(ConstraintViolationException exception)
	 {
		 Map<String,String> errors=new HashMap<>();
		 exception.getConstraintViolations().forEach(voilation ->
		 {
			 String propertyPath=voilation.getPropertyPath().toString();
			 String message =voilation.getMessage();
			 errors.put(propertyPath,message);
			 });
		 return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	 }
}

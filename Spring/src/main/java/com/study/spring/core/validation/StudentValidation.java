package com.study.spring.core.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name is empty");
		Student stu = (Student) target;
		if(stu.getId() <= 0){
			errors.rejectValue("id", "id < 1");
		}
		
	}

}

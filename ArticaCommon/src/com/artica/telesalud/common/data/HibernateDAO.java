package com.artica.telesalud.common.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;


public abstract class HibernateDAO{
	
	
	public HibernateDAO(String configFile) {
		super();
	}
	
	public HibernateDAO() {
		super();
	}
	public Session getSession(){
		return HibernateSessionFactoryManager.getInstance().getSession();
	}
	
	public Session getNewSession(){
	
		return HibernateSessionFactoryManager.getInstance().getNewSession();
	}

	public static <T> List<DataConstraintViolation> validate(T entity){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<T>> constraintViolations = validator.validate((T)entity);

		List<DataConstraintViolation> violations = new ArrayList<DataConstraintViolation>();
		
		for(ConstraintViolation<T> violation : constraintViolations){
			
			violations.add(new DataConstraintViolation(violation.getPropertyPath().toString(), violation.getMessageTemplate()));			
		}
		
		return violations;
	}
}

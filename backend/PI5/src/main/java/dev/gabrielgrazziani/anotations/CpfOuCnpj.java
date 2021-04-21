package dev.gabrielgrazziani.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import dev.gabrielgrazziani.validator.CpfCnpjValidator;

@Constraint(validatedBy = CpfCnpjValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfOuCnpj {

	String message() default "Deve ser um CPF ou CNPJ valido";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

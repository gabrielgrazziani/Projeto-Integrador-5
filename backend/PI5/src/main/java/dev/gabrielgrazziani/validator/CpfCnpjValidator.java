package dev.gabrielgrazziani.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dev.gabrielgrazziani.anotations.CpfOuCnpj;

public class CpfCnpjValidator implements ConstraintValidator<CpfOuCnpj, String>{

	private Pattern padraoCNPJ = Pattern.compile("[0-9]{14}");
	private Pattern padraoCPF = Pattern.compile("[0-9]{11}");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null || value.trim().equals("")) {
			return true;
		}
		
		String valueNumeros = value.replaceAll("[^\\d]", "");	
		
		return eUmCpfValido(valueNumeros) || eUmCnpjValido(valueNumeros);
	}

	private boolean eUmCnpjValido(String valueNumeros) {
		if(padraoCNPJ.matcher(valueNumeros).matches()) {
			
			int soma = 0;
			int peso = 2;
			for(int i = 11;i >= 0;i--) {
				soma += (valueNumeros.charAt(i) - 48) * peso;
				peso++;
				if(peso > 9) peso = 2;
			}
			int primeiroVerificador = soma % 11;
			if(primeiroVerificador <= 1) {
				primeiroVerificador = 0;
			}else {
				primeiroVerificador = 11 - primeiroVerificador;
			}
			
			soma = 0;
			peso = 2;
			for(int i = 12;i >= 0;i--) {
				soma += (valueNumeros.charAt(i) - 48) * peso;
				peso++;
				if(peso > 9) peso = 2;
			}
			int segundoVerificador = soma % 11;
			if(segundoVerificador <= 1) {
				segundoVerificador = 0;
			}else {
				segundoVerificador = 11 - segundoVerificador;
			};
			
			String verificadores = primeiroVerificador + "" + segundoVerificador;
			return valueNumeros.substring(12, 14).equals(verificadores);
			
		}
		return false;
	}
	
	private boolean eUmCpfValido(String valueNumeros) {
		if(padraoCPF.matcher(valueNumeros).matches()) {
			
			int soma = 0;
			for(int i = 0;i < 9;i++) {
				soma += (valueNumeros.charAt(i) - 48) * (10 - i);
			}
			int primeiroVerificador = 11 - (soma % 11);
			primeiroVerificador = primeiroVerificador >= 10 ? 0 : primeiroVerificador;
			
			soma = 0;
			for(int i = 0;i < 10;i++) {
				soma += (valueNumeros.charAt(i) - 48) * (11 - i);
			}
			int segundoVerificador = 11 - (soma % 11);
			segundoVerificador = segundoVerificador >= 10 ? 0 : segundoVerificador;
			
			String verificadores = primeiroVerificador + "" + segundoVerificador;
			return valueNumeros.substring(9, 11).equals(verificadores);
			
		}
		return false;
	}

}

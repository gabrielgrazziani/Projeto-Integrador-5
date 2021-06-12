package dev.gabrielgrazziani.model;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil implements GrantedAuthority{
	FUNCIONARIO,CLIENTE;

	@Override
	public String getAuthority() {
		return "ROLE_" + this.toString();
	}
}

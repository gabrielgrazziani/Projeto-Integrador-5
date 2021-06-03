package dev.gabrielgrazziani.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String cpfCnpj;

	@Pattern(regexp = "^(\\(\\d{2}\\)\\d{4}\\-\\d{4})|(\\(\\d{2}\\)\\d{5}\\-\\d{4})", message = "Telefone deve ter o formato= (99)9999-9999 ou (99)99999-9999")
	private String telefone;

	@NotNull
	@Email
	private String email;

	private String funcao;
	
	@NotNull
	@Size(min = 6, max = 20)
	private String login;

	@NotNull
	@Pattern(regexp = "(?=^.{6,}$)((?=.*\\d)(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "A senha deve conter 1 letra Maiúscula, números e caracteres especiais e no mínimo 6 caracteres!")
	private String senha;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Perfil perfil;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(this.perfil);
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
	
	
}

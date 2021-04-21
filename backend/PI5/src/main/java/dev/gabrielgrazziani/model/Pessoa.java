package dev.gabrielgrazziani.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//@Entity
//@Table(name = "pessoa")
//public class Pessoa {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "pes_id")
//	private Long id;
//	
//	@Column(name = "pes_nome")
//	@NotNull
//	private String nome;
//	
//	@Column(name = "pes_cpf_cnpj")
//	@NotNull
//	private String cpfCnpj;
//
//	@Column(name = "pes_telefone")
//	@Pattern(regexp = "^(\\(\\d{2}\\)\\d{4}\\-\\d{4})|(\\(\\d{2}\\)\\d{5}\\-\\d{4})", message = "Telefone deve ter o formato= (99)9999-9999 ou (99)99999-9999")
//	private String telefone;
//
//	@Column(name = "pes_email")
//	@NotNull
//	@Email
//	private String email;
//
//	@Column(name = "pes_funcao")
//	private String funcao;
//	
//	@Column(name = "pes_login")
//	@NotNull
//	@Size(min = 6, max = 20)
//	private String login;
//
//	@Column(name = "pes_senha")
//	@NotNull
//	@Pattern(regexp = "(?=^.{6,}$)((?=.*\\d)(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "A senha deve conter 1 letra Maiúscula, números e caracteres especiais e no mínimo 6 caracteres!")
//	private String senha;
//	/*
//		/^
//		  (?=.*\d)              // deve conter ao menos um dígito
//		  (?=.*[A-Z])           // deve conter ao menos uma letra maiúscula
//		  (?=.*[$*&@#])         // deve conter ao menos um caractere especial
//		$/	
//	*/
//	
//	
//	@Column(name = "pes_perfil")
//	@NotNull
//	@Pattern(regexp = "Interno|Externo")
//	private String perfil;
//
//	
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getCpfCnpj() {
//		return cpfCnpj;
//	}
//
//	public void setCpfCnpj(String cpfCnpj) {
//		this.cpfCnpj = cpfCnpj;
//	}
//
//	public String getTelefone() {
//		return telefone;
//	}
//
//	public void setTelefone(String telefone) {
//		this.telefone = telefone;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getFuncao() {
//		return funcao;
//	}
//
//	public void setFuncao(String funcao) {
//		this.funcao = funcao;
//	}
//
//	public String getLogin() {
//		return login;
//	}
//
//	public void setLogin(String login) {
//		this.login = login;
//	}
//
//	public String getSenha() {
//		return senha;
//	}
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
//
//	public String getPerfil() {
//		return perfil;
//	}
//
//	public void setPerfil(String perfil) {
//		this.perfil = perfil;
//	}
//	
//	
//}

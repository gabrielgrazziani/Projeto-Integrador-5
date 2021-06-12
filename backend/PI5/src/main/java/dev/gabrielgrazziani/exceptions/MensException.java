package dev.gabrielgrazziani.exceptions;

public class MensException {
	
	private String mensUsuarioFinal;
	private String mensDesenvolvedor;
	private String campo;
	
	public MensException(String mensUsuarioFinal, String mensDesenvolvedor,String campo) {
		this.mensUsuarioFinal = mensUsuarioFinal;
		this.mensDesenvolvedor = mensDesenvolvedor;
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}

	public String getMensUsuarioFinal() {
		return mensUsuarioFinal;
	}
	public String getMensDesenvolvedor() {
		return mensDesenvolvedor;
	}

}

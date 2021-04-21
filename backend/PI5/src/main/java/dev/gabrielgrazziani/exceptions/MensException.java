package dev.gabrielgrazziani.exceptions;

public class MensException {
	
	private String mensUsuarioFinal;
	private String mensDesenvolvedor;
	
	public MensException(String mensUsuarioFinal, String mensDesenvolvedor) {
		this.mensUsuarioFinal = mensUsuarioFinal;
		this.mensDesenvolvedor = mensDesenvolvedor;
	}

	public String getMensUsuarioFinal() {
		return mensUsuarioFinal;
	}
	public String getMensDesenvolvedor() {
		return mensDesenvolvedor;
	}

}

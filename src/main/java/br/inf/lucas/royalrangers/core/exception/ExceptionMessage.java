package br.inf.lucas.royalrangers.core.exception;

import java.util.List;

public class ExceptionMessage {

	private List<String> mensagens;
	
	public ExceptionMessage(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public List<String> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
}

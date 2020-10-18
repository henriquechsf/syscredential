package me.henrique.syscredential.domain.exception;

public class RecursoNaoEncontradoException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(String msg) {
		super(msg);
	}
}

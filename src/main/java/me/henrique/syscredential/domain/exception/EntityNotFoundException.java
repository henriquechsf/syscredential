package me.henrique.syscredential.domain.exception;

public class EntityNotFoundException extends DomainException {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String msg) {
		super(msg);
	}
}

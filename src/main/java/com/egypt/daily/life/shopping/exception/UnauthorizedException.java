package com.egypt.daily.life.shopping.exception;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2750857782930296370L;
	
	protected static MessageSourceAccessor accessor = SpringSecurityMessageSource.getAccessor();
	public UnauthorizedException() {
		super(accessor.getMessage("AbstractAccessDecisionManager.accessDenied","Access is denied"));
	}
	public UnauthorizedException(String message) {
		super(message);
	}
}

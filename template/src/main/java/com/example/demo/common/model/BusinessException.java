package com.example.demo.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1570498302611499442L;
	
	// 에러 분류
	private String type;
	// 에러 코드. 우선은 json 형태. 이것도 어딘가 정리를 해야 하는데...
	private String errorCode;
	// 에러 메시지
	private String message;
	
	public BusinessException() {
	}
	
	@Builder
	public BusinessException(String type, String errorCode, String message) {
		this.type = type;
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public enum Type {

		// 로그인,
		LOGIN,
		ACCESS_TOKEN,
		REFRESH_TOKEN,
		// 사용자
		USER
	}
}

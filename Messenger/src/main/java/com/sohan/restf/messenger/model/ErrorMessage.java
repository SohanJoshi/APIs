package com.sohan.restf.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String message;
	private int erroCode;
	private String documentation;
	
	public ErrorMessage() {
		
	}

	public ErrorMessage(String message, int erroCode, String documentation) {
		this.message = message;
		this.erroCode = erroCode;
		this.documentation = documentation;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErroCode() {
		return erroCode;
	}
	public void setErroCode(int erroCode) {
		this.erroCode = erroCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	
}

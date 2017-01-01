package com.karki.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMesssage;
	private int errorCode;
	private String documentation;

	public ErrorMessage() {
	}

	public ErrorMessage(String errorMesssage, int errorCode, String documentation) {
		super();
		this.errorMesssage = errorMesssage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}

	public String getErrorMesssage() {
		return errorMesssage;
	}

	public void setErrorMesssage(String errorMesssage) {
		this.errorMesssage = errorMesssage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

}

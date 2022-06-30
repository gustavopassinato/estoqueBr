package br.com.estoqueBr.model.dto;

public class ErroDto {

	private String fieldErrorName;
	private String message;
	private String errorFieldContent;

	public ErroDto(String message, String content, String fieldErrorName) {
		this.fieldErrorName = fieldErrorName;
		this.message = message;
		this.errorFieldContent = content;
	}

	public String getFieldErrorName() {
		return fieldErrorName;
	}

	public void setFieldErrorName(String fieldErrorName) {
		this.fieldErrorName = fieldErrorName;
	}

	public String getErrorFieldContent() {
		return errorFieldContent;
	}

	public void setErrorFieldContent(String errorFieldContent) {
		this.errorFieldContent = errorFieldContent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

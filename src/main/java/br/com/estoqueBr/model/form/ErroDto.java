package br.com.estoqueBr.model.form;

public class ErroDto {

	private String message;
	private String errorFieldContent;

	public ErroDto(String message, String content) {
		this.message = message;
		this.errorFieldContent = content;
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

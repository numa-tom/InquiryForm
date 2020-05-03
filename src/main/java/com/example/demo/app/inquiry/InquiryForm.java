package com.example.demo.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InquiryForm {
	
	@NotNull(message="記入必須です。")
	@Size(min = 1, max = 20, message = "20文字以内で入力してください。")
	private String name;
	
	@NotNull(message="記入必須です。")
	@Email(message = "アドレス形式で入力してください。")
	private String email;
	
	@NotNull(message="記入必須です。")
	private String contents;
	
	
	public InquiryForm() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	

}

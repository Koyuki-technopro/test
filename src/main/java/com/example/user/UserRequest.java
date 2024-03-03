package com.example.user;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest implements Serializable {

	@NotBlank(message = "ユーザー名を入力してください")
	@Size(min = 1, max = 45, message = "ユーザー名は1~45文字以内で入力してください")
	private String name;

	@NotBlank(message = "メールアドレスを入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", message = "規定外のメールアドレス形式です")
	private String email;

	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 4, max = 8, message = "パスワードは4~8文字以内で入力してください")
	@Pattern(regexp = "^([0-9]{4,8}|[a-zA-Z]{4,8})$", message = "使用できない文字列が含まれています。半角英数字で入力してください")
	private String password;

	@NotBlank(message = "パスワード（再確認）を入力してください")
	@Size(min = 4, max = 8, message = "パスワード（再確認）は4~8文字以内で入力してください")
	@Pattern(regexp = "^([0-9]{4,8}|[a-zA-Z]{4,8})$", message = "使用できない文字列が含まれています。半角英数字で入力してください")
	private String confirmPassword;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}

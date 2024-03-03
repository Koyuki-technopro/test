package com.example.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	private UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/loginForm")
	public String getLogin() {
		return "loginForm";
	}

	/*
	 * ユーザー新規登録画面を表示
	 * @param model Model
	 * @return 新規登録画面
	 * */
	@GetMapping("/registration")
	public String userCreate(Model model) {
		model.addAttribute("userRequest", new UserRequest());
		return "registration";

	}

	/*
	 * ユーザー新規登録
	 * @param userRequest リクエストデータ
	 * @param model Model
	 * @return 問題集一覧画面（問題集一覧画面が完成後に遷移先を変更する）
	 * */
	@PostMapping("/registration")
	public String registration(
			Model model,
			@ModelAttribute @Validated UserRequest userRequest,
			BindingResult result,
			HttpServletRequest request) {

		// 入力値がエラーの場合、エラーメッセージを表示する
		if (result.hasErrors()) {
			model.addAttribute(userRequest);
			return "registration";
		}

		// メールアドレスの重複チェック
		if (this.userService.emailDuplicate(userRequest.getEmail())) {
			model.addAttribute("emailExistsError", "既に使用されているメールアドレスです。");
			return "registration";
		}

		// パスワードと再確認パスワードが一致するか検証
		if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
			model.addAttribute("validationErrorPassword", "パスワードとパスワード（再確認）が一致しません。");
			return "registration";
		}

		// ユーザー情報登録後、問題集一覧画面へ遷移する。（問題集一覧画面が完成後に遷移先を変更する）
		userService.create(userRequest, request);
		return "redirect:/debugger";
	}
}

package com.example.explanation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.question.QuestionService;
import com.example.user.LoginUser;

@Controller
public class ExplanationController {
	
	private ExplanationService explanationService;

	public ExplanationController(ExplanationService explanationService,QuestionService questionService) {
		this.explanationService = explanationService;
	}
	/**
	 * 問題新規登録画面を表示する
	 * */
	@GetMapping("/question/create")
	public String questionCreate(Model model) {
		ExplanationRequest explanationRequest = new ExplanationRequest();
		explanationRequest.setanswer(1);
		model.addAttribute("explanationRequest", explanationRequest);
		return "question/questionCreate";
	}
	
	@PostMapping("/question/create")
	public String registration(Model model,@ModelAttribute @Validated ExplanationRequest explanationRequest,
			@AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = loginUser.getUser().getId();
		model.addAttribute(explanationRequest);
		explanationService.create(explanationRequest,userId);
		return "redirect:/question/create";
	}
}

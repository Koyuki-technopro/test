package com.example.review;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.question.QuestionService;

@Controller
public class ReviewController {
	private final ReviewService reviewService;
	private QuestionService questionService;

	public ReviewController(ReviewService reviewService, QuestionService questionService) {
		this.reviewService = reviewService;
		this.questionService = questionService;
	}

	@GetMapping("/score")
	public String score(Model model) {
		Integer correctAnswers = this.reviewService.correctAnswers;
		int totalQuestions = this.questionService.totalQuestions;

		model.addAttribute("correctAnswers", correctAnswers);
		model.addAttribute("totalQuestions", totalQuestions);
		System.out.println("正解数：" + correctAnswers);
		System.out.println("総問題数：" + totalQuestions);

		return "score";
	}

	@PostMapping("/score")
	@ResponseBody
	public Map<String, Object> createReview(@RequestBody ReviewForm reviewForm) {
		Map<String, Object> response = new HashMap<>();
		boolean incorrectAnswer = reviewService.checkAnswer(reviewForm.getQuestionId(), reviewForm.getSelectedAnswer());

		// 不正解の時だけREVIEWSテーブルに保存する
		if (!incorrectAnswer) {
			reviewService.saveReview(reviewForm.getUserId(), reviewForm.getQuestionId());
			response.put("result", "incorrect");
		} else {
			response.put("result", "correct");
		}

		// JSONで受け取れる形式で返す
		return response;
	}

}

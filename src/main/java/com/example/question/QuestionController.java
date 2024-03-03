package com.example.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.category.Category;
import com.example.category.CategoryService;
import com.example.explanation.Explanation;
import com.example.explanation.ExplanationService;
import com.example.user.LoginUser;

@Controller
public class QuestionController {
	private final QuestionService questionService;
	private final CategoryService categoryService;
	private final ExplanationService explanationService;

	@Autowired
	public QuestionController(
			QuestionService questionService,
			CategoryService categoryService,
			ExplanationService explanationService) {
		this.questionService = questionService;
		this.categoryService = categoryService;
		this.explanationService = explanationService;
	}

	@GetMapping("/question/{userId}/{categoryId}/{questionsId}")
	public String showQuestion(
			@PathVariable Integer questionsId,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId,
			Model model) {
		Question question = this.questionService.findByQuestion(questionsId);
		List<Explanation> explanations = this.explanationService.findByQestionsId(questionsId);
		int allQuestion = this.questionService.allQuestion(userId, categoryId);
		Integer nextQuestionId = this.questionService.findNextQuestionId(questionsId, userId, categoryId);
		model.addAttribute("question", question);
		model.addAttribute("explanations", explanations);
		model.addAttribute("nextQuestionId", nextQuestionId);

		// 中身を確認するために作成
		System.out.println("総問題数：" + allQuestion);
		System.out.println("問題文：" + question.getName());
		explanations.forEach(explanation -> System.out.println("選択肢：" + explanation.getContent()));
		System.out.println("次の問題のID：" + nextQuestionId);
		System.out.println("ユーザーID：" + question.getUserId());
		System.out.println("問題ID：" + question.getId());

		return "question/question";
	}

	/**
	 * 問題編集画面を表示する
	 * */
	@GetMapping("/question/edit/{id}")
	public String questionEdit() {

		return "question/questionEdit";
	}

	/**
	 * ユーザーIDと問題集IDに紐づく問題文と問題集名を表示する
	 * @param user_id ユーザーID
	 * @param category_id 問題集ID
	 * @param model
	 * @return 問題一覧画面
	 * */
	@GetMapping("/question/list/{userId}/{categoryId}")
	public String showQuestions(@PathVariable Integer userId, @PathVariable Integer categoryId,
			Model model) {
		List<Question> questions = questionService.findByUserIdAndCategoryId(userId, categoryId);
		Category category = categoryService.findByCategoryId(categoryId);

		model.addAttribute("questions", questions);
		model.addAttribute("questionsId", questions.get(0).getId());
		model.addAttribute("category", category);

		System.out.println(questions.get(0).getId());
		return "question/questionList";
	}

	@PostMapping("/question/list/{userId}/{categoryId}")
	public String showQuestions(
			@RequestParam("selectedCategory") Integer selectedCategoryId,
			@AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = loginUser.getUser().getId();
		List<Question> questions = questionService.findByUserIdAndCategoryId(userId, selectedCategoryId);
		return "redirect:/question/" + userId + "/" + selectedCategoryId + "/" + questions.get(0).getId();
	}

	/**
	 * 問題削除処理
	 *
	 * @param id 問題ID
	 * @param model
	 * @param ra
	 * @return 問題一覧画面
	 */
	//QUESTIONSテーブルのIDと紐づくEXPLANATIONSテーブルの選択肢４つを削除する必要がある
	@GetMapping("question/delete/{id}")
	public String deleteQuestion(@PathVariable Integer id, Model model, RedirectAttributes ra) {
		Question question = questionService.findById(id);
		String userId = question.getUserId().toString();
		String categoryId = question.getCategoryId().toString();
		try {
			questionService.delete(id);
			ra.addFlashAttribute("success_message", "削除に成功しました");
		} catch (NotFoundException e) {
			ra.addFlashAttribute("error_message", "対象のデータが見つかりませんでした");
		}
		ra.addAttribute("userId", userId);
		ra.addAttribute("categoryId", categoryId);
		return "redirect:/question/list/" + userId + "/" + categoryId;
	}

}
package com.example.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.question.Question;
import com.example.question.QuestionService;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final QuestionService questionService;
	public Integer correctAnswers = 0;

	@Autowired
	public ReviewService(ReviewRepository reviewRepository, QuestionService questionService) {
		this.reviewRepository = reviewRepository;
		this.questionService = questionService;
	}

	public void saveReview(Integer userId, Integer reviewId) {
		Review review = new Review();
		review.setUserId(userId);
		review.setReview_Id(reviewId);
		reviewRepository.save(review);
	}

	//	public boolean checkAnswer(Integer questionId, Integer selectedAnswer) {
	//		// 問題の正解の選択肢（EXPLANATIONSテーブルのID）を取得
	//		Question question = this.questionService.findById(questionId);
	//		Integer correctAnswers = 0;
	//		if (question != null) {
	//			// QUESTIONSテーブルのANSWER（EXPLANATIONSテーブルのID）とユーザーが選択したEXPLANATIONSテーブルのIDを比較
	//			correctAnswers++;
	//			return question.getAnswer().equals(selectedAnswer);
	//		}
	//		// 選択した答えが間違っている場合は、falseを返す
	//		return false;
	//	}

	public boolean checkAnswer(Integer questionId, Integer selectedAnswer) {
		// 問題の正解の選択肢（EXPLANATIONSテーブルのID）を取得
		Question question = this.questionService.findByQuestion(questionId);
		if (question != null) {
			// QUESTIONSテーブルのANSWER（EXPLANATIONSテーブルのID）とユーザーが選択したEXPLANATIONSテーブルのIDを比較
			boolean isCorrect = question.getAnswer().equals(selectedAnswer);
			if (isCorrect) {
				// 正解の場合、正解数をインクリメント
				correctAnswers++;
			}
			return isCorrect;
		}
		// 選択した答えが間違っている場合は、falseを返す
		return false;
	}
}
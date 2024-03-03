package com.example.review;

public class ReviewForm {
	private Integer userId;
	private Integer questionId;
	private Integer selectedAnswer;

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getSelectedAnswer() {
		return this.selectedAnswer;
	}

	public void setSelectedAnswer(Integer selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
}
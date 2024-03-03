package com.example.explanation;

import java.io.Serializable;

public class ExplanationRequest implements Serializable {
	
	private Integer questionsNumber;

	private String choice1;
	
	private String choice2;
	
	private String choice3;
	
	private String choice4;
	
	private String questionText;
	
	private Integer answer;
	
	private String explanation;
	
	private Integer categoryId;
	
	public Integer getQuestionsNumber() {
		return this.questionsNumber;
	}

	public void setQuestionsNumber(Integer questionsNumber) {
		this.questionsNumber = questionsNumber;
	}

	public String getchoice1() {
		return this.choice1;
	}

	public void setchoice1(String choice1) {
		this.choice1 = choice1;
	}
	
	public String getchoice2() {
		return this.choice2;
	}

	public void setchoice2(String choice2) {
		this.choice2 = choice2;
	}
	
	public String getchoice3() {
		return this.choice3;
	}

	public void setchoice3(String choice3) {
		this.choice3 = choice3;
	}
	
	public String getchoice4() {
		return this.choice4;
	}

	public void setchoice4(String choice4) {
		this.choice4 = choice4;
	}
	
	public String getquestionText() {
		return this.questionText;
	}

	public void setquestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public Integer getanswer() {
		return this.answer;
	}

	public void setanswer(Integer answer) {
		this.answer = answer;
	}
	
	public String getexplanation() {
		return this.explanation;
	}

	public void setexplanation(String explanation) {
		this.explanation = explanation;
	}
	
	public Integer getquestionCreate() {
		return this.categoryId;
	}

	public void setquestionCreate(Integer categoryId) {
		this.categoryId = categoryId;
	}

}

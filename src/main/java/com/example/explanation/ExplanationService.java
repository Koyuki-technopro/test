package com.example.explanation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.question.Question;
import com.example.question.QuestionRepository;

@Service
public class ExplanationService {
	private final ExplanationRepository explanationRepository;
	private QuestionRepository questionRepository;

	@Autowired
	public ExplanationService(ExplanationRepository explanationRepository,QuestionRepository questionRepository) {
		this.explanationRepository = explanationRepository;
		this.questionRepository = questionRepository;
	}
	
	public List<Explanation> findAll() {
		return this.explanationRepository.findAll();
	}
	
	
	public void create(ExplanationRequest explanationRequest,Integer userId) {
	    long questionCount = questionRepository.countQuestions();
	    
	    saveExplanation((int) questionCount, explanationRequest.getchoice1());
	    saveExplanation((int) questionCount, explanationRequest.getchoice2());
	    saveExplanation((int) questionCount, explanationRequest.getchoice3());
	    saveExplanation((int) questionCount, explanationRequest.getchoice4());
	    
	    Question question = new Question();
	    question.setUserId(userId);
		
		question.setName(explanationRequest.getquestionText());
		question.setComment(explanationRequest.getexplanation());
		
		question.setCategoryId(explanationRequest.getquestionCreate());
		question.setAnswer(explanationRequest.getanswer());
		this.questionRepository.save(question);
	}
	//選択肢を登録
	private void saveExplanation(long questionCount, String content) {
	    Explanation explanation = new Explanation();
	    explanation.setQestionsId((int) questionCount);
	    explanation.setContent(content);
	    this.explanationRepository.save(explanation);
	}

	// 選択肢を４つ表示するために問題番号を取得する
	public List<Explanation> findByQestionsId(Integer questionsId) {
		return explanationRepository.findByQestionsId(questionsId);
	}

}
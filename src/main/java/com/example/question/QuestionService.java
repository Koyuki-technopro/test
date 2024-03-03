package com.example.question;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	public Integer totalQuestions;

	@Autowired
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	/**
	 * ユーザーIDと問題集IDに紐づく問題情報取得処理
	 * @param user_id ユーザーID
	 * @param category_id 問題集ID
	 * @return 問題情報
	 */
	public List<Question> findByUserIdAndCategoryId(Integer userId, Integer categoryId) {
		return this.questionRepository.findByUserIdAndCategoryId(userId, categoryId);
	}

	/**
	 * 問題IDに紐づく問題情報取得処理
	 * @param id 問題ID
	 * @return 問題情報
	 */
	public Question findById(Integer id) {
		Optional<Question> question = questionRepository.findById(id);
		return question.get();
	}

	/**
	 * 問題IDに紐づく問題情報の削除処理
	 * @param id 問題ID
	 * @throws NotFoundException
	 */
	public void delete(Integer id) throws NotFoundException {
		// IDに紐づく問題情報が存在するかの確認
		if (!this.exists(id)) {
			throw new NotFoundException();
		}
		questionRepository.deleteById(id);
	}

	/**
	 * 問題情報の存在チェック
	 *
	 * @param id 確認したい問題情報のID
	 * @return true:存在する false:存在しない
	 */
	private boolean exists(Integer id) {
		Integer countById = questionRepository.countById(id);
		if (countById == null || countById == 0) {
			return false;
		}
		return true;
	}

	public Question findByQuestion(Integer questionsId) {
		return this.questionRepository.findById(questionsId).get();
	}

	// 総問題数を表示するために作成
	public int allQuestion(Integer userId, Integer categoryId) {
		int allQuestion = this.findByUserIdAndCategoryId(userId, categoryId).size();
		totalQuestions = allQuestion;
		return allQuestion;
	}

	public Integer findNextQuestionId(Integer currentQuestionId, Integer userId, Integer categoryId) {
		return questionRepository.findFirstByIdGreaterThanAndUserIdAndCategoryIdOrderByIdAsc(
				currentQuestionId, userId, categoryId)
				.map(Question::getId) // 「次の問題」ボタン押下時に次の問題IDを取得する
				.orElse(null); // 「結果を見る」ボタン表示のために次の問題がない場合はnullを返す
	}

}
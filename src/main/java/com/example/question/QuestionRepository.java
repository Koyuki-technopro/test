package com.example.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	/**
	 * 問題情報取得クエリ
	 * @param userId ユーザーID
	 * @param  categoryId 問題集ID
	 * @return 問題情報
	 */
	List<Question> findByUserIdAndCategoryId(Integer userId, Integer categoryId);

	/**
	 * 問題情報の件数取得
	 * @param id 問題ID
	 * @return 取得件数
	 */
	Integer countById(Integer categoryId);

	@Override
	public List<Question> findAll();

	@Query(value = "SELECT questions_seq.NEXTVAL FROM dual", nativeQuery = true)
	long countQuestions();

	Optional<Question> findFirstByIdGreaterThanAndUserIdAndCategoryIdOrderByIdAsc(Integer currentQuestionId,
			Integer userId, Integer categoryId);
}
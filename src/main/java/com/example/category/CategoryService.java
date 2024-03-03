package com.example.category;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * 問題集IDに紐づく問題集情報の取得処理
	 * @param categoryId 問題集ID
	 * @return 問題集情報
	 * */
	public Category findByCategoryId(Integer categoryId) {
		Optional<Category> category = this.categoryRepository.findById(categoryId);
		return category.get();
	}

	//	public List<Category> findCategoriesByUserId(Integer userId) {
	//		return categoryRepository.findByQuestionsUserId(userId);
	//	}

}

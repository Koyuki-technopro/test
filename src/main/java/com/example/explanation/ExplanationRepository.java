package com.example.explanation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplanationRepository extends JpaRepository<Explanation, Integer> {
	@Override
	public List<Explanation> findAll();
	
	List<Explanation> findByQestionsId(Integer questionsId);
	
}
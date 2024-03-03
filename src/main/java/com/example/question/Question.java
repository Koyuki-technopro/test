package com.example.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.category.Category;
import com.example.explanation.Explanation;

@Entity
@Table(name = "QUESTIONS")
public class Question {
	@Id
	@SequenceGenerator(name = "QUESTIONS_ID_GENERATOR", sequenceName = "QUESTIONS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTIONS_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ANSWER")
	private Integer answer;

	@Column(name = "QUESTION_COMMENT")
	private String comment;

	@Column(name = "CATEGORY_ID")
	private Integer categoryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAnswer() {
		return answer;
	}

	public void setAnswer(Integer answer) {
		this.answer = answer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	//CATEGORIESとのリレーション設定
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private Category category;

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	//EXPLANATIONSとのリレーション設定
	@ManyToOne
	@JoinColumn(name = "ANSWER", referencedColumnName = "ID", insertable = false, updatable = false)
	private Explanation explanation;

	public Explanation explanation() {
		return this.explanation;
	}
}
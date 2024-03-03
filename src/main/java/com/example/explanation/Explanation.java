package com.example.explanation;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.question.Question;

@Entity
@Table(name = "EXPLANATIONS")
public class Explanation {

	@Id
	@SequenceGenerator(name = "EXPLANATIONS_ID_GENERATOR", sequenceName = "EXPLANATIONS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPLANATIONS_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "QUESTIONS_ID")
	private Integer qestionsId;

	@Column(name = "CONTENT")
	private String content;

	//QUESTIONSとのリレーション設定
	@OneToMany(mappedBy = "explanation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Question> questions;

	public List<Question> getQuestions() {
		return this.questions;
	}

	public Integer getQestionsId() {
		return this.qestionsId;
	}

	public void setQestionsId(Integer qestionsId) {
		this.qestionsId = qestionsId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
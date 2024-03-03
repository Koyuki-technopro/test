package com.example.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.user.User;

@Entity
@Table(name = "REVIEWS")
public class Review {
	@Id
	@SequenceGenerator(name = "REVIEWS_ID_GENERATOR", sequenceName = "REVIEWS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEWS_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "REVIEW_ID")
	private Integer reviewId;

	//USERSとのリレーション設定
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private User user;

	public User getUser() {
		return this.user;
	}

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

	public Integer getReview_Id() {
		return reviewId;
	}

	public void setReview_Id(Integer revieId) {
		this.reviewId = revieId;
	}
}
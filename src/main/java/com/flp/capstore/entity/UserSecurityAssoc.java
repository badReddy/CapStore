package com.flp.capstore.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_security_assoc database table.
 * 
 */
@Entity
@Table(name="user_security_assoc")
@NamedQuery(name="UserSecurityAssoc.findAll", query="SELECT u FROM UserSecurityAssoc u")
public class UserSecurityAssoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name="security_answer")
	private String securityAnswer;

	@Column(name="upd_tsp")
	private Timestamp updTsp;

	//bi-directional many-to-one association to SecurityQuestion
	@ManyToOne
	@JoinColumn(name="question_id")
	private SecurityQuestion securityQuestion;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

	public UserSecurityAssoc() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSecurityAnswer() {
		return this.securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public Timestamp getUpdTsp() {
		return this.updTsp;
	}

	public void setUpdTsp(Timestamp updTsp) {
		this.updTsp = updTsp;
	}

	public SecurityQuestion getSecurityQuestion() {
		return this.securityQuestion;
	}

	public void setSecurityQuestion(SecurityQuestion securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
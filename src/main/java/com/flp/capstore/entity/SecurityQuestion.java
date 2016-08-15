package com.flp.capstore.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the security_questions database table.
 * 
 */
@Entity
@Table(name="security_questions")
@NamedQuery(name="SecurityQuestion.findAll", query="SELECT s FROM SecurityQuestion s")
public class SecurityQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="question_id")
	private int questionId;

	@Column(name="security_question")
	private String securityQuestion;

	//bi-directional many-to-one association to UserSecurityAssoc
	@OneToMany(mappedBy="securityQuestion", fetch=FetchType.EAGER)
	private Set<UserSecurityAssoc> userSecurityAssocs;

	public SecurityQuestion() {
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getSecurityQuestion() {
		return this.securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public Set<UserSecurityAssoc> getUserSecurityAssocs() {
		return this.userSecurityAssocs;
	}

	public void setUserSecurityAssocs(Set<UserSecurityAssoc> userSecurityAssocs) {
		this.userSecurityAssocs = userSecurityAssocs;
	}

	public UserSecurityAssoc addUserSecurityAssoc(UserSecurityAssoc userSecurityAssoc) {
		getUserSecurityAssocs().add(userSecurityAssoc);
		userSecurityAssoc.setSecurityQuestion(this);

		return userSecurityAssoc;
	}

	public UserSecurityAssoc removeUserSecurityAssoc(UserSecurityAssoc userSecurityAssoc) {
		getUserSecurityAssocs().remove(userSecurityAssoc);
		userSecurityAssoc.setSecurityQuestion(null);

		return userSecurityAssoc;
	}

}
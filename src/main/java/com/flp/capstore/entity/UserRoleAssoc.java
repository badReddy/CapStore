package com.flp.capstore.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_role_assoc database table.
 * 
 */
@Entity
@Table(name="user_role_assoc")
@NamedQuery(name="UserRoleAssoc.findAll", query="SELECT u FROM UserRoleAssoc u")
public class UserRoleAssoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="assoc_id")
	private String assocId;

	@Column(name="upd_tsp")
	private Timestamp updTsp;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_name")
	private User user;

	public UserRoleAssoc() {
	}

	public String getAssocId() {
		return this.assocId;
	}

	public void setAssocId(String assocId) {
		this.assocId = assocId;
	}

	public Timestamp getUpdTsp() {
		return this.updTsp;
	}

	public void setUpdTsp(Timestamp updTsp) {
		this.updTsp = updTsp;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
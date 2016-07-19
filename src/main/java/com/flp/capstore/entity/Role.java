package com.flp.capstore.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_id")
	private int roleId;

	@Column(name="role_type")
	private String roleType;

	//bi-directional many-to-one association to UserRoleAssoc
	@OneToMany(mappedBy="role",fetch=FetchType.EAGER)
	private List<UserRoleAssoc> userRoleAssocs;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<UserRoleAssoc> getUserRoleAssocs() {
		return this.userRoleAssocs;
	}

	public void setUserRoleAssocs(List<UserRoleAssoc> userRoleAssocs) {
		this.userRoleAssocs = userRoleAssocs;
	}

	public UserRoleAssoc addUserRoleAssoc(UserRoleAssoc userRoleAssoc) {
		getUserRoleAssocs().add(userRoleAssoc);
		userRoleAssoc.setRole(this);

		return userRoleAssoc;
	}

	public UserRoleAssoc removeUserRoleAssoc(UserRoleAssoc userRoleAssoc) {
		getUserRoleAssocs().remove(userRoleAssoc);
		userRoleAssoc.setRole(null);

		return userRoleAssoc;
	}

}
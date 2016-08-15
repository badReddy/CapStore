package com.flp.capstore.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_name")
	private String userName;

	@Column(name="date_joined")
	private Timestamp dateJoined;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	@Column(name="phone_number")
	private BigInteger phoneNumber;

	private String status;

	@Column(name="upd_tsp")
	private Timestamp updTsp;

	//bi-directional many-to-one association to Contact
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<Contact> contacts;

	//bi-directional many-to-one association to UserRoleAssoc
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<UserRoleAssoc> userRoleAssocs;

	//bi-directional one-to-one association to UserSecurityAssoc
	@OneToOne(mappedBy="user")
	private UserSecurityAssoc userSecurityAssoc;

	public User() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getDateJoined() {
		return this.dateJoined;
	}

	public void setDateJoined(Timestamp dateJoined) {
		this.dateJoined = dateJoined;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigInteger getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(BigInteger phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getUpdTsp() {
		return this.updTsp;
	}

	public void setUpdTsp(Timestamp updTsp) {
		this.updTsp = updTsp;
	}

	public Set<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Contact addContacts(Contact contacts) {
		getContacts().add(contacts);
		contacts.setUser(this);

		return contacts;
	}

	public Contact removeContacts(Contact contacts) {
		getContacts().remove(contacts);
		contacts.setUser(null);

		return contacts;
	}

	public Set<UserRoleAssoc> getUserRoleAssocs() {
		return this.userRoleAssocs;
	}

	public void setUserRoleAssocs(Set<UserRoleAssoc> userRoleAssocs) {
		this.userRoleAssocs = userRoleAssocs;
	}

	public UserRoleAssoc addUserRoleAssoc(UserRoleAssoc userRoleAssoc) {
		getUserRoleAssocs().add(userRoleAssoc);
		userRoleAssoc.setUser(this);

		return userRoleAssoc;
	}

	public UserRoleAssoc removeUserRoleAssoc(UserRoleAssoc userRoleAssoc) {
		getUserRoleAssocs().remove(userRoleAssoc);
		userRoleAssoc.setUser(null);

		return userRoleAssoc;
	}

	public UserSecurityAssoc getUserSecurityAssoc() {
		return this.userSecurityAssoc;
	}

	public void setUserSecurityAssoc(UserSecurityAssoc userSecurityAssoc) {
		this.userSecurityAssoc = userSecurityAssoc;
	}

}
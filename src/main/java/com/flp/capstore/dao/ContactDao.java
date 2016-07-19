package com.flp.capstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.flp.capstore.entity.Contact;

@Repository
public interface ContactDao extends JpaRepository<Contact,String>{

	@Transactional
	@Query("select c from Contact c where c.contactId=?1")
	public Contact fetchContact(String contactID);
}

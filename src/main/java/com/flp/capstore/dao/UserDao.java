package com.flp.capstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.flp.capstore.entity.User;

@Repository
public interface UserDao extends JpaRepository<User,String>{

	@Transactional
	@Query("select u from User u where u.userName=?1")
	public User fetchUser(String userName);

}

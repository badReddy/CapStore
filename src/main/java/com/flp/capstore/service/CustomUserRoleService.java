package com.flp.capstore.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flp.capstore.dao.UserDao;
import com.flp.capstore.entity.User;
import com.flp.capstore.entity.UserRoleAssoc;

@Service
public class CustomUserRoleService implements UserDetailsService{


	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-servlet.xml")){
			UserDao dao = (UserDao) context.getBean(UserDao.class);
			User user = dao.fetchUser(userName);
			 if (user == null || !user.getUserName().equalsIgnoreCase(userName)) {
	                throw new UsernameNotFoundException("Username not found.");
	            }
	     
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), 
					user.getStatus().equals("A"), true, true, true, getGrantedAuthorities(user));
		}
	}
	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for(UserRoleAssoc userProfile : user.getUserRoleAssocs()){
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getRole().getRoleType()));
		}
		return authorities;
	}
}

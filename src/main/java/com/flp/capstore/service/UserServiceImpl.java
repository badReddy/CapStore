package com.flp.capstore.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flp.capstore.dao.UserDao;
import com.flp.capstore.domain.UserDTO;
import com.flp.capstore.domain.mappers.DomainMapper;
import com.flp.capstore.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired private UserDao dao;

	static Logger logger = Logger.getLogger(UserService.class.getName());
	@Override
	public UserDTO fetchUser(String userName) throws Exception {
		UserDTO userResponse = new UserDTO();
		try{
			logger.info("In UserServiceImpl.fetchUser(). Ready to call DAO. ");
			userResponse = DomainMapper.mapUserEntityToDomain(dao.fetchUser(userName));
			logger.info("In UserServiceImpl.fetchUser(). dao call successful");
		} catch(Exception e){
			logger.error("In UserServiceImpl.fetchUser(). Something bad happened--> "+ e.getMessage());
			throw e;
		}
		return userResponse;
	}
	@Override
	public boolean registerUser(UserDTO user) throws Exception {
		boolean saveSuccessful = false;
		try{
			try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-security.xml")){
				com.flp.capstore.entity.User userToSave = DomainMapper.mapUserDomainToEntity(user);
				BCryptPasswordEncoder passwordEncoder = (BCryptPasswordEncoder) context.getBean("bcryptEncoder");
				userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
				if(null==userToSave.getStatus()){
					userToSave.setStatus("A");
				}
				dao.saveAndFlush(userToSave);
				saveSuccessful = true;
			}
		} 
		catch(Exception e){
			logger.error("In UserServiceImpl.saveUser(). Something bad happened--> "+ e.getMessage());
			saveSuccessful = false;
			throw e;
		}
		return saveSuccessful;
	}

}

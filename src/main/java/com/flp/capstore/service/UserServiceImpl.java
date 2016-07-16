package com.flp.capstore.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flp.capstore.dao.UserDao;
import com.flp.capstore.domain.User;
import com.flp.capstore.domain.mappers.DomainMapper;
import com.flp.capstore.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired private UserDao dao;
	static Logger logger = Logger.getLogger(UserService.class.getName());
	@Override
	public User fetchUser(String userName) throws Exception {
		User userResponse = new User();
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

}

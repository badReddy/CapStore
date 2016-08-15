package com.flp.capstore.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flp.capstore.dao.ContactDao;
import com.flp.capstore.domain.ContactDTO;
import com.flp.capstore.domain.mappers.DomainMapper;
import com.flp.capstore.service.interfaces.ContactService;

@Service
public class ContactServiceImpl implements ContactService{
	
	static Logger logger = Logger.getLogger(ContactService.class.getName());
	@Autowired ContactDao dao;
	@Override
	public ContactDTO fetchContact(String contactID) throws Exception {
		ContactDTO contactResponse = new ContactDTO();
		try{
			logger.info("In ContactServiceImpl.fetchContact(). Ready to call DAO. ");
			contactResponse = DomainMapper.mapContactEntityToDomain(dao.fetchContact(contactID));
			logger.info("In UserServiceImpl.fetchUser(). dao call successful");
		} catch(Exception e){
			logger.error("In ContactServiceImpl.fetchContact(). Something bad happened--> "+ e.getMessage());
			throw e;
		}
		return contactResponse;
	}

}

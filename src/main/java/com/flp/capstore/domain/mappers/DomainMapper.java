package com.flp.capstore.domain.mappers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flp.capstore.domain.Contact;
import com.flp.capstore.domain.User;
import com.flp.capstore.entity.UserRoleAssoc;

public class DomainMapper {
	
	static Logger logger = Logger.getLogger(DomainMapper.class.getName());
	
	public static User mapUserEntityToDomain(com.flp.capstore.entity.User userEntity) throws Exception{
		logger.info("In mapUserEntityToDomain(). Here to map userEntity to Domain for--> "+userEntity.getUserName());
		User userDomain = new User();
		DateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd");
		userDomain.setUserName(userEntity.getUserName());
		userDomain.setFirstName(userEntity.getFirstName());
		userDomain.setLastName(userEntity.getLastName());
		userDomain.setEmail(userEntity.getEmail());
		userDomain.setPhone(String.valueOf(userEntity.getPhoneNumber()));
		userDomain.setStatus(userEntity.getStatus());
		userDomain.setJoinedOn(timeStampFormat.format(userEntity.getDateJoined()));
		List<Contact> contacts = new ArrayList<Contact>();
		for(com.flp.capstore.entity.Contact contactEntity:userEntity.getContacts()){
			Contact contact = new Contact();
			contact = mapContactEntityToDomain(contactEntity);
			contacts.add(contact);
		}
		List<String> roles = new ArrayList<String>();
		for(UserRoleAssoc assoc:userEntity.getUserRoleAssocs()){
			roles.add(assoc.getRole().getRoleType());
		}
		userDomain.setContacts(contacts);
		userDomain.setRoles(roles);
		logger.info("In mapUserEntityToDomain(). Mapping successful for--> "+userEntity.getUserName());
		return userDomain;
		
	}

	public static Contact mapContactEntityToDomain(com.flp.capstore.entity.Contact contactEntity) throws Exception{
		logger.info("In mapContactEntityToDomain(). Here to map contactEntity to Domain for--> "+contactEntity.getContactId());
		Contact contactDomain = new Contact();
		DateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd");
		contactDomain.setName(contactEntity.getContactName());
		contactDomain.setAddressLine1(contactEntity.getAddressLine1());
		contactDomain.setAddressLine2(contactEntity.getAddressLine2());
		contactDomain.setCity(contactEntity.getCity());
		contactDomain.setState(contactEntity.getState());
		contactDomain.setZip(String.valueOf(contactEntity.getZip()));
		contactDomain.setLastUpdatedOn(timeStampFormat.format(contactEntity.getUpdTsp()));
		logger.info("In mapContactEntityToDomain(). Mapping successful for--> "+contactEntity.getContactId());
		return contactDomain;
	}
}

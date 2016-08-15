package com.flp.capstore.service.interfaces;

import com.flp.capstore.domain.ContactDTO;

public interface ContactService {

	public ContactDTO fetchContact(String contactID) throws Exception;
}

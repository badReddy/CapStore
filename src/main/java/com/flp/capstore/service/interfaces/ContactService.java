package com.flp.capstore.service.interfaces;

import com.flp.capstore.domain.Contact;

public interface ContactService {

	public Contact fetchContact(String contactID) throws Exception;
}

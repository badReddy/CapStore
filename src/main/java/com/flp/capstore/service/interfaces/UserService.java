package com.flp.capstore.service.interfaces;

import com.flp.capstore.domain.UserDTO;

public interface UserService {
	
	public boolean registerUser(UserDTO user) throws Exception;

	public UserDTO fetchUser(String userID) throws Exception;

}

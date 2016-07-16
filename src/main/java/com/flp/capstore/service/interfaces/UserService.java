package com.flp.capstore.service.interfaces;

import com.flp.capstore.domain.User;

public interface UserService {

	public User fetchUser(String userID) throws Exception;

}

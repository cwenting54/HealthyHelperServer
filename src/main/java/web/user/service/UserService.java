package web.user.service;

import web.user.vo.User;

public interface UserService {
	String register(User user);

	User login(User user);

	String userUpdate(User user);
	
	User getUserByAccount(String account);
}
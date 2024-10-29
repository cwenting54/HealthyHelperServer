package web.user.dao;

import web.user.vo.User;

public interface UserDao {
	int insert(User user);
	User selectByAccount(String account);
	User selectByAccountAndPassword(User user);
	int update(User user);
//	int register(User user);
//    User login(String account, String password);


}

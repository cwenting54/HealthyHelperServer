package web.user.service.impl;

import java.util.Objects;

import javax.naming.NamingException;

import web.user.dao.UserDao;
import web.user.dao.impl.UserDaoImpl;
import web.user.service.UserService;
import web.user.vo.User;

public class UserServiceImpl implements UserService{
	private UserDao userDao;

	public UserServiceImpl() throws NamingException {
		userDao = new UserDaoImpl();
	}

	@Override
	public String register(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userUpdate(User user) {
		String password = user.getPassword();
		if (password == null || password.length() < 30) {
			return "密碼必須填寫";
		}

		String cPassword = user.getcPassword();
		if (!Objects.equals(cPassword, password)) {
			return "密碼與確認密碼必須相同";
		}

		String username = user.getUsername();
		if (username == null) {
			return "姓名必須填寫";
		}
		int result = userDao.update(user);
		
		return result > 0 ? null : "修改失敗";
	}

}

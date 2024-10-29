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
	       // 檢查必填欄位
	       if (user.getAccount() == null || user.getAccount().trim().isEmpty()) {
	           return "帳號必須填寫";
	       }
	       if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
	           return "密碼必須填寫";
	       }
	       if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
	           return "姓名必須填寫";
	       }

	       // 檢查帳號是否已存在
	       if (userDao.selectByAccount(user.getAccount()) != null) {
	           return "帳號已經存在";
	       }

	       // 檢查手機號碼格式（如果有填寫）
	       String phone = user.getPhoneno();
	       if (phone != null && !phone.trim().isEmpty() 
	           && !phone.matches("^09\\d{8}$")) {
	           return "手機號碼格式不正確";
	       }

	       // 執行註冊
	       int result = userDao.insert(user);
	       return result > 0 ? null : "註冊失敗";
	   }

	   @Override
	   public User login(User user) {
	       // 檢查必填欄位
	       if (user.getAccount() == null || user.getAccount().trim().isEmpty()) {
	           System.out.println("登入失敗：帳號為空");
	           return null;
	       }
	       if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
	           System.out.println("登入失敗：密碼為空");
	           return null;
	       }

	       // 執行登入驗證
	       System.out.println("執行登入驗證 - 帳號：" + user.getAccount());
	       User loginUser = userDao.selectByAccountAndPassword(user);
	       
	       if (loginUser != null) {
	           System.out.println("登入成功 - 用戶：" + loginUser.getUsername());
	       } else {
	           System.out.println("登入失敗：帳號或密碼錯誤");
	       }
	       
	       return loginUser;
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

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
	    try {
	        System.out.println("開始註冊流程，檢查帳號: " + user.getAccount());
	        
	        
	        if (user.getAccount() == null || user.getAccount().trim().isEmpty()) {
	            return "帳號必須填寫";
	        }
	        
	      
	        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
	            return "密碼必須填寫";
	        }
	        
	      
	        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
	            return "姓名必須填寫";
	        }
	        
	        
	        if (user.getUserEmail() == null || user.getUserEmail().trim().isEmpty()) {
	            return "Email必須填寫";
	        }

	       
	        if (userDao.isAccountExists(user.getAccount())) {
	            System.out.println("帳號已存在: " + user.getAccount());
	            return "帳號已經存在，無法註冊";
	        }

	     
	        if (!user.getUserEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	            return "Email格式不正確";
	        }

	     
	        String phone = user.getPhoneno();
	        if (phone != null && !phone.trim().isEmpty() && !phone.matches("^09\\d{8}$")) {
	            return "手機號碼格式不正確";
	        }

	        
	        if (user.getGender() == null) {
	            return "性別必須選擇";
	        }

	 
	        if (user.getBirthday() == null) {
	            return "生日必須填寫";
	        }

	       
	        if (user.getRoleID() == null) {
	            return "使用者身分必須選擇";
	        }

	      
	        if (user.getRoleID() == 2 && (user.getCertificate() == null || user.getCertificate().length == 0)) {
	            return "營養師必須上傳證書";
	        }

	        if (user.getRegistrationDate() == null) {
	            user.setRegistrationDate(new java.sql.Timestamp(System.currentTimeMillis()));
	        }

	     
	        System.out.println("開始執行資料庫註冊");
	        int result = userDao.insert(user);
	        
	      
	        if (result <= 0) {
	            System.out.println("註冊失敗，資料庫操作未成功");
	            return "註冊失敗";
	        }
	        
	        System.out.println("註冊成功完成");
	        return null;

	    } catch (Exception e) {
	        System.out.println("註冊過程發生錯誤: " + e.getMessage());
	        e.printStackTrace();
	        return "系統錯誤: " + e.getMessage();
	    }
	}

	@Override
	public User login(User user) {
		System.out.println("測試用文字:Login");
		
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
	    
	    // 在這裡加入檢查
	    if (loginUser != null) {
	        System.out.println("\n=== 登入成功，檢查回傳資料 ===");
	        System.out.println("電話: [" + loginUser.getPhoneno() + "]");
	        System.out.println("性別: [" + loginUser.getGender() + "]");
	        System.out.println("生日: [" + loginUser.getBirthday() + "]");
	        System.out.println("角色: [" + loginUser.getRoleID() + "]");
	        System.out.println("============================\n");
	        
	        return loginUser;
	    } else {
	        System.out.println("登入失敗：帳號或密碼錯誤");
	        return null;
	    }
	}
//	@Override
//	public User login(User user) {
//	    // 檢查必填欄位
//	    if (user.getAccount() == null || user.getAccount().trim().isEmpty()) {
//	        System.out.println("登入失敗：帳號為空");
//	        return null;
//	    }
//	    if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
//	        System.out.println("登入失敗：密碼為空");
//	        return null;
//	    }
//
//	    // 執行登入驗證
//	    System.out.println("執行登入驗證 - 帳號：" + user.getAccount());
//	    User loginUser = userDao.selectByAccountAndPassword(user);
//
//	    if (loginUser != null) {
//	        // 在登入成功時檢查資料
//	        System.out.println("登入成功 - 用戶：" + loginUser.getUsername());
//	        System.out.println("=== 登入成功後的用戶資料 ===");
//	        System.out.println("電話: " + loginUser.getPhoneno());
//	        System.out.println("性別: " + loginUser.getGender());
//	        System.out.println("角色: " + loginUser.getRoleID());
//	        System.out.println("生日: " + loginUser.getBirthday());
//	    } else {
//	        System.out.println("登入失敗：帳號或密碼錯誤");
//	    }
//
//	    return loginUser;
//	}
	   
	   @Override
	   public User getUserByAccount(String account) {
	       if (account == null || account.trim().isEmpty()) {
	           return null;
	       }
	       return userDao.selectByAccount(account);
	   }

	   @Override
	   public String userUpdate(User user) {
	       // 先找到原有的用戶資料
	       try {
	           // 基本驗證
	           if (user.getAccount() == null || user.getAccount().trim().isEmpty()) {
	               return "帳號不能為空";
	           }

	           // 檢查信箱格式
	           if (user.getUserEmail() != null && !user.getUserEmail().trim().isEmpty()) {
	               if (!user.getUserEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	                   return "Email格式不正確";
	               }
	           }

	           // 檢查電話格式
	           if (user.getPhoneno() != null && !user.getPhoneno().trim().isEmpty()) {
	               if (!user.getPhoneno().matches("^09\\d{8}$")) {
	                   return "手機號碼格式不正確";
	               }
	           }

	           // 執行更新
	           int result = userDao.update(user);
	           if (result > 0) {
	               return null;  // 更新成功
	           } else {
	               return "更新失敗";
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	           return "系統錯誤：" + e.getMessage();
	       }
	   }


}

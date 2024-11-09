package web.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import web.user.dao.UserDao;
import web.user.vo.User;

public class UserDaoImpl implements UserDao {
	private DataSource ds;

	public UserDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public int insert(User user) {
	    String sql = "INSERT INTO user(account, password, username, userEmail, phoneno, gender, birthday, roleID, certificate, userIcon) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (Connection conn = ds.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, user.getAccount());
	        pstmt.setString(2, user.getPassword());
	        pstmt.setString(3, user.getUsername());
	        pstmt.setString(4, user.getUserEmail());
	        pstmt.setString(5, user.getPhoneno());
	        pstmt.setInt(6, user.getGender());
	        pstmt.setDate(7, user.getBirthday());
	        pstmt.setInt(8, user.getRoleID());
	        pstmt.setBytes(9, user.getCertificate());
	        pstmt.setBytes(10, user.getUserIcon());
	        
	        return pstmt.executeUpdate();
	    } catch (SQLException e) {
	        // 檢查 SQL 錯誤代碼來識別是否為重複鍵
	        if ("23000".equals(e.getSQLState())) { 
	            System.out.println("帳號已存在，無法插入相同帳號");
	            return -1; // 返回 -1 表示插入失敗，帳號重複
	        }
	        e.printStackTrace();
	    }
	    return -1;
	}

	
	@Override
	public boolean isAccountExists(String account) {
	    String sql = "SELECT COUNT(*) FROM user WHERE account = ?";
	    try (Connection conn = ds.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, account);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            System.out.println("帳號檢查結果: " + count); // 顯示查詢結果
	            return count > 0; // 若 count > 0 表示帳號已存在
	        }
	    } catch (SQLException e) {
	        System.out.println("檢查帳號時發生錯誤：" + e.getMessage());
	        e.printStackTrace();
	    }
	    return false; 
	}


	@Override
	public User selectByAccount(String account) {
		String sql = "select * from user where account = ?";
		try(
				Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);	
			) {
				pstmt.setString(1, account);			
				try(ResultSet rs = pstmt.executeQuery()){
					if (rs.next()) {
						User user = new User();
						user.setUserId(rs.getInt("userid"));
						user.setAccount (rs.getString("account"));
						user.setPassword(rs.getString("password"));
						user.setUsername(rs.getString("username"));
						user.setUserEmail(rs.getString("userEmail"));				
						user.setPhoneno(rs.getString("phoneno"));				
						user.setGender(rs.getInt("gender"));				
						user.setBirthday(rs.getDate("birthday"));	
						user.setRoleID(rs.getInt("roleID"));
						user.setCertificate(rs.getBytes("certificate"));
						user.setUserIcon(rs.getBytes("userIcon"));
						user.setPhotoUrl(rs.getString("photoUrl"));//不太確定需不需要
						//System.out.println("Retrieved birthday from DB: " + user.getBirthday());
						return user;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public User selectByAccountAndPassword(User user) {
	    String sql = "SELECT * FROM user WHERE account = ? AND password = ?";
	    
	    try (Connection conn = ds.getConnection(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, user.getAccount());
	        pstmt.setString(2, user.getPassword());

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                User resultUser = new User();
	                resultUser.setUserId(rs.getInt("userId"));
	                resultUser.setAccount(rs.getString("account"));
	                resultUser.setPassword(rs.getString("password"));
	                resultUser.setUsername(rs.getString("username"));
	                resultUser.setUserEmail(rs.getString("userEmail"));
	                resultUser.setPhoneno(rs.getString("phoneno"));
	                resultUser.setGender(rs.getInt("gender")); 
	                resultUser.setBirthday(rs.getDate("birthday"));
	                resultUser.setRoleID(rs.getInt("roleID")); 
	                resultUser.setPhotoUrl(rs.getString("photoUrl"));
	                return resultUser;
	            } else {
	                System.out.println("未找到匹配的資料");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

//	
	@Override
	public int update(User user) {
	    String sql = "UPDATE user SET username=?, gender=?, phoneno=?, userEmail=?, birthday=? WHERE account=?";
	    try (Connection conn = ds.getConnection(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, user.getUsername());

	        // 檢查 gender 是否為 null，若為 null 則設置為 SQL NULL
	        if (user.getGender() != null) {
	            pstmt.setInt(2, user.getGender());
	        } else {
	            pstmt.setNull(2, java.sql.Types.TINYINT);
	        }

	        pstmt.setString(3, user.getPhoneno());
	        pstmt.setString(4, user.getUserEmail());

	        // 檢查 birthday 是否為 null，若為 null 則設置為 SQL NULL
	        if (user.getBirthday() != null) {
	            pstmt.setDate(5, user.getBirthday());
	        } else {
	            pstmt.setNull(5, java.sql.Types.DATE);
	        }
	       
	        
	     // 設置 certificate
	        if (user.getCertificate() != null) {
	            pstmt.setBytes(6, user.getCertificate());
	        } else {
	            pstmt.setNull(6, java.sql.Types.BLOB);
	        }

	     // 使用 account 作為 WHERE 條件，確保不會被更新
	        pstmt.setString(6, user.getAccount());

	        return pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}



}
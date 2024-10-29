package web.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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
		String sql = "insert into user(account, password, username, userEmail, phoneno, gender, birthday, roleID, certificate, userIcon) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(
				Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, user.getAccount());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getUserEmail());
			pstmt.setString(5, user.getPhoneno());
			pstmt.setInt(6, user.getGender());
			pstmt.setTimestamp(7, user.getBirthday());
			pstmt.setInt(8, user.getRoleID());
			pstmt.setBytes(9, user.getCertificate());
			pstmt.setBytes(10, user.getUserIcon());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
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
						user.setUserId(rs.getInt("id"));
						user.setAccount (rs.getString("account"));
						user.setPassword(rs.getString("password"));
						user.setUsername(rs.getString("username"));
						user.setUserEmail(rs.getString("userEmail"));				
						user.setPhoneno(rs.getString("phoneno"));				
						user.setGender(rs.getInt("gender"));				
						user.setBirthday(rs.getTimestamp("birthday"));				
						user.setRoleID(rs.getInt("roleID"));
						user.setCertificate(rs.getBytes("certificate"));
						user.setUserIcon(rs.getBytes("userIcon"));
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

//	@Override
//	public User selectByAccountAndPassword(User user) {
//		String sql = "select * from user where account = ? and password = ?";
//		try(
//				Connection conn = ds.getConnection(); 
//				PreparedStatement pstmt = conn.prepareStatement(sql);	
//			) {
//				pstmt.setString(1, user.getUsername());			
//				pstmt.setString(2, user.getPassword());		 	
//				try(ResultSet rs = pstmt.executeQuery()){
//					if (rs.next()) {
//						user = new User();
//						user.setUserId (rs.getInt("id"));
//						user.setAccount(rs.getString("account"));
//						user.setPassword(rs.getString("password"));
//						user.setUsername(rs.getString("username"));
//						user.setUserEmail(rs.getString("userEmail"));				
//						user.setPhoneno(rs.getString("phoneno"));				
//						user.setGender(rs.getInt("gender"));				
//						user.setBirthday(rs.getTimestamp("birthday"));				
//						user.setRoleID(rs.getInt("roleID"));
//						user.setCertificate(rs.getBytes("certificate"));
//						user.setUserIcon(rs.getBytes("userIcon"));										
//						return user;
//					}
//				}catch (Exception e) {
//					e.printStackTrace();
//				}			
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		return null;
//	}

	@Override
	public User selectByAccountAndPassword(User user) {
	    System.out.println("=== 開始查詢 ===");
	    System.out.println("輸入帳號: " + user.getAccount());
	    System.out.println("輸入密碼: " + user.getPassword());
	    
	    String sql = "select * from user where account = ? and password = ?";
	    try (
	        Connection conn = ds.getConnection(); 
	        PreparedStatement pstmt = conn.prepareStatement(sql);    
	    ) {
	        pstmt.setString(1, user.getAccount());            
	        pstmt.setString(2, user.getPassword());            

	        try (ResultSet rs = pstmt.executeQuery()) {
	            // 先檢查 SQL 查詢的結果
	            System.out.println("執行查詢: " + sql);
	            System.out.println("參數1 (account): " + user.getAccount());
	            System.out.println("參數2 (password): " + user.getPassword());
	            
	            if (rs.next()) {
	                System.out.println("找到符合的資料：");
	                System.out.println("DB account: " + rs.getString("account"));
	                System.out.println("DB password: " + rs.getString("password"));
	                
	                User resultUser = new User();
	                resultUser.setUserId(rs.getInt("userid"));
	                resultUser.setAccount(rs.getString("account"));
	                resultUser.setUsername(rs.getString("username"));
	                resultUser.setPassword(rs.getString("password"));
	                resultUser.setUserEmail(rs.getString("userEmail"));                
	                return resultUser;
	            } else {
	                System.out.println("未找到符合的資料");
	            }
	        }
	    } catch (Exception e) {
	        System.err.println("查詢發生錯誤: " + e.getMessage());
	        e.printStackTrace();
	    }
	    System.out.println("=== 查詢結束 ===");
	    return null;
	}
//	
	@Override
	public int update(User user) {
		String sql = "update user set password=?, username=?, gender=?, phoneno=?, userEmail=?, birthday=? where account = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUsername());
			pstmt.setInt(3, user.getGender());
			pstmt.setString(4, user.getPhoneno());
			pstmt.setString(5, user.getUserEmail());
			pstmt.setTimestamp(6, user.getBirthday());	
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
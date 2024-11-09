package web.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.user.dao.UserPhotoDao;

public class UserPhotoDaoImpl implements UserPhotoDao {
	private DataSource ds;

	public UserPhotoDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public int insertUserPhoto(int userId, String photoUrl) {
		String sql = "update user set photoUrl = ? where userId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, photoUrl);
			pstmt.setInt(2, userId);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public String selectUserPhoto(int userId) {
		String sql = "select photoUrl from user where userId = ?";
		try(Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					String photoUrl = rs.getString("photoUrl");
					return photoUrl;
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

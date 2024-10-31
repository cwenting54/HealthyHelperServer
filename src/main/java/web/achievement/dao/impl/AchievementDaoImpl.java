package web.achievement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.achievement.dao.AchievementDao;
import web.achievement.vo.Achievement;

public class AchievementDaoImpl implements AchievementDao {
	private DataSource ds;

	public AchievementDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public List<Achievement> selectAchievementsByUserId(int userId) {
		String sql = "SELECT " + "a.aname, " + "a.aTypeId, " + "a.content, " + "au.finishtime, " + "a.photo "
				+ "FROM achievement a " + "JOIN achievementlist au " + "ON a.aId = au.aId " + "WHERE au.userId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				var list = new ArrayList<Achievement>();
				while (rs.next()) {
					Achievement achievement = new Achievement();
					achievement.setAname(rs.getString("aname"));
					achievement.setaTypeId(rs.getInt("aTypeId"));
					achievement.setContent(rs.getString("content"));
					achievement.setFinishtime(rs.getTimestamp("finishtime"));
					achievement.setPhoto(rs.getBytes("photo"));
					list.add(achievement);
				}
				return list;
			}catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Achievement insertAchievement(int aTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectDiaryTimesByUserID(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectWeightTimesByUserID(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> selectFinishPlanByUserID(int userId) {
		String sql = "select categoryId from userdietplan where userId = ? and finishstate = 1";
		List<Integer> list = new ArrayList<>();
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int categoryId = rs.getInt("categoryId");
					list.add(categoryId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}

}

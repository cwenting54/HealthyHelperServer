package web.achievement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.achievement.dao.AchievementDao;
import web.achievement.vo.Achievement;
import web.user.vo.User;

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
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertAchievement(Achievement achievement) {
		String sql = "insert into achievementlist (userId, aId) values(?,?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			User user = new User();
			pstmt.setInt(1, user.getUserId());
			pstmt.setInt(2, achievement.getaTypeId());

			int result = pstmt.executeUpdate();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int selectDiaryTimesByUserID(int userId) {
		String sql = "select count(distinct createDate) from fooddiary";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int selectWeightTimesByUserID(int userId) {
		String sql = "select count(*) from bodymanagement";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Map<Integer, Integer> selectFinishPlanCountByUserID(int userId) {
		String sql = "select categoryId, count(*) as count from userdietplan where userId = ? and finishstate = 1 GROUP BY categoryId";
		Map<Integer, Integer> categoryCountMap = new HashMap<>();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int categoryId = rs.getInt("categoryId");
					int count = rs.getInt("count");

					categoryCountMap.put(categoryId, count);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryCountMap;
	}

}

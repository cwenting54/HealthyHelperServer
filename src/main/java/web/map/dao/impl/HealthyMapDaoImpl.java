package web.map.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.map.dao.HealthyMapDao;
import web.map.vo.HealthyMap;
import web.map.vo.UserFavoriteList;
import web.user.vo.User;

public class HealthyMapDaoImpl implements HealthyMapDao {
	private DataSource ds;

	public HealthyMapDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public List<HealthyMap> selectRestaurantByRegion(String rregion, String rcity) {
		String sql = "select * from restaurant where rregion = ? and rcity = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, rregion);
			pstmt.setString(2, rcity);
			
			var list = new ArrayList<HealthyMap>();
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					HealthyMap healthyMap = new HealthyMap();
					healthyMap.setRID(rs.getInt("rID"));
					healthyMap.setRname(rs.getString("rname"));
					healthyMap.setRweb(rs.getString("rweb"));
					healthyMap.setRcity(rs.getString("rcity"));
					healthyMap.setRregion(rs.getString("rregion"));
					healthyMap.setRaddress(rs.getString("raddress"));
					healthyMap.setRphone(rs.getString("rphone"));
					healthyMap.setRrating(rs.getFloat("rrating"));
					healthyMap.setRphotoUrl(rs.getString("rphotoUrl"));
					healthyMap.setRlongitude(rs.getDouble("rlongitude"));
					healthyMap.setRlatitude(rs.getDouble("rlatitude"));

					list.add(healthyMap);
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
	public List<HealthyMap> selectRestaurantByCity(String rcity) {
		String sql = "select * from restaurant where rcity = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, rcity);
			var list = new ArrayList<HealthyMap>();
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					HealthyMap healthyMap = new HealthyMap();
					healthyMap.setRID(rs.getInt("rID"));
					healthyMap.setRname(rs.getString("rname"));
					healthyMap.setRweb(rs.getString("rweb"));
					healthyMap.setRcity(rs.getString("rcity"));
					healthyMap.setRregion(rs.getString("rregion"));
					healthyMap.setRaddress(rs.getString("raddress"));
					healthyMap.setRphone(rs.getString("rphone"));
					healthyMap.setRrating(rs.getFloat("rrating"));
					healthyMap.setRphotoUrl(rs.getString("rphotoUrl"));
					healthyMap.setRlongitude(rs.getDouble("rlongitude"));
					healthyMap.setRlatitude(rs.getDouble("rlatitude"));

					list.add(healthyMap);
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
	public List<UserFavoriteList> selectFavorRestaurants(int userId) {
		String sql = "select * from userfavoritelist where userId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery();) {
				var list = new ArrayList<UserFavoriteList>();
				while (rs.next()) {
					UserFavoriteList userFavoriteList = new UserFavoriteList();
					userFavoriteList.setUfid(rs.getInt("ufid"));
					userFavoriteList.setRid(rs.getInt("rid"));
					list.add(userFavoriteList);
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
	public int insertFavorRestaurants(int userId, int rid) {
		String sql = "insert into userfavoritelist(userid, rid) values(?,?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {		
			pstmt.setInt(1, userId);
			pstmt.setInt(2, rid);

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteFavorRestaurants(int userId, int rid) {
		String sql = "delete from userfavoritelist where rid = ? and userId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, rid);
			pstmt.setInt(2, userId);

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}

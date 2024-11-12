package web.plan.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import web.plan.dao.PlanDao;
import web.plan.vo.PlanWithCategory;

public class PlanDaoImpl implements PlanDao{
	private DataSource ds;

	public PlanDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}


	@Override
	public PlanWithCategory selectSingleByUserIDAndFinishState(Integer userId, Integer finishstate) {
		String sql = "SELECT u.userDietPlanId, u.startDatetime, u.endDatetime, u.categoryId, d.categoryName "
				+ "FROM userdietplan AS u JOIN dietplancategory AS d "
				+ "ON u.categoryId = d.categoryId "
				+ "WHERE u.userId = ? AND u.finishstate = ? "
				+ "ORDER BY startDatetime;";
		try(
				Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
		   )
		{
			pstmt.setInt(1, userId);
			pstmt.setInt(2, finishstate);
			try (ResultSet rs =pstmt.executeQuery()){
				if(rs.next())
				{
					PlanWithCategory plan = new PlanWithCategory();
					plan.setUserDietPlanId(rs.getInt("userDietPlanId"));
					plan.setStartDateTime(rs.getTimestamp("startDatetime"));
					plan.setEndDateTime(rs.getTimestamp("endDatetime"));
					plan.setCategoryID(rs.getInt("categoryId"));
					plan.setCategoryName(rs.getString("categoryName"));
					System.out.println("select out: " + plan);
					return plan;
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int insertByUserID(PlanWithCategory planWithCategory) {
		String sql = "INSERT INTO userdietplan(categoryId,userId,startDatetime,endDatetime,finishstate,"
				+ "fatgoal,carbongoal,proteingoal,Caloriesgoal) "
				+ "VALUES(?,?,?,?,?,?,?,?,?);";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, planWithCategory.getCategoryID());
			pstmt.setInt(2, planWithCategory.getUserId());
			pstmt.setTimestamp(3, planWithCategory.getStartDateTime());
			pstmt.setTimestamp(4, planWithCategory.getEndDateTime());
			pstmt.setInt(5, planWithCategory.getFinishstate());
			pstmt.setFloat(6, planWithCategory.getFatgoal());
			pstmt.setFloat(7, planWithCategory.getCarbongoal());
			pstmt.setFloat(8, planWithCategory.getProteingoal());
			pstmt.setFloat(9, planWithCategory.getCaloriesgoal());
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}

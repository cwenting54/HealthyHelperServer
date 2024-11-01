package web.plan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public List<PlanWithCategory> selectByUserIDAndFinishState(Integer userId, Integer finishstate) {
		String sql = "SELECT u.userDietPlanId, u.startDatetime, u.endDatetime, d.categoryName "
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
				var list = new ArrayList<PlanWithCategory>();
				while(rs.next())
				{
					PlanWithCategory plan = new PlanWithCategory();
					plan.setUserDietPlanId(rs.getInt("userDietPlanId"));
					plan.setStartDateTime(rs.getTimestamp("startDatetime"));
					plan.setEndDateTime(rs.getTimestamp("endDatetime"));
					plan.setCategoryName("categoryName");
					list.add(plan);
				}
				System.out.println("select out: " + list);
				return list;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



}

package web.plan.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import web.plan.dao.CheckPlanDao;
import web.plan.vo.DiaryAll;
import web.plan.vo.PlanWithCategory;

public class CheckPlanDaoImpl implements CheckPlanDao{
	private DataSource ds;

	public CheckPlanDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public PlanWithCategory selectPlanAll(Integer userId, Integer userDietPlanId) {
		String sql = "SELECT * FROM userdietplan "
				+ "WHERE userId = ? AND userDietPlanId = ? ";
		try(
				Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
		   )
		{
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userDietPlanId);
			try (ResultSet rs =pstmt.executeQuery()){
				if(rs.next())
				{
					PlanWithCategory plan = new PlanWithCategory();
					plan.setUserDietPlanId(rs.getInt("userDietPlanId"));
					plan.setCategoryID(rs.getInt("categoryId"));
					plan.setUserId(rs.getInt("userId"));
					plan.setStartDateTime(rs.getTimestamp("startDatetime"));
					plan.setEndDateTime(rs.getTimestamp("endDatetime"));
					plan.setFinishstate(rs.getInt("finishstate"));
					plan.setFatgoal(rs.getFloat("fatgoal"));
					plan.setCarbongoal(rs.getFloat("carbongoal"));
					plan.setProteingoal(rs.getFloat("proteingoal"));
					plan.setCaloriesgoal(rs.getFloat("Caloriesgoal"));
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
	public List<DiaryAll> selectDiaryAll(Integer userId, Date startDate, Date endDate) {
		String sql = "SELECT * FROM fooddiary "
				+ "WHERE userID = ? AND createdate >= ? AND createdate <= ? ";
		try(
				Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql);
		   )
		{
			pstmt.setInt(1, userId);
			pstmt.setDate(2, startDate);
			pstmt.setDate(3, endDate);
			try (ResultSet rs =pstmt.executeQuery()){
				var list = new ArrayList<DiaryAll>();
				while(rs.next())
				{
					DiaryAll dietDiary = new DiaryAll();
					dietDiary.setDiaryId(rs.getInt("diaryId"));
					dietDiary.setUserId(rs.getInt("userId"));
					dietDiary.setCreateDate(rs.getDate("createDate"));
					dietDiary.setTotalFat(rs.getFloat("totalFat"));
					dietDiary.setTotalCarbon(rs.getFloat("totalCarbon"));
					dietDiary.setTotalFiber(rs.getFloat("totalFiber"));
					dietDiary.setTotalSugar(rs.getFloat("totalSugar"));
					dietDiary.setTotalSodium(rs.getFloat("totalSodium"));
					dietDiary.setTotalProtein(rs.getFloat("totalProtein"));
					dietDiary.setTotalCalories(rs.getFloat("totalCalories"));
					list.add(dietDiary);
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

	@Override
	public int updateByUserIdAndUserDietPlanID(Integer userId, Integer userDietPlanId) {
		String sql = "UPDATE userdietplan SET finishstate = 1 "
				+ "WHERE userId = ? AND userDietPlanId = ? ;";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userDietPlanId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}

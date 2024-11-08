package web.dietdiary.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.dietdiary.vo.DietDiaryVO;
import web.dietdiary.vo.MealTimeRangeCategoryVO;

public class MealTimeRangeCategoryDaoImpl implements MealTimeRangeCategoryDao{

	private DataSource dataSource;
	
	public MealTimeRangeCategoryDaoImpl(DataSource dataSource) throws NamingException {
		this.dataSource = dataSource;
		if (this.dataSource == null) {
			this.dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
		}
	}
	
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
	
	private ArrayList<MealTimeRangeCategoryVO> resultSetToObjects(ResultSet resultSet) throws SQLException {
		ArrayList<MealTimeRangeCategoryVO> mealTimeRangeCategoryVOs = new ArrayList<MealTimeRangeCategoryVO>();
		while (resultSet.next()) {
			MealTimeRangeCategoryVO mealTimeRangeCategoryVO = new MealTimeRangeCategoryVO();

			int userId = resultSet.getInt("userID");
			Time breakfastStartTime = resultSet.getTime("breakfastStartTime");
			Time breakfastEndTime = resultSet.getTime("breakfastEndTime");
			Time lunchStartTime = resultSet.getTime("lunchStartTime");
			Time lunchEndTime = resultSet.getTime("lunchEndTime");
			Time dinnerStartTime = resultSet.getTime("dinnerStartTime");
			Time dinnerEndTime = resultSet.getTime("dinnerEndTime");
			Time supperStartTime = resultSet.getTime("supperStartTime");
			Time supperEndTime = resultSet.getTime("supperEndTime");
			
			mealTimeRangeCategoryVO.setUserId(userId);
			mealTimeRangeCategoryVO.setBreakfastStartTime(breakfastStartTime);
			mealTimeRangeCategoryVO.setBreakfastEndTime(breakfastEndTime);
			mealTimeRangeCategoryVO.setLunchStartTime(lunchStartTime);
			mealTimeRangeCategoryVO.setLunchEndTime(lunchEndTime);
			mealTimeRangeCategoryVO.setDinnerStartTime(dinnerStartTime);
			mealTimeRangeCategoryVO.setDinnerEndTime(dinnerEndTime);
			mealTimeRangeCategoryVO.setSupperStartTime(supperStartTime);
			mealTimeRangeCategoryVO.setSupperEndTime(supperEndTime);
			
			mealTimeRangeCategoryVOs.add(mealTimeRangeCategoryVO);
		}
		
		return mealTimeRangeCategoryVOs;
	}
	
	@Override
	public int insert(MealTimeRangeCategoryVO mealTimeRangeCategoryVO) {
		String sqlCommand = "INSERT INTO mealTimeRangeCategory "
				+ "(userId,breakfastStartTime,breakfastEndTime,lunchStartTime,lunchEndTime,dinnerStartTime,dinnerEndTime,supperStartTime,supperEndTime)"
				+ "VALUES "
				+ "(?,?,?,?,?,?,?,?,?);";

		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {

			preparedStatement.setInt(1, mealTimeRangeCategoryVO.getUserId());
			preparedStatement.setTime(2, mealTimeRangeCategoryVO.getBreakfastStartTime());
			preparedStatement.setTime(3, mealTimeRangeCategoryVO.getBreakfastEndTime());
			preparedStatement.setTime(4, mealTimeRangeCategoryVO.getLunchStartTime());
			preparedStatement.setTime(5, mealTimeRangeCategoryVO.getLunchEndTime());
			preparedStatement.setTime(6, mealTimeRangeCategoryVO.getLunchEndTime());
			preparedStatement.setTime(7, mealTimeRangeCategoryVO.getDinnerEndTime());
			preparedStatement.setTime(8, mealTimeRangeCategoryVO.getSupperStartTime());
			preparedStatement.setTime(9, mealTimeRangeCategoryVO.getSupperEndTime());

			System.out.println("sqlCommand:"+sqlCommand);
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("Unknown error during execution of sql statement.");
			}

			return affectedRows;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public ArrayList<MealTimeRangeCategoryVO> selectByUserId(int userId) {
		String sqlCommand = "SELECT * FROM mealTimeRangeCategory "
				+ "WHERE userId = ? ;";

		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
			preparedStatement.setInt(1,userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<MealTimeRangeCategoryVO> mealTimeRangeCategoryVOs = this.resultSetToObjects(resultSet);
			if(mealTimeRangeCategoryVOs == null) {
				throw new Exception("Unknown error!!!");
			}
			System.out.println("mealTimeRangeCategories:"+mealTimeRangeCategoryVOs.toString());
			if(mealTimeRangeCategoryVOs.isEmpty()) {
				return mealTimeRangeCategoryVOs;
			}
			if(mealTimeRangeCategoryVOs.size()!=1) {
				throw new Exception("Error!!! Duplicated record!!!");
			}
			return mealTimeRangeCategoryVOs;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int update(MealTimeRangeCategoryVO mealTimeRangeCategoryVO) {
		String sqlCommand = "UPDATE mealTimeRangeCategory SET "
				+ "breakfastStartTime = ? ,"
				+ "breakfastEndTime = ? ,"
				+ "lunchStartTime = ? ,"
				+ "lunchEndTime = ? ,"
				+ "dinnerStartTime = ? ,"
				+ "dinnerEndTime = ? ,"
				+ "supperStartTime = ? ,"
				+ "supperEndTime = ? "
				+ "WHERE "
				+ "userID = ? ;";

		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
			
			Time breakfastStartTime = mealTimeRangeCategoryVO.getBreakfastEndTime();
			Time breakfastEndTime = mealTimeRangeCategoryVO.getBreakfastEndTime();
			Time lunchStartTime = mealTimeRangeCategoryVO.getLunchStartTime();
			Time lunchEndTime = mealTimeRangeCategoryVO.getLunchEndTime();
			Time dinnerStartTime = mealTimeRangeCategoryVO.getDinnerStartTime();
			Time dinnerEndTime = mealTimeRangeCategoryVO.getDinnerEndTime();
			Time supperStartTime = mealTimeRangeCategoryVO.getSupperStartTime();
			Time supperEndTime = mealTimeRangeCategoryVO.getSupperEndTime();
			int userId = mealTimeRangeCategoryVO.getUserId();
			
			preparedStatement.setTime(1,breakfastStartTime);
			preparedStatement.setTime(2,breakfastEndTime);
			preparedStatement.setTime(3,lunchStartTime);
			preparedStatement.setTime(4,lunchEndTime);
			preparedStatement.setTime(5,dinnerStartTime);
			preparedStatement.setTime(6,dinnerEndTime);
			preparedStatement.setTime(7,supperStartTime);
			preparedStatement.setTime(8,supperEndTime);
			preparedStatement.setInt(9,userId);
		
			int affectedRows = preparedStatement.executeUpdate();
			if(affectedRows != 1) {
				throw new Exception("Unknown error!!!");
			}
			return affectedRows;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
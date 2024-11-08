package web.dietdiary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.dietdiary.vo.FoodItemVO;

public class FoodItemDaoImpl implements FoodItemDao {

	private DataSource dataSource;
	
	public FoodItemDaoImpl(DataSource dataSource) throws NamingException {
		this.dataSource = dataSource;
		if (this.dataSource == null) {
			this.dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
		}
	}
	
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
	
	private ArrayList<FoodItemVO> resultSetToObjects(ResultSet resultSet) throws SQLException{
		ArrayList<FoodItemVO> foodItemVOs = new ArrayList<FoodItemVO>();
		while(resultSet.next()) {
			
			int diaryId = resultSet.getInt("diaryID");
			int foodId = resultSet.getInt("foodID");
			Double grams = resultSet.getDouble("grams");
			
			FoodItemVO foodItemVO = new FoodItemVO();
			
			foodItemVO.setDiaryId(diaryId);
			foodItemVO.setFoodId(foodId);
			foodItemVO.setGrams(grams);
			
			foodItemVOs.add(foodItemVO);
		}	
		return foodItemVOs;
	}

	@Override
	public int insert(FoodItemVO foodItemVO) {
		String sqlCommand = "INSERT INTO fooditem "
				+ " (diaryId,foodId,grams)"
				+ " VALUES "
				+ "(?,?,?);";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1,foodItemVO.getDiaryId());
			preparedStatement.setInt(2,foodItemVO.getFoodId());
			preparedStatement.setDouble(3,foodItemVO.getGrams());
			
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	@Override
	public FoodItemVO select(int foodId) {
		String sqlCommand = "SELECT * FROM fooditem WHERE foodID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
				preparedStatement.setInt(1,foodId);
				ResultSet resultSet = preparedStatement.executeQuery();
				ArrayList<FoodItemVO> foodItemVOs = this.resultSetToObjects(resultSet);
				System.out.println("foodItems:"+foodItemVOs);
				if(foodItemVOs == null || foodItemVOs.isEmpty()) {
					throw new Exception("Unknown error during execution of sql statement.");
				}
				return foodItemVOs.get(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int delete(FoodItemVO foodItemVO) {
		String sqlCommand = "DELETE FROM fooditem WHERE foodId = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1,foodItemVO.getFoodId());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(FoodItemVO foodItemVO) {
		String sqlCommand = "UPDATE fooditem SET diaryId = ?, grams = ? WHERE foodId = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1, foodItemVO.getDiaryId());
			preparedStatement.setDouble(2, foodItemVO.getGrams());
			preparedStatement.setInt(3,foodItemVO.getFoodId());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}

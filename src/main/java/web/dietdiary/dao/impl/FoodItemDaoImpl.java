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
		ArrayList<FoodItemVO> foodItems = new ArrayList<FoodItemVO>();
		while(resultSet.next()) {
			
			int diaryId = resultSet.getInt("diaryID");
			int foodId = resultSet.getInt("foodID");
			int mealCategoryId = resultSet.getInt("mealCategoryID");
			Double grams = resultSet.getDouble("grams");
			
			FoodItemVO foodItem = new FoodItemVO();
			
			foodItem.setDiaryId(diaryId);
			foodItem.setFoodId(foodId);
			foodItem.setMealCategoryId(mealCategoryId);
			foodItem.setGrams(grams);
			
			foodItems.add(foodItem);
		}	
		return foodItems;
	}

	@Override
	public int insert(FoodItemVO foodItem) {
		String sqlCommand = "INSERT INTO fooditem "
				+ " (diaryId,foodId,mealCategoryID,grams)"
				+ " VALUES "
				+ "(?,?,?,?);";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1,foodItem.getDiaryId());
			preparedStatement.setInt(2,foodItem.getFoodId());
			preparedStatement.setInt(3,foodItem.getMealCategoryId());
			preparedStatement.setDouble(4,foodItem.getGrams());
			
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
				ArrayList<FoodItemVO> foodItems = this.resultSetToObjects(resultSet);
				System.out.println("foodItems:"+foodItems);
				if(foodItems == null || foodItems.isEmpty()) {
					throw new Exception("Unknown error during execution of sql statement.");
				}
				return foodItems.get(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int delete(FoodItemVO foodItem) {
		String sqlCommand = "DELETE FROM fooditem WHERE foodId = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1,foodItem.getFoodId());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(FoodItemVO foodItem) {
		String sqlCommand = "UPDATE fooditem SET diaryId = ?, grams = ? WHERE foodId = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1, foodItem.getDiaryId());
			preparedStatement.setDouble(2, foodItem.getGrams());
			preparedStatement.setInt(3,foodItem.getFoodId());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateMealCategoryId(FoodItemVO foodItem) {
		String sqlCommand = "UPDATE fooditem SET mealCategoryID = ? WHERE foodId = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1, foodItem.getMealCategoryId());
			preparedStatement.setInt(2,foodItem.getFoodId());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

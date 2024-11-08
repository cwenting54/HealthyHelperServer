package web.dietdiary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.dietdiary.vo.DietDiaryVO;
import web.dietdiary.vo.FoodVO;
import web.dietdiary.vo.FoodItemVO;
import web.dietdiary.vo.FoodNameAndGramsVO;

public class FoodDaoImpl implements FoodDao {

	private DataSource dataSource;

	public FoodDaoImpl(DataSource dataSource) throws NamingException {
		this.dataSource = dataSource;
		if (this.dataSource == null) {
			this.dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
		}
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	private ArrayList<FoodVO> resultSetToObjects(ResultSet resultSet) throws SQLException {
		ArrayList<FoodVO> foodVOs = new ArrayList<FoodVO>();
		while (resultSet.next()) {
			
			FoodVO foodVO = new FoodVO();

			int foodId = resultSet.getInt("foodID");
			String foodName = resultSet.getString("foodname");
			Double fat = resultSet.getDouble("fat");
			Double carbon = resultSet.getDouble("carbon");
			Double protein = resultSet.getDouble("protein");
			Double fiber = resultSet.getDouble("fiber");
			Double sugar = resultSet.getDouble("sugar");
			Double sodium = resultSet.getDouble("sodium");
			Double calories = resultSet.getDouble("calories");

			foodVO.setFoodId(foodId);
			foodVO.setFoodName(foodName);
			foodVO.setFat(fat);
			foodVO.setCarbon(carbon);
			foodVO.setProtein(protein);
			foodVO.setFiber(fiber);
			foodVO.setSugar(sugar);
			foodVO.setSodium(sodium);
			foodVO.setCalories(calories);
			
			foodVOs.add(foodVO);
		}
		
		return foodVOs;
	}

	@Override
	public FoodVO selectByFoodName(String name) {
		String sqlCommand = "SELECT * FROM food WHERE foodname = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<FoodVO> foodVOs = this.resultSetToObjects(resultSet);
			
			if(foodVOs == null || foodVOs.size() <= 0 ) {
				throw new Exception("There are no record found.");
			}
			
			if(foodVOs.size() != 1) {
				throw new Exception("Unknown error!!!\nToo many records found.\nIt should found only one record");
			}
				
			return foodVOs.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public FoodVO selectByFoodId(int foodId) {
		String sqlCommand = "SELECT * FROM food WHERE foodID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1, foodId);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<FoodVO> foodVOs = this.resultSetToObjects(resultSet);
			
			if(foodVOs == null || foodVOs.size() <= 0 ) {
				throw new Exception("There are no record found.");
			}
			
			if(foodVOs.size() != 1) {
				throw new Exception("Unknown error!!!\nToo many records found.\nIt should found only one record");
			}
				
			return foodVOs.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<FoodVO> listAvailableFoods() {
		String sqlCommand = "SELECT * FROM food ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSetToObjects(resultSet);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(FoodVO foodVO) {
		String sqlCommand = "INSERT INTO food "
				+ " (foodname,fat,carbon,protein,fiber,sugar,sodium,calories) "
				+ " VALUES "
				+ "(?,?,?,?,?,?,?,?);";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setString(1, foodVO.getFoodName());
			preparedStatement.setDouble(2, foodVO.getFat());
			preparedStatement.setDouble(3, foodVO.getCarbon());
			preparedStatement.setDouble(4, foodVO.getProtein());
			preparedStatement.setDouble(5, foodVO.getFiber());
			preparedStatement.setDouble(6, foodVO.getSugar());
			preparedStatement.setDouble(7, foodVO.getSodium());
			preparedStatement.setDouble(8, foodVO.getCalories());
			
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(FoodVO foodVO) {
		String sqlCommand = "DELETE FROM food "
				+ " WHERE foodId = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1, foodVO.getFoodId());
			
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}

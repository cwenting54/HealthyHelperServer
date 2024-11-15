package web.dietdiary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.dietdiary.vo.FoodVO;

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
		ArrayList<FoodVO> foods = new ArrayList<FoodVO>();
		while (resultSet.next()) {
			
			FoodVO food = new FoodVO();

			int foodId = resultSet.getInt("foodID");
			String foodName = resultSet.getString("foodname");
			Double fat = resultSet.getDouble("fat");
			Double carbon = resultSet.getDouble("carbon");
			Double protein = resultSet.getDouble("protein");
			Double fiber = resultSet.getDouble("fiber");
			Double sugar = resultSet.getDouble("sugar");
			Double sodium = resultSet.getDouble("sodium");
			Double calories = resultSet.getDouble("calories");

			food.setFoodID(foodId);
			food.setFoodName(foodName);
			food.setFat(fat);
			food.setCarbon(carbon);
			food.setProtein(protein);
			food.setFiber(fiber);
			food.setSugar(sugar);
			food.setSodium(sodium);
			food.setCalories(calories);
			
			foods.add(food);
		}
		
		return foods;
	}

	@Override
	public ArrayList<FoodVO> selectByFoodName(String name) {
		String sqlCommand = "SELECT * FROM food WHERE foodname = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<FoodVO> foods = this.resultSetToObjects(resultSet);
			return foods;
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
			ArrayList<FoodVO> foods = this.resultSetToObjects(resultSet);
			
			if(foods == null || foods.size() <= 0 ) {
				throw new Exception("There are no record found.");
			}
			
			if(foods.size() != 1) {
				throw new Exception("Unknown error!!!\nToo many records found.\nIt should found only one record");
			}
				
			return foods.get(0);
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
	public int insert(FoodVO food) {
		String sqlCommand = "INSERT INTO food "
				+ " (foodname,fat,carbon,protein,fiber,sugar,sodium,calories) "
				+ " VALUES "
				+ "(?,?,?,?,?,?,?,?);";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setString(1, food.getFoodName());
			preparedStatement.setDouble(2, food.getFat());
			preparedStatement.setDouble(3, food.getCarbon());
			preparedStatement.setDouble(4, food.getProtein());
			preparedStatement.setDouble(5, food.getFiber());
			preparedStatement.setDouble(6, food.getSugar());
			preparedStatement.setDouble(7, food.getSodium());
			preparedStatement.setDouble(8, food.getCalories());
			
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(FoodVO food) {
		String sqlCommand = "DELETE FROM food WHERE foodID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1, food.getFoodID());
			
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public ArrayList<FoodVO> selectByFoodId(FoodVO food) {
		String sqlCommand = "SELECT * FROM food  WHERE foodID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1, food.getFoodID());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSetToObjects(resultSet);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

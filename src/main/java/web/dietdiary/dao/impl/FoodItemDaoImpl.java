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
			
			foodItem.setDiaryID(diaryId);
			foodItem.setFoodID(foodId);
			foodItem.setMealCategoryID(mealCategoryId);
			foodItem.setGrams(grams);
			
			foodItems.add(foodItem);
		}	
		return foodItems;
	}

	@Override
	public int insert(FoodItemVO foodItem) {
		System.out.println("--------------------------------");
		System.out.println("FoodItemDaoImpl class, insert method was called.");
		
		String sqlCommand = "INSERT INTO fooditem "
				+ " (diaryID,foodID,mealCategoryID,grams)"
				+ " VALUES "
				+ "(?,?,?,?);";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1,foodItem.getDiaryID());
			preparedStatement.setInt(2,foodItem.getFoodID());
			preparedStatement.setObject(3,foodItem.getMealCategoryID());
			preparedStatement.setDouble(4,foodItem.getGrams());
			
			System.out.println("FoodItemDaoImpl class, insert method was finished to called.");
			System.out.println("--------------------------------");
			
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("FoodItemDaoImpl class, insert method was finished to called.");
		System.out.println("--------------------------------");
		return -1;
	}
	
	@Override
	public ArrayList<FoodItemVO> selectByDiaryId(FoodItemVO foodItem) {
		System.out.println("--------------------------------");
		System.out.println("FoodItemDaoImpl class, selectByDiaryId method was called.");
		
		String sqlCommand = "SELECT * FROM fooditem WHERE diaryID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
				preparedStatement.setInt(1,foodItem.getDiaryID());
				ResultSet resultSet = preparedStatement.executeQuery();
				ArrayList<FoodItemVO> foodItems = this.resultSetToObjects(resultSet);
				System.out.println();
				System.out.println();
				System.out.println("foodItems:"+foodItems);
				System.out.println();
				System.out.println();
				
				System.out.println("FoodItemDaoImpl class, selectByDiaryId method was finished to called.");
				System.out.println("--------------------------------");
				return foodItems;
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("FoodItemDaoImpl class, selectByDiaryId method was finished to called.");
		System.out.println("--------------------------------");
		return null;
	}
	
	@Override
	public ArrayList<FoodItemVO> selectByFoodId(FoodItemVO foodItem) {
		System.out.println("--------------------------------");
		System.out.println("FoodItemDaoImpl class, selectByFoodId method was called.");
		
		String sqlCommand = "SELECT * FROM fooditem WHERE foodID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
				preparedStatement.setInt(1,foodItem.getFoodID());
				ResultSet resultSet = preparedStatement.executeQuery();
				ArrayList<FoodItemVO> foodItems = this.resultSetToObjects(resultSet);
				
				System.out.println();
				System.out.println();
				System.out.println("foodItems:"+foodItems);
				System.out.println();
				System.out.println();
				
				System.out.println("FoodItemDaoImpl class, selectByFoodId method was finished to called.");
				System.out.println("--------------------------------");
				return foodItems;
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("FoodItemDaoImpl class, selectByFoodId method was finished to called.");
		System.out.println("--------------------------------");
		return null;
	}
	
	@Override
	public ArrayList<FoodItemVO> selectByDiaryIdAndFoodId(FoodItemVO foodItem) {
		System.out.println("--------------------------------");
		System.out.println("FoodItemDaoImpl class, selectByDiaryIdAndFoodId method was called.");
		
		String sqlCommand = "SELECT * FROM fooditem WHERE diaryID = ? AND foodID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
				preparedStatement.setInt(1,foodItem.getDiaryID());
				preparedStatement.setInt(2,foodItem.getFoodID());

				ResultSet resultSet = preparedStatement.executeQuery();
				ArrayList<FoodItemVO> foodItems = this.resultSetToObjects(resultSet);
				
				System.out.println();
				System.out.println();
				System.out.println("foodItems:"+foodItems);
				System.out.println();
				System.out.println();
				
				System.out.println("FoodItemDaoImpl class, selectByDiaryIdAndFoodId method was finished to called.");				
				System.out.println("--------------------------------");
				return foodItems;
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("FoodItemDaoImpl class, selectByDiaryIdAndFoodId method was finished to called.");				
		System.out.println("--------------------------------");
		return null;
	}

	@Override
	public int deleteByDiaryIdAndFoodId(FoodItemVO foodItem) {
		System.out.println("------------------------------------------------------------");
		System.out.println("FoodItemDaoImpl class delete method was called.`");
		
		System.out.println();
        System.out.println();
        System.out.println("foodItem:"+foodItem);
        System.out.println();
        System.out.println();
        
		System.out.println("FoodItemDaoImpl class delete method was finished to called.`");
		System.out.println("------------------------------------------------------------");
		
		String sqlCommand = "DELETE FROM fooditem WHERE diaryID = ? AND foodID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1,foodItem.getDiaryID());
			preparedStatement.setInt(2,foodItem.getFoodID());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateByFoodId(FoodItemVO foodItem) {
		System.out.println("------------------------------------------------------------");
		System.out.println("FoodItemDaoImpl class updateByFoodId method was called.`");
		
		System.out.println();
        System.out.println();
        System.out.println("foodItem:"+foodItem);
        System.out.println();
        System.out.println();
        
		System.out.println("FoodItemDaoImpl class updateByFoodId method was finished to called.`");
		System.out.println("------------------------------------------------------------");
		
		String sqlCommand = "UPDATE fooditem SET grams = ?, mealCategoryID = ? WHERE foodID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setDouble(1, foodItem.getGrams());
			preparedStatement.setInt(2,foodItem.getMealCategoryID());
			preparedStatement.setInt(3,foodItem.getFoodID());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateByDiaryIdAndFoodId(FoodItemVO foodItem) {
		System.out.println("------------------------------------------------------------");
		System.out.println("FoodItemServiceImpl class updateByDiaryIdAndFoodId method was called.`");
		
		System.out.println();
        System.out.println();
        System.out.println("foodItem:"+foodItem);
        System.out.println();
        System.out.println();
        
		System.out.println("FoodItemServiceImpl class updateByDiaryIdAndFoodId method was finished to called.`");
		System.out.println("------------------------------------------------------------");
		
		String sqlCommand = "UPDATE fooditem SET mealCategoryID = ?, grams = ? WHERE diaryID = ? AND foodID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setInt(1, foodItem.getMealCategoryID());
			preparedStatement.setDouble(2, foodItem.getGrams());
			preparedStatement.setInt(3,foodItem.getDiaryID());
			preparedStatement.setInt(4,foodItem.getFoodID());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public ArrayList<FoodItemVO> selectByDiaryIdAndMealCategoryId(FoodItemVO foodItem) {
		String sqlCommand = "SELECT * FROM fooditem WHERE diaryID = ? AND mealCategoryID = ? ;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
				preparedStatement.setInt(1,foodItem.getDiaryID());
				preparedStatement.setInt(2,foodItem.getMealCategoryID());

				System.out.println("preparedStatement:"+preparedStatement);
				ResultSet resultSet = preparedStatement.executeQuery();
				ArrayList<FoodItemVO> foodItems = this.resultSetToObjects(resultSet);
				System.out.println("foodItems:"+foodItems);
				return foodItems;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

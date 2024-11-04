package web.dietdiary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.dietdiary.vo.Food;
import web.dietdiary.vo.FoodName;
import web.dietdiary.vo.MealTimeRangeCategory;

public class FoodNameDaoImpl implements FoodNameDao{

	private DataSource dataSource;
	
	public FoodNameDaoImpl(DataSource dataSource) throws NamingException {
		this.dataSource = dataSource;
		if (this.dataSource == null) {
			this.dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
		}
	}
	
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
	
	private ArrayList<FoodName> resultSetToObjects(ResultSet resultSet) throws SQLException {
		ArrayList<FoodName> foodNames = new ArrayList<FoodName>();
		while (resultSet.next()) {
			FoodName foodName = new FoodName();

			String name = resultSet.getString("foodname");
			
			foodName.setFoodName(name);
			
			foodNames.add(foodName);
		}
		return foodNames;
	}

	
	@Override
	public ArrayList<FoodName> listAvailableFoodName() {
		String sqlCommand = "SELECT * FROM food;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<FoodName> foods = this.resultSetToObjects(resultSet);
			return foods;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

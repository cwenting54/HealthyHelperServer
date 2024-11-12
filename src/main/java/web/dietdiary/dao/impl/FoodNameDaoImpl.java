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

import web.dietdiary.vo.FoodNameVO;

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
	
	private ArrayList<FoodNameVO> resultSetToObjects(ResultSet resultSet) throws SQLException {
		ArrayList<FoodNameVO> foodNames = new ArrayList<FoodNameVO>();
		while (resultSet.next()) {
			FoodNameVO foodName = new FoodNameVO();

			String name = resultSet.getString("foodname");
			
			foodName.setFoodName(name);
			
			foodNames.add(foodName);
		}
		return foodNames;
	}

	
	@Override
	public ArrayList<FoodNameVO> listAvailableFoodName() {
		String sqlCommand = "SELECT * FROM food;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<FoodNameVO> foods = this.resultSetToObjects(resultSet);
			return foods;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
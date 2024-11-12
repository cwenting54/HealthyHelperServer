package web.dietdiary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.dietdiary.vo.FoodNameAndGramsVO;


public class FoodNameAndGramsDaoImpl implements FoodNameAndGramsDao{

	private DataSource dataSource;

	public FoodNameAndGramsDaoImpl(DataSource dataSource) throws NamingException {
		this.dataSource = dataSource;
		if (this.dataSource == null) {
			this.dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
		}
	}
	
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
	
	private ArrayList<FoodNameAndGramsVO> resultSetToObjects(ResultSet resultSet) throws SQLException{
		ArrayList<FoodNameAndGramsVO> foodNameAndGramses = new ArrayList<FoodNameAndGramsVO>();
		while(resultSet.next()) {
			
			String foodName = resultSet.getString("foodName");
			Double grams = resultSet.getDouble("gram");
			
			FoodNameAndGramsVO foodNameAndGrams = new FoodNameAndGramsVO();
			
			foodNameAndGrams.setFoodName(foodName);
			foodNameAndGrams.setGrams(grams);
			
			foodNameAndGramses.add(foodNameAndGrams);
		}	
		return foodNameAndGramses;
	}

	
	@Override
	public ArrayList<FoodNameAndGramsVO> listAvailableFoodsNameAndGrams() {
		String sqlCommand = "SELECT foodname,gram FROM food;";
		try(
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			ResultSet resultSet = preparedStatement.executeQuery();
			return this.resultSetToObjects(resultSet);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

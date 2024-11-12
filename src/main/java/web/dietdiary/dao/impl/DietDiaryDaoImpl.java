package web.dietdiary.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.dietdiary.vo.DietDiaryVO;


public class DietDiaryDaoImpl implements DietDiaryDao {

	private DataSource dataSource;

	public DietDiaryDaoImpl(DataSource dataSource) throws NamingException {
		this.dataSource = dataSource;
		if (this.dataSource == null) {
			this.dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
		}
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	private ArrayList<DietDiaryVO> resultSetToObjects(ResultSet resultSet) throws SQLException {
		ArrayList<DietDiaryVO> dietDiaries = new ArrayList<DietDiaryVO>();
		while (resultSet.next()) {
			
			int diaryId = resultSet.getInt("diaryID");
			int userId = resultSet.getInt("userID");
			Date createDate = resultSet.getDate("createdate");
			Time createTime = resultSet.getTime("createtime");
			Double totalFat = resultSet.getDouble("totalfat");
			Double totalCarbon = resultSet.getDouble("totalcarbon");
			Double totalProtein = resultSet.getDouble("totalprotein");
			Double totalFiber = resultSet.getDouble("totalfiber");
			Double totalSugar = resultSet.getDouble("totalsugar");
			Double totalSodium = resultSet.getDouble("totalsodium");
			Double totalCalories = resultSet.getDouble("totalcalories");

			DietDiaryVO dietDiaryVO = new DietDiaryVO();

			dietDiaryVO.setDiaryID(diaryId);
			dietDiaryVO.setUserID(userId);
			dietDiaryVO.setCreateDate(createDate);
			dietDiaryVO.setCreateTime(createTime);
			dietDiaryVO.setTotalFat(totalFat);
			dietDiaryVO.setTotalCarbon(totalCarbon);
			dietDiaryVO.setTotalProtein(totalProtein);
			dietDiaryVO.setTotalFiber(totalFiber);
			dietDiaryVO.setTotalSugar(totalSugar);
			dietDiaryVO.setTotalSodium(totalSodium);
			dietDiaryVO.setTotalCalories(totalCalories);

			dietDiaries.add(dietDiaryVO);
		}

		return dietDiaries;
	}

	@Override
	public int insert(DietDiaryVO dietDiaryVO) {
		String sqlCommand = "INSERT INTO fooddiary "
				+ "(diaryID,userID,createdate,createtime,totalFat,totalCarbon,totalFiber,totalSugar,totalSodium,totalProtein,totalCalories) "
				+ " VALUES " + "(?,?,?,?,?,?,?,?,?,?,?)";

		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
			
			preparedStatement.setInt(1, dietDiaryVO.getDiaryID());
			preparedStatement.setInt(2, dietDiaryVO.getUserID());
			preparedStatement.setDate(3, dietDiaryVO.getCreateDate());
			preparedStatement.setTime(4, dietDiaryVO.getCreateTime());
			preparedStatement.setDouble(5, dietDiaryVO.getTotalFat());
			preparedStatement.setDouble(6, dietDiaryVO.getTotalCarbon());
			preparedStatement.setDouble(7, dietDiaryVO.getTotalFiber());
			preparedStatement.setDouble(8, dietDiaryVO.getTotalSugar());
			preparedStatement.setDouble(9, dietDiaryVO.getTotalSodium());
			preparedStatement.setDouble(10, dietDiaryVO.getTotalProtein());
			preparedStatement.setDouble(11, dietDiaryVO.getTotalCalories());

			int affectedRow = preparedStatement.executeUpdate();
			return affectedRow;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public ArrayList<DietDiaryVO> selectByTime(int userId, Time time) {
		String sqlCommand = "SELECT * FROM fooddiary WHERE" + " userId = ? AND createtime = ? ;";
		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
			preparedStatement.setInt(1, userId);
			preparedStatement.setTime(2, time);

			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSetToObjects(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<DietDiaryVO> selectByDate(int userId, Date date) {
		String sqlCommand = "SELECT * FROM fooddiary WHERE" + " userId = ? AND createdate = ? ;";
		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {

			preparedStatement.setInt(1, userId);
			preparedStatement.setDate(2, date);

			ResultSet resultSet = preparedStatement.executeQuery();

			ArrayList<DietDiaryVO> dietDiaries = resultSetToObjects(resultSet);

			if (dietDiaries == null) {
				throw new Exception("Unknown error during execution of sql statement.");
			}

			return dietDiaries;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<DietDiaryVO> selectByDateAndTime(int userId, Date date, Time time) {
		String sqlCommand = "SELECT * FROM fooddiary WHERE" + " userId = ? AND createdate = ? AND createtime = ? ;";
		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {

			preparedStatement.setInt(1, userId);
			preparedStatement.setDate(2, date);
			preparedStatement.setTime(3, time);

			ResultSet resultSet = preparedStatement.executeQuery();

			ArrayList<DietDiaryVO> dietDiaries = resultSetToObjects(resultSet);

			if (dietDiaries == null) {
				throw new Exception("Unknown error during execution of sql statement.");
			}

			return dietDiaries;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public int updateByDiaryId(DietDiaryVO dietDiaryVO) {
		String sqlCommand = "UPDATE fooddiary SET " + "totalfat = ? , " + "totalcarbon = ? , " + "totalprotein = ? , "
				+ "totalfiber = ? , " + "totalsugar = ? ," + "totalsodium = ? " + "WHERE" + " diaryID = ? AND "
				+ "createdate = ? ;";

		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {

			preparedStatement.setDouble(1, dietDiaryVO.getTotalFat());
			preparedStatement.setDouble(2, dietDiaryVO.getTotalCarbon());
			preparedStatement.setDouble(3, dietDiaryVO.getTotalProtein());
			preparedStatement.setDouble(4, dietDiaryVO.getTotalFiber());
			preparedStatement.setDouble(5, dietDiaryVO.getTotalSugar());
			preparedStatement.setDouble(6, dietDiaryVO.getTotalSodium());

			preparedStatement.setInt(7, dietDiaryVO.getDiaryID());
			preparedStatement.setDate(8, dietDiaryVO.getCreateDate());

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
	public DietDiaryVO selectByDiaryIdAndDate(int diaryId, Date date) {
		String sqlCommand = "SELECT * FROM fooddiary WHERE" + " diaryID = ? AND createdate = ? ;";
		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {

			preparedStatement.setInt(1, diaryId);
			preparedStatement.setDate(2, date);

			ResultSet resultSet = preparedStatement.executeQuery();

			ArrayList<DietDiaryVO> dietDiaries = resultSetToObjects(resultSet);

			if (dietDiaries == null || dietDiaries.size() != 1) {
				throw new Exception("Unknown error during execution of sql statement.");
			}
			return dietDiaries.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<DietDiaryVO> selectByUserIdAndDate(DietDiaryVO dietDiary) {
		String sqlCommand = "SELECT * FROM fooddiary WHERE" + " userID = ? AND createdate = ? ;";
		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
			
			preparedStatement.setInt(1,dietDiary.getUserID());
			preparedStatement.setDate(2,dietDiary.getCreateDate());
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSetToObjects(resultSet);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

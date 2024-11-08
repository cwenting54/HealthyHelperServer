package web.dietdiary.dao.impl;

import java.sql.Blob;
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

import web.dietdiary.vo.DiaryDescriptionVO;
import web.dietdiary.vo.DietDiaryVO;

public class DiaryDescriptionDaoImpl implements DiaryDescriptionDao {

	private DataSource dataSource;

	public DiaryDescriptionDaoImpl(DataSource dataSource) throws NamingException {
		this.dataSource = dataSource;
		if (this.dataSource == null) {
			this.dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
		}
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	private ArrayList<DiaryDescriptionVO> resultSetToObjects(ResultSet resultSet) throws SQLException {
		ArrayList<DiaryDescriptionVO> diaryDescriptionVOs = new ArrayList<DiaryDescriptionVO>();
		while (resultSet.next()) {
			DiaryDescriptionVO diaryDescriptionVO = new DiaryDescriptionVO();
			int diaryId = resultSet.getInt("diaryID");
			String foodIconUri = resultSet.getString("foodIconUri");
			String foodDescription = resultSet.getString("foodDescription");

			diaryDescriptionVO.setDiaryId(diaryId);
			diaryDescriptionVO.setFoodIconUri(foodIconUri);
			diaryDescriptionVO.setFoodDescription(foodDescription);
			
			diaryDescriptionVOs.add(diaryDescriptionVO);
		}

		return diaryDescriptionVOs;
	}

	@Override
	public int insert(DiaryDescriptionVO diaryDescriptionVO) {
		String sqlCommand = "INSERT INTO diarydescription "
				+ "(diaryID,foodIconUri,foodDescription) "
				+ " VALUES " + "(?,?,?);";
		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
		
			preparedStatement.setInt(1,diaryDescriptionVO.getDiaryId());
			preparedStatement.setString(2,diaryDescriptionVO.getFoodIconUri());
			preparedStatement.setString(3,diaryDescriptionVO.getFoodDescription());
			
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateById(DiaryDescriptionVO diaryDescriptionVO) {
		String sqlCommand = "UPDATE diarydescription "
				+ "SET foodIconUri = ? , foodDescription = ? "
				+ " WHERE " + " diaryID = ? ; ";
		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {

			preparedStatement.setString(1,diaryDescriptionVO.getFoodIconUri());
			preparedStatement.setString(2,diaryDescriptionVO.getFoodDescription());
			preparedStatement.setInt(3,diaryDescriptionVO.getDiaryId());
			
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public ArrayList<DiaryDescriptionVO> selectById(DiaryDescriptionVO diaryDescriptionVO) {
		String sqlCommand = "SELECT * FROM diarydescription WHERE diaryID = ? ;";
		try (Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);) {
			
			preparedStatement.setInt(1,diaryDescriptionVO.getDiaryId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<DiaryDescriptionVO> diaryDescriptionVOs = resultSetToObjects(resultSet);
			
			return diaryDescriptionVOs;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

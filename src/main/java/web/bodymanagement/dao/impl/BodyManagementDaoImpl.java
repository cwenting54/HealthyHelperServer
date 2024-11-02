package web.bodymanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.bodymanagement.dao.BodyManagementDao;
import web.bodymanagement.vo.BodyManagement;

public class BodyManagementDaoImpl<selectByUserIdAndDateRange> implements BodyManagementDao {
	private DataSource ds;

	public BodyManagementDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public int insertBodyData(BodyManagement bodyManagement) {
		String sql = "insert into bodymanagement (userId, height, weight, bodyFat, recordDate, BMI) values(?,?,?,?,?,?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, bodyManagement.getUserId());
			pstmt.setFloat(2, bodyManagement.getHeight());
			pstmt.setFloat(3, bodyManagement.getWeight());
			pstmt.setFloat(4, bodyManagement.getBodyFat());
			pstmt.setTimestamp(5, bodyManagement.getRecordDate());
			pstmt.setFloat(6, bodyManagement.getBmi());

			int result = pstmt.executeUpdate();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	
	@Override
	public int deleteBodyDataByRecordId(int recordId) {
		String sql = "delete from bodymanagement where recordId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, recordId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateBodyDate(BodyManagement bodyManagement) {
		String sql = "UPDATE bodymanagement SET height = ?, weight = ?, bodyFat = ?, BMI = ? WHERE recordId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setFloat(1, bodyManagement.getHeight());
			pstmt.setFloat(2, bodyManagement.getWeight());
			pstmt.setFloat(3, bodyManagement.getBodyFat());
			pstmt.setFloat(4, bodyManagement.getBmi());
			pstmt.setInt(5, bodyManagement.getRecordId());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<BodyManagement> selectByUserIdAndDateRange(int userId, Timestamp startDate, Timestamp endDate) {
		String sql = "select * from bodymanagement where userId = ? and recordDate between ? and ? order by recordDate desc;";
	    
	    try (Connection conn = ds.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	         
	        pstmt.setInt(1, userId);
	        pstmt.setTimestamp(2, new Timestamp(startDate.getTime()));
	        pstmt.setTimestamp(3, new Timestamp(endDate.getTime()));
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            var list = new ArrayList<BodyManagement>();
	            
	            while (rs.next()) {
	                BodyManagement bodyManagement = new BodyManagement();
	                bodyManagement.setRecordId(rs.getInt("recordId"));
	                bodyManagement.setWeight(rs.getFloat("weight"));
	                bodyManagement.setHeight(rs.getFloat("height"));
	                bodyManagement.setBodyFat(rs.getFloat("bodyFat"));
	                bodyManagement.setBmi(rs.getFloat("BMI"));
	                bodyManagement.setRecordDate(rs.getTimestamp("recordDate"));

	                list.add(bodyManagement);
	            }
	            return list;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public boolean isRecordDateExists(int userId, Timestamp recordDate) {
	    String sql = "SELECT * FROM bodymanagement WHERE userId = ? AND recordDate = ?";
	    try (Connection conn = ds.getConnection(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, userId);
	        pstmt.setTimestamp(2, recordDate);
	        
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}

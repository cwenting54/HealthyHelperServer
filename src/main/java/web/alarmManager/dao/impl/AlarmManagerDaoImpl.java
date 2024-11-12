package web.alarmManager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.alarmManager.dao.AlarmManagerDao;
import web.alarmManager.vo.AlarmManager;

public class AlarmManagerDaoImpl implements AlarmManagerDao {
	private DataSource ds;

	public AlarmManagerDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public List<AlarmManager> selectAlarmByUserId(int userId) {
		List<AlarmManager> alarms = new ArrayList<>();
		String sql = "SELECT alarmId, userId, alarmTime FROM alarmManager WHERE userId = ?";

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int alarmId = rs.getInt("alarmId");
				Time alarmTime = rs.getTime("alarmTime");
				alarms.add(new AlarmManager(alarmId, userId, alarmTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alarms;
	}

	@Override
	public int insertAlarmManager(AlarmManager alarmManager) {
		String sql = "INSERT INTO alarmManager (userId, alarmTime) VALUES (?, ?)";

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, alarmManager.getUserId());
			pstmt.setTime(2, alarmManager.getAlarmTime());
			pstmt.executeUpdate();

			int result = pstmt.executeUpdate();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int deleteAlarmManager(int alarmId) {
		String sql = "DELETE FROM alarmManager WHERE alarmId = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, alarmId);
            
            int result = pstmt.executeUpdate();
			return result;
			
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
	}

}

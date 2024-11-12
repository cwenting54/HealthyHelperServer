package web.alarmManager.dao;

import java.util.List;

import web.alarmManager.vo.AlarmManager;

public interface AlarmManagerDao {
	List<AlarmManager> selectAlarmByUserId(int userId);
	int insertAlarmManager(AlarmManager alarmManager);
	int deleteAlarmManager(int alarmId);
}

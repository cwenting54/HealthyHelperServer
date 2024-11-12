package web.alarmManager.vo;

import java.sql.Time;

public class AlarmManager {
    private Integer alarmId;   
    private Integer userId;     
    private Time alarmTime;


    public AlarmManager() {
    }


    public AlarmManager(int alarmId, int userId, Time alarmTime) {
        this.alarmId = alarmId;
        this.userId = userId;
        this.alarmTime = alarmTime;
    }

    // Getter 和 Setter 方法
    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Time getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Time alarmTime) {
        this.alarmTime = alarmTime;
    }


}

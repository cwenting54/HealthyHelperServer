package web.achievement.dao;

import java.util.List;

import web.achievement.vo.Achievement;

public interface AchievementDao {
	List<Achievement> selectAchievementsByUserId(int userId);
}

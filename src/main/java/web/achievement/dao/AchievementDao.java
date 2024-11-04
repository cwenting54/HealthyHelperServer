package web.achievement.dao;

import java.util.List;
import java.util.Map;

import web.achievement.vo.Achievement;

public interface AchievementDao {
	int insertAchievement(Achievement achievement);

	int selectDiaryTimesByUserID(int userId);

	int selectWeightTimesByUserID(int userId);

	Map<Integer, Integer> selectFinishPlanCountByUserID(int userId);

	List<Achievement> selectAchievementsByUserId(int userId);
}

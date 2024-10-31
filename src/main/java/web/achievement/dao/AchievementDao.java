package web.achievement.dao;

import java.util.List;

import web.achievement.vo.Achievement;

public interface AchievementDao {
	Achievement insertAchievement(int aTypeId);

	int selectDiaryTimesByUserID(int userId);

	int selectWeightTimesByUserID(int userId);

	List<Integer> selectFinishPlanByUserID(int userId);

	List<Achievement> selectAchievementsByUserId(int userId);
}

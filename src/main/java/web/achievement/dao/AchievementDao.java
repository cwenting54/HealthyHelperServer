package web.achievement.dao;

import java.util.List;
import java.util.Map;

import web.achievement.vo.Achievement;
import web.achievement.vo.AchievementList;

public interface AchievementDao {

	int insertAchievement(int userId, int aid);

	int selectDiaryTimesByUserID(int userId);

	int selectWeightTimesByUserID(int userId);

	Map<Integer, Integer> selectFinishPlanCountByUserID(int userId);

	List<Achievement> selectAchievementsByUserId(int userId);
	
	boolean isAchievementExists(int userId, int aId);
}

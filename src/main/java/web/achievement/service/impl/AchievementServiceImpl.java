package web.achievement.service.impl;

import java.util.Map;
import javax.naming.NamingException;
import web.achievement.dao.AchievementDao;
import web.achievement.dao.impl.AchievementDaoImpl;
import web.achievement.service.AchievementService;

public class AchievementServiceImpl implements AchievementService {
    private AchievementDao achievementDao;

    public AchievementServiceImpl() throws NamingException {
        achievementDao = new AchievementDaoImpl();
    }

    @Override
    public String addDieryAchievement(int userId) {
        int dieryCount = achievementDao.selectDiaryTimesByUserID(userId);
        int result = 0;
        if (dieryCount == 1 && !achievementDao.isAchievementExists(userId, 1)) {
            result = achievementDao.insertAchievement(userId, 1);
        } else if (dieryCount == 3 && !achievementDao.isAchievementExists(userId, 2)) {
            result = achievementDao.insertAchievement(userId, 2);
        } else if (dieryCount == 7 && !achievementDao.isAchievementExists(userId, 3)) {
            result = achievementDao.insertAchievement(userId, 3);
        } else if (dieryCount == 30 && !achievementDao.isAchievementExists(userId, 4)) {
            result = achievementDao.insertAchievement(userId, 4);
        } else if (dieryCount == 90 && !achievementDao.isAchievementExists(userId, 5)) {
            result = achievementDao.insertAchievement(userId, 5);
        } else if (dieryCount == 180 && !achievementDao.isAchievementExists(userId, 6)) {
            result = achievementDao.insertAchievement(userId, 6);
        } else if (dieryCount == 365 && !achievementDao.isAchievementExists(userId, 7)) {
            result = achievementDao.insertAchievement(userId, 7);
        }
        return result > 0 ? "新增食物紀錄成就" : "尚未達標";
    }

    @Override
    public String addWeightAchievement(int userId) {
        int weightCount = achievementDao.selectWeightTimesByUserID(userId);
        int result = 0;
        if (weightCount == 1 && !achievementDao.isAchievementExists(userId, 8)) {
            result = achievementDao.insertAchievement(userId, 8);
        } else if (weightCount == 10 && !achievementDao.isAchievementExists(userId, 9)) {
            result = achievementDao.insertAchievement(userId, 9);
        } else if (weightCount == 25 && !achievementDao.isAchievementExists(userId, 10)) {
            result = achievementDao.insertAchievement(userId, 10);
        } else if (weightCount == 50 && !achievementDao.isAchievementExists(userId, 11)) {
            result = achievementDao.insertAchievement(userId, 11);
        } else if (weightCount == 100 && !achievementDao.isAchievementExists(userId, 12)) {
            result = achievementDao.insertAchievement(userId, 12);
        } else if (weightCount == 250 && !achievementDao.isAchievementExists(userId, 13)) {
            result = achievementDao.insertAchievement(userId, 13);
        } else if (weightCount == 500 && !achievementDao.isAchievementExists(userId, 14)) {
            result = achievementDao.insertAchievement(userId, 14);
        }
        return result > 0 ? "新增體重紀錄成就" : "尚未達標";
    }

    @Override
    public String addPlanAchievement(int userId) {
        Map<Integer, Integer> planCountMap = achievementDao.selectFinishPlanCountByUserID(userId);
        int result = 0;

        for (int i = 1; i <= 4; i++) {
            Integer planCount = planCountMap.get(i);
            if (planCount != null) {
                if (planCount == 1 && !achievementDao.isAchievementExists(userId, 15 + (i - 1) * 4)) {
                    result = achievementDao.insertAchievement(userId, 15 + (i - 1) * 4);
                } else if (planCount == 3 && !achievementDao.isAchievementExists(userId, 16 + (i - 1) * 4)) {
                    result = achievementDao.insertAchievement(userId, 16 + (i - 1) * 4);
                } else if (planCount == 7 && !achievementDao.isAchievementExists(userId, 17 + (i - 1) * 4)) {
                    result = achievementDao.insertAchievement(userId, 17 + (i - 1) * 4);
                } else if (planCount == 30 && !achievementDao.isAchievementExists(userId, 18 + (i - 1) * 4)) {
                    result = achievementDao.insertAchievement(userId, 18 + (i - 1) * 4);
                }
            }
        }

        return result > 0 ? "新增飲食計畫成就" : "尚未達標";
    }
}

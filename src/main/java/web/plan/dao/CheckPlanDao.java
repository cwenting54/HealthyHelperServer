package web.plan.dao;

import java.sql.Date;
import java.util.List;

import web.plan.vo.DiaryAll;
import web.plan.vo.PlanWithCategory;

public interface CheckPlanDao {
	PlanWithCategory selectPlanAll(Integer userId, Integer userDietPlanId);
	List<DiaryAll> selectDiaryAll(Integer userId, Date startDate,Date endDate);
	int updateByUserIdAndUserDietPlanID(Integer userId, Integer userDietPlanId, Integer finishstate);
}

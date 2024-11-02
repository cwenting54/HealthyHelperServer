package web.plan.dao;

import java.util.List;

import web.plan.vo.PlanWithCategory;

public interface PlanManageDao {
	List<PlanWithCategory> selectByUserIDAndFinishState(Integer userId, Integer finishstate);
	int deleteByUserIdAndPlanIdAndFinishState(Integer userId,Integer userDietPlanId, Integer finishstate);
}

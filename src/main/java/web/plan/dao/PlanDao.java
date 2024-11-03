package web.plan.dao;

import web.plan.vo.PlanWithCategory;

public interface PlanDao {
	PlanWithCategory selectSingleByUserIDAndFinishState(Integer userId, Integer finishstate);
	int insertByUserID(PlanWithCategory planWithCategory);
}

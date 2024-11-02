package web.plan.dao;

import java.util.List;

import web.plan.vo.PlanWithCategory;

public interface PlanDao {
	PlanWithCategory selectSingleByUserIDAndFinishState(Integer userId, Integer finishstate);
}

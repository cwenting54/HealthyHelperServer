package web.plan.dao;

import java.util.List;

import web.plan.vo.PlanWithCategory;

public interface PlanDao {
//	int insert(Plan plan);
//	int delete();
	
	List<PlanWithCategory> selectByUserIDAndFinishState(Integer userId, Integer finishstate);

}

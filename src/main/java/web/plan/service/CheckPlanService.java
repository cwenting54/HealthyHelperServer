package web.plan.service;

import java.sql.Date;
import java.util.List;

import web.plan.vo.DiaryAll;
import web.plan.vo.PlanWithCategory;

public interface CheckPlanService {
	PlanWithCategory GetSelectedPlan(Integer userId, Integer userDietPlanId);
	List<DiaryAll> GetDiaryWithDate(Integer userId, Date startDate, Date endDate);
	String SetPlanComplete(Integer userId, Integer userDietPlanId);
}

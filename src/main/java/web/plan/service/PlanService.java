package web.plan.service;

import java.util.List;

import web.plan.vo.PlanWithCategory;

public interface PlanService {
	List<PlanWithCategory> getUnFinishedPlan(PlanWithCategory plan);
}

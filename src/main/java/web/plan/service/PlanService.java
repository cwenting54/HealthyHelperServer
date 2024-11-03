package web.plan.service;

import web.plan.vo.PlanWithCategory;

public interface PlanService {
	PlanWithCategory getplan(PlanWithCategory planWithCategory);
	String insertPlan(PlanWithCategory planWithCategory);
}

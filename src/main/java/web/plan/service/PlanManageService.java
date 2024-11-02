package web.plan.service;

import java.util.List;

import web.plan.vo.PlanWithCategory;

public interface PlanManageService {
	String DeletePlan(PlanWithCategory plan);
	List<PlanWithCategory> getPlanList(PlanWithCategory plan);
}

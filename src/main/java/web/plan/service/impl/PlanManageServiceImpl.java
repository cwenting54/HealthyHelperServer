package web.plan.service.impl;

import java.util.List;

import javax.naming.NamingException;

import web.plan.dao.PlanManageDao;
import web.plan.dao.impl.PlanManageDaoImpl;
import web.plan.service.PlanManageService;
import web.plan.vo.PlanWithCategory;

public class PlanManageServiceImpl implements PlanManageService{
	PlanManageDao planManageDao;
	
	public PlanManageServiceImpl() throws NamingException {
		planManageDao = new PlanManageDaoImpl();
	}



	@Override
	public String DeletePlan(PlanWithCategory plan) {
		Integer userID = plan.getUserId();
		if(userID == null)
		{
			System.out.println("userID is null");
			return "userID is null";
		}
		Integer finishsate = plan.getFinishstate();
		if (finishsate == null) {
			System.out.println("finishstate is null");
			return "finishstate is null";
		}
		Integer userDietPlanId = plan.getUserDietPlanId();
		if (userDietPlanId == null) {
			System.out.println("userDietPlanId is null");
			return "userDietPlanId is null";
		}
		
		int result = planManageDao.deleteByUserIdAndPlanIdAndFinishState(userID, userDietPlanId, finishsate);
		
		
		return result> 0 ? null:"刪除計畫錯誤";
	}



	@Override
	public List<PlanWithCategory> getPlanList(PlanWithCategory planWithCategory) {
		Integer userID = planWithCategory.getUserId();
		if(userID == null)
		{
			System.out.println("userID is null");
			return null;
		}
		Integer finishsate = planWithCategory.getFinishstate();
		if (finishsate == null) {
			System.out.println("finishstate is null");
			return null;
		}
		if(planManageDao.selectByUserIDAndFinishState(userID, finishsate) == null)
		{
			System.out.println("select is null");
			return null;
		}
		
		System.out.println("selectlist is: " + planManageDao.selectByUserIDAndFinishState(userID, finishsate));
		return planManageDao.selectByUserIDAndFinishState(userID, finishsate);
	}

}

package web.plan.service.impl;

import java.util.List;

import javax.naming.NamingException;

import web.plan.dao.PlanDao;
import web.plan.dao.impl.PlanDaoImpl;
import web.plan.service.PlanService;
import web.plan.vo.PlanWithCategory;

public class PlanServiceImpl implements PlanService{
	PlanDao planDao;
	

	public PlanServiceImpl() throws NamingException {
		planDao = new PlanDaoImpl();
	}


	@Override
	public List<PlanWithCategory> getUnFinishedPlan(PlanWithCategory plan) {
		Integer userID = plan.getUserId();
		if(userID == null)
		{
			System.out.println("userID is null");
			return null;
		}
		Integer finishsate = plan.getFinishstate();
		if (finishsate == null) {
			System.out.println("finishstate is null");
			return null;
		}
		if(planDao.selectByUserIDAndFinishState(userID, finishsate) == null)
		{
			System.out.println("select is null");
			return null;
		}
		
		System.out.println("selectlist is: " + planDao.selectByUserIDAndFinishState(userID, finishsate));
		return planDao.selectByUserIDAndFinishState(userID, finishsate);
	}

}

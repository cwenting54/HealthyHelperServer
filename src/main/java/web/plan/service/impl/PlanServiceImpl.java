package web.plan.service.impl;


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
	public PlanWithCategory getplan(PlanWithCategory planWithCategory) {
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
		if(planDao.selectSingleByUserIDAndFinishState(userID, finishsate) == null)
		{
			System.out.println("select is null");
			return null;
		}
		System.out.println("selectsingle is: " + planDao.selectSingleByUserIDAndFinishState(userID, finishsate));
		return planDao.selectSingleByUserIDAndFinishState(userID, finishsate);
	}


}

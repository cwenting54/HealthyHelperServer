package web.plan.service.impl;


import java.sql.Timestamp;

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


	@Override
	public String insertPlan(PlanWithCategory planWithCategory) {
		
		Integer categoryId = planWithCategory.getCategoryID();
		if(categoryId == null)
		{
			System.out.println("categoryId is null");
			return "categoryId is null";
		}
		Integer userID = planWithCategory.getUserId();
		if(userID == null)
		{
			System.out.println("userID is null");
			return "userID is null";
		}
		Timestamp startDatetime = planWithCategory.getStartDateTime();
		if(startDatetime == null)
		{
			System.out.println("startDatetime is null");
			return "startDatetime is null";
		}
		Timestamp endDatetime = planWithCategory.getEndDateTime();
		if(endDatetime == null)
		{
			System.out.println("endDatetime is null");
			return "endDatetime is null";
		}
		Integer finishsate = planWithCategory.getFinishstate();
		if (finishsate == null) {
			System.out.println("finishstate is null");
			return "finishstate is null";
		}
		Float fatgoal = planWithCategory.getFatgoal();
		if (fatgoal == null) {
			System.out.println("fatgoal is null");
			return "fatgoal is null";
		}
		Float carbongoal = planWithCategory.getCarbongoal();
		if (carbongoal == null) {
			System.out.println("carbongoal is null");
			return "carbongoal is null";
		}
		Float proteingoal = planWithCategory.getProteingoal();
		if (proteingoal == null) {
			System.out.println("proteingoal is null");
			return "proteingoal is null";
		}
		Float Caloriesgoal = planWithCategory.getCaloriesgoal();
		if (Caloriesgoal == null) {
			System.out.println("Caloriesgoal is null");
			return "Caloriesgoal is null";
		}
		
		int result = planDao.insertByUserID(planWithCategory);
		
		return result > 0 ? null:"新增計畫錯誤";
	}


}

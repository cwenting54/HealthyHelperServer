package web.plan.service.impl;

import java.sql.Date;
import java.util.List;
import javax.naming.NamingException;
import web.plan.dao.CheckPlanDao;
import web.plan.dao.impl.CheckPlanDaoImpl;
import web.plan.service.CheckPlanService;
import web.plan.vo.DiaryAll;
import web.plan.vo.PlanWithCategory;

public class CheckPlanServiceImpl implements CheckPlanService{
	CheckPlanDao checkPlanDao;
	

	public CheckPlanServiceImpl() throws NamingException {
		checkPlanDao = new CheckPlanDaoImpl();
	}

	@Override
	public PlanWithCategory GetSelectedPlan(Integer userId, Integer userDietPlanId) {
		if(userId == null) {
			System.out.println("userID is null");
			return null;
		}
		if(userDietPlanId == null) {
			System.out.println("userDietPlanId is null");
			return null;
		}
		if (checkPlanDao.selectPlanAll(userId, userDietPlanId) == null) {
			System.out.println("no such plan to select");
			return null;
		}
		
		return checkPlanDao.selectPlanAll(userId, userDietPlanId);
	}

	@Override
	public List<DiaryAll> GetDiaryWithDate(Integer userId, Date startDate, Date endDate) {
		if(userId == null) {
			System.out.println("userID is null");
			return null;
		}
		if (startDate == null) {
			System.out.println("startDate is null");
			return null;
		}
		if (endDate == null) {
			System.out.println("endDate is null");
			return null;
		}
		if (checkPlanDao.selectDiaryAll(userId, startDate, endDate) == null) {
			System.out.println("no such diary to select");
			return null;
		}
		
		return checkPlanDao.selectDiaryAll(userId, startDate, endDate);
	}

	@Override
	public String SetPlanComplete(Integer userId, Integer userDietPlanId) {
		if(userId == null) {
			System.out.println("userID is null");
			return null;
		}
		if(userDietPlanId == null) {
			System.out.println("userDietPlanId is null");
			return null;
		}
		int result = checkPlanDao.updateByUserIdAndUserDietPlanID(userId, userDietPlanId);
		return result > 0 ? null:"更新錯誤";
	}

}

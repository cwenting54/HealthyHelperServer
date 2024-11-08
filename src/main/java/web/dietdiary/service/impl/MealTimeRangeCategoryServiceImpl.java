package web.dietdiary.service.impl;

import java.util.ArrayList;

import javax.naming.NamingException;

import web.dietdiary.dao.impl.MealTimeRangeCategoryDao;
import web.dietdiary.dao.impl.MealTimeRangeCategoryDaoImpl;
import web.dietdiary.vo.MealTimeRangeCategoryVO;

public class MealTimeRangeCategoryServiceImpl implements MealTimeRangeCategoryService {

	private MealTimeRangeCategoryDao mealTimeRangeCategoryDao;
	
	public MealTimeRangeCategoryServiceImpl() throws NamingException {
		this.mealTimeRangeCategoryDao = new MealTimeRangeCategoryDaoImpl(null);	
	}
	
	@Override
	public String change(MealTimeRangeCategoryVO mealTimeRangeCategoryVO) {
		String errorMessage = "";
		ArrayList<MealTimeRangeCategoryVO> newMealTimeRangeCategory = new ArrayList<MealTimeRangeCategoryVO>();
		newMealTimeRangeCategory = this.mealTimeRangeCategoryDao.selectByUserId(mealTimeRangeCategoryVO.getUserId());
		if(newMealTimeRangeCategory.isEmpty()) {
			errorMessage = this.insert(mealTimeRangeCategoryVO);
			return errorMessage;
		}
		errorMessage = this.update(mealTimeRangeCategoryVO);
		return errorMessage;
	}

	@Override
	public String insert(MealTimeRangeCategoryVO mealTimeRangeCategoryVO) {
		String errorMessage = "";
		int affectedRows = 1;
		affectedRows = this.mealTimeRangeCategoryDao.insert(mealTimeRangeCategoryVO);
		if(affectedRows != 1) {
			errorMessage = "Unknown error";
			return errorMessage;
		}
		errorMessage = "";
		return errorMessage;
	}

	@Override
	public String update(MealTimeRangeCategoryVO mealTimeRangeCategoryVO) {
		String errorMessage = "";
		int affectedRows = 1;
		affectedRows = this.mealTimeRangeCategoryDao.update(mealTimeRangeCategoryVO);
		if(affectedRows != 1) {
			errorMessage = "Unknown error";
			return errorMessage;
		}
		errorMessage = "";
		return errorMessage;
	}

	@Override
	public ArrayList<MealTimeRangeCategoryVO> select(MealTimeRangeCategoryVO mealTimeRangeCategoryVO) {
		ArrayList<MealTimeRangeCategoryVO> mealTimeRangeCategoryVOs = this.mealTimeRangeCategoryDao.selectByUserId(mealTimeRangeCategoryVO.getUserId());
		System.out.println("In MealTimeRangeCategoryServiceImpl, mealTimeRangeCategories:"+mealTimeRangeCategoryVOs.toString());
		return mealTimeRangeCategoryVOs;
	}
}

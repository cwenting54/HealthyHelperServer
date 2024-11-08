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
	public String change(MealTimeRangeCategoryVO mealTimeRangeCategory) {
		String errorMessage = "";
		ArrayList<MealTimeRangeCategoryVO> newMealTimeRangeCategory = new ArrayList<MealTimeRangeCategoryVO>();
		newMealTimeRangeCategory = this.mealTimeRangeCategoryDao.selectByUserId(mealTimeRangeCategory.getUserId());
		if(newMealTimeRangeCategory.isEmpty()) {
			errorMessage = this.insert(mealTimeRangeCategory);
			return errorMessage;
		}
		errorMessage = this.update(mealTimeRangeCategory);
		return errorMessage;
	}

	@Override
	public String insert(MealTimeRangeCategoryVO mealTimeRangeCategory) {
		String errorMessage = "";
		int affectedRows = 1;
		affectedRows = this.mealTimeRangeCategoryDao.insert(mealTimeRangeCategory);
		if(affectedRows != 1) {
			errorMessage = "Unknown error";
			return errorMessage;
		}
		errorMessage = "";
		return errorMessage;
	}

	@Override
	public String update(MealTimeRangeCategoryVO mealTimeRangeCategory) {
		String errorMessage = "";
		int affectedRows = 1;
		affectedRows = this.mealTimeRangeCategoryDao.update(mealTimeRangeCategory);
		if(affectedRows != 1) {
			errorMessage = "Unknown error";
			return errorMessage;
		}
		errorMessage = "";
		return errorMessage;
	}

	@Override
	public ArrayList<MealTimeRangeCategoryVO> select(MealTimeRangeCategoryVO mealTimeRangeCategory) {
		ArrayList<MealTimeRangeCategoryVO> mealTimeRangeCategories = this.mealTimeRangeCategoryDao.selectByUserId(mealTimeRangeCategory.getUserId());
		System.out.println("In MealTimeRangeCategoryServiceImpl, mealTimeRangeCategories:"+mealTimeRangeCategories.toString());
		return mealTimeRangeCategories;
	}
}

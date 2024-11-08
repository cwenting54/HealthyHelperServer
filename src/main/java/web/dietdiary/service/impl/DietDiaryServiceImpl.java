package web.dietdiary.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.naming.NamingException;

import web.dietdiary.dao.impl.DietDiaryDao;
import web.dietdiary.dao.impl.DietDiaryDaoImpl;
import web.dietdiary.dao.impl.FoodDao;
import web.dietdiary.dao.impl.FoodDaoImpl;
import web.dietdiary.dao.impl.FoodItemDao;
import web.dietdiary.dao.impl.FoodItemDaoImpl;
import web.dietdiary.dao.impl.NutritionDao;
import web.dietdiary.dao.impl.NutritionDaoImpl;
import web.dietdiary.handler.impl.NutritionHandler;
import web.dietdiary.handler.impl.NutritionHandlerImpl;
import web.dietdiary.vo.DietDiaryVO;
import web.dietdiary.vo.FoodVO;
import web.dietdiary.vo.FoodItemVO;
import web.dietdiary.vo.NutritionVO;

public class DietDiaryServiceImpl implements DietDiaryService {

	private DietDiaryDao dietDiaryDao;
	private FoodItemDao foodItemDao;
	private FoodDao foodDao;
	private NutritionDao nutritionDao;
	private NutritionHandler nutritionHandler;

	public DietDiaryServiceImpl() throws NamingException {
		this.dietDiaryDao = new DietDiaryDaoImpl(null);
		this.foodItemDao = new FoodItemDaoImpl(null);
		this.foodDao = new FoodDaoImpl(null);
		this.nutritionDao = new NutritionDaoImpl();
		this.nutritionHandler = new NutritionHandlerImpl();
	}

	@Override
	public String insert(DietDiaryVO dietDiaryVO) {
		String errorMessage = "";
		boolean duplicatedDate = false;
		ArrayList<DietDiaryVO> dietDiaryVOs = new ArrayList<DietDiaryVO>();
		
		dietDiaryVOs = this.search(dietDiaryVO, 1);
		if(dietDiaryVOs==null) {
			errorMessage = "Unknown error during execution of searching data!!!";
			return errorMessage;
		}
		if(!dietDiaryVOs.isEmpty()) {
			errorMessage = "Existing error!!! The userId and createDate in foodDiary table has already exists. Can not insert it!!!";
			return errorMessage;
		}
		
		errorMessage = this.dietDiaryDao.insert(dietDiaryVO);
		return errorMessage;
	}

	@Override
	public ArrayList<DietDiaryVO> search(DietDiaryVO dietDiaryVO, int mode) {
		if (mode == 1) {
			return this.searchByDate(dietDiaryVO);
		} else if (mode == 2) {
			return this.searchByTime(dietDiaryVO);
		} else if (mode == 3) {
			return this.searchByDateAndTime(dietDiaryVO);
		}
		return null;
	}

	@Override
	public ArrayList<DietDiaryVO> searchByDate(DietDiaryVO dietDiaryVO) {
		int userId = dietDiaryVO.getUserId();
		Date createDate = dietDiaryVO.getCreateDate();
		return this.dietDiaryDao.selectByDate(userId, createDate);
	}

	@Override
	public ArrayList<DietDiaryVO> searchByTime(DietDiaryVO dietDiaryVO) {
		int userId = dietDiaryVO.getUserId();
		Time createTime = dietDiaryVO.getCreateTime();
		return this.dietDiaryDao.selectByTime(userId, createTime);
	}

	@Override
	public ArrayList<DietDiaryVO> searchByDateAndTime(DietDiaryVO dietDiaryVO) {
		int userId = dietDiaryVO.getUserId();
		Date createDate = dietDiaryVO.getCreateDate();
		Time createTime = dietDiaryVO.getCreateTime();
		return this.dietDiaryDao.selectByDateAndTime(userId, createDate, createTime);
	}

	@Override
	public DietDiaryVO plusNutrition(DietDiaryVO dietDiaryVO, NutritionVO nutritionVO) {
		DietDiaryVO result = new DietDiaryVO();

		result.setUserId(dietDiaryVO.getUserId());
		result.setDiaryId(dietDiaryVO.getDiaryId());
		result.setCreateDate(dietDiaryVO.getCreateDate());
		result.setCreateTime(dietDiaryVO.getCreateTime());
		result.setTotalFat(dietDiaryVO.getTotalFat() + nutritionVO.getFat());
		result.setTotalFiber(dietDiaryVO.getTotalFiber() + nutritionVO.getFiber());
		result.setTotalProtein(dietDiaryVO.getTotalProtein() + nutritionVO.getProtein());
		result.setTotalCarbon(dietDiaryVO.getTotalCarbon() + nutritionVO.getCarbon());
		result.setTotalSugar(dietDiaryVO.getTotalSugar() + nutritionVO.getSugar());
		result.setTotalSodium(dietDiaryVO.getTotalSodium() + nutritionVO.getSodium());
		result.setTotalCalories(dietDiaryVO.getTotalCalories() + nutritionVO.getCalories());

		return result;
	}

	@Override
	public String updateDietDiary(int foodId, Date date) {
		try {
			FoodItemVO foodItemVO = foodItemDao.select(foodId);
			FoodVO foodVO = foodDao.selectByFoodId(foodId);
			NutritionVO nutritionVO = nutritionDao.getNutritionFromFood(foodVO);
			int diaryId = foodItemVO.getDiaryId();
			Double grams = foodItemVO.getGrams();
			
			DietDiaryVO dietDiaryVO = dietDiaryDao.selectByDiaryIdAndDate(diaryId, date);
			if (dietDiaryVO == null) {
				throw new Exception("Unknown error!!!");
			}

			nutritionVO = nutritionHandler.multiply(nutritionVO, grams);
					
			DietDiaryVO updatedDietDiary = this.plusNutrition(dietDiaryVO, nutritionVO);
			this.dietDiaryDao.updateByDiaryId(updatedDietDiary);
			
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getStackTrace().toString();
		}
	}

	@Override
	public ArrayList<DietDiaryVO> sort(ArrayList<DietDiaryVO> dietDiaryVOs, int mode, boolean isAscending) {
		if (mode == 1) {
			return this.sortByDate(dietDiaryVOs,isAscending);
		}
		return null;
	}

	@Override
	public ArrayList<DietDiaryVO> sortByDate(ArrayList<DietDiaryVO> dietDiaryVOs, boolean isAscending) {
		DietDiaryVO[] newDietDiaries = (DietDiaryVO[]) dietDiaryVOs.clone();
		if(isAscending) {
			Arrays.sort(newDietDiaries);
		}else {
			Arrays.sort(newDietDiaries);
			Collections.reverse(Arrays.asList(newDietDiaries));
		}
		ArrayList<DietDiaryVO> result = (ArrayList<DietDiaryVO>) Arrays.asList(newDietDiaries);
		return result;
	}
}

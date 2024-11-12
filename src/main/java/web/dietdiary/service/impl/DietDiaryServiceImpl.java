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
import web.dietdiary.vo.FoodItemVO;
import web.dietdiary.vo.FoodVO;
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
	public int insert(DietDiaryVO dietDiary) {
		System.out.println("--------------------------------------------");
        System.out.println("DietDiaryServiceImpl class, insert method was  called.");

		int affectedRows = 0;
		ArrayList<DietDiaryVO> targetDietDiaries = this.search(dietDiary, 1);	
		System.out.println();
		System.out.println();
		System.out.println("targetDietDiaries:"+targetDietDiaries);
		System.out.println();
		System.out.println();
		
		if(targetDietDiaries==null) {
	        System.out.println("DietDiaryServiceImpl class, insert method  was finished to called.");
			System.out.println("--------------------------------------------");
			return -2;
		}
		if(!targetDietDiaries.isEmpty()) {
	        System.out.println("DietDiaryServiceImpl class, insert method  was finished to called.");
			System.out.println("--------------------------------------------");
			return -1;
		}
		
		affectedRows = this.dietDiaryDao.insert(dietDiary);
		
		System.out.println();
		System.out.println();
		System.out.println("affectedRows:"+affectedRows);
		System.out.println();
		System.out.println();
		
        System.out.println("DietDiaryServiceImpl class, insert method  was finished to called.");
		System.out.println("--------------------------------------------");
		return affectedRows;
	}

	@Override
	public ArrayList<DietDiaryVO> search(DietDiaryVO dietDiary, int mode) {
		System.out.println("--------------------------------------------");
        System.out.println("DietDiaryServiceImpl class, search method was called.");
        
		System.out.println();
		System.out.println();
		System.out.println("dietDiary:"+dietDiary);
		System.out.println();
		System.out.println();        
		
		if (mode == 1) {
	        System.out.println("DietDiaryServiceImpl class, search method was finished to called.");
			System.out.println("--------------------------------------------");
			return this.searchByDate(dietDiary);
		} else if (mode == 2) {
	        System.out.println("DietDiaryServiceImpl class, search method was finished to called.");
			System.out.println("--------------------------------------------");
			return this.searchByTime(dietDiary);
		} else if (mode == 3) {
	        System.out.println("DietDiaryServiceImpl class, search method was finished to called.");
			System.out.println("--------------------------------------------");
			return this.searchByDateAndTime(dietDiary);
		}
        System.out.println("DietDiaryServiceImpl class, search method was finished to called.");
		System.out.println("--------------------------------------------");
		return null;
	}

	@Override
	public ArrayList<DietDiaryVO> searchByDate(DietDiaryVO dietDiary) {
		int userId = dietDiary.getUserID();
		Date createDate = dietDiary.getCreateDate();
		return this.dietDiaryDao.selectByDate(userId, createDate);
	}

	@Override
	public ArrayList<DietDiaryVO> searchByTime(DietDiaryVO dietDiary) {
		int userId = dietDiary.getUserID();
		Time createTime = dietDiary.getCreateTime();
		return this.dietDiaryDao.selectByTime(userId, createTime);
	}

	@Override
	public ArrayList<DietDiaryVO> searchByDateAndTime(DietDiaryVO dietDiary) {
		int userId = dietDiary.getUserID();
		Date createDate = dietDiary.getCreateDate();
		Time createTime = dietDiary.getCreateTime();
		return this.dietDiaryDao.selectByDateAndTime(userId, createDate, createTime);
	}

	@Override
	public DietDiaryVO plusNutrition(DietDiaryVO dietDiary, NutritionVO nutrition) {
		DietDiaryVO result = new DietDiaryVO();

		result.setUserID(dietDiary.getUserID());
		result.setDiaryID(dietDiary.getDiaryID());
		result.setCreateDate(dietDiary.getCreateDate());
		result.setCreateTime(dietDiary.getCreateTime());
		result.setTotalFat(dietDiary.getTotalFat() + nutrition.getFat());
		result.setTotalFiber(dietDiary.getTotalFiber() + nutrition.getFiber());
		result.setTotalProtein(dietDiary.getTotalProtein() + nutrition.getProtein());
		result.setTotalCarbon(dietDiary.getTotalCarbon() + nutrition.getCarbon());
		result.setTotalSugar(dietDiary.getTotalSugar() + nutrition.getSugar());
		result.setTotalSodium(dietDiary.getTotalSodium() + nutrition.getSodium());
		result.setTotalCalories(dietDiary.getTotalCalories() + nutrition.getCalories());

		return result;
	}

	@Override
	public String updateDietDiary(int foodId, Date date) {
		try {
			FoodItemVO sourceFoodItem = new FoodItemVO();
			sourceFoodItem.setFoodID(foodId);
			ArrayList<FoodItemVO> foodItems = this.foodItemDao.selectByFoodId(sourceFoodItem);
			FoodItemVO firstFoodItem = foodItems.get(0);
			FoodVO food = foodDao.selectByFoodId(foodId);
			NutritionVO nutrition = nutritionDao.getNutritionFromFood(food);
			int diaryId = firstFoodItem.getDiaryID();
			Double grams = firstFoodItem.getGrams();
			
			DietDiaryVO dietDiary = dietDiaryDao.selectByDiaryIdAndDate(diaryId, date);
			if (dietDiary == null) {
				throw new Exception("Unknown error!!!");
			}

			nutrition = nutritionHandler.multiply(nutrition, grams);
					
			DietDiaryVO updatedDietDiary = this.plusNutrition(dietDiary, nutrition);
			this.dietDiaryDao.updateByDiaryId(updatedDietDiary);
			
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getStackTrace().toString();
		}
	}

	@Override
	public ArrayList<DietDiaryVO> sort(ArrayList<DietDiaryVO> dietDiaries, int mode, boolean isAscending) {
		if (mode == 1) {
			return this.sortByDate(dietDiaries,isAscending);
		}
		return null;
	}

	@Override
	public ArrayList<DietDiaryVO> sortByDate(ArrayList<DietDiaryVO> dietDiaries, boolean isAscending) {
		DietDiaryVO[] newDietDiaries = (DietDiaryVO[]) dietDiaries.clone();
		if(isAscending) {
			Arrays.sort(newDietDiaries);
		}else {
			Arrays.sort(newDietDiaries);
			Collections.reverse(Arrays.asList(newDietDiaries));
		}
		ArrayList<DietDiaryVO> result = (ArrayList<DietDiaryVO>) Arrays.asList(newDietDiaries);
		return result;
	}

	@Override
	public ArrayList<DietDiaryVO> selectByUserIdAndDate(DietDiaryVO dietDiary) {
		return this.dietDiaryDao.selectByUserIdAndDate(dietDiary);
	}
}

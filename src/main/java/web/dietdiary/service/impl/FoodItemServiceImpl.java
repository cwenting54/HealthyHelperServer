package web.dietdiary.service.impl;

import java.util.ArrayList;

import javax.naming.NamingException;

import web.dietdiary.dao.impl.FoodDao;
import web.dietdiary.dao.impl.FoodDaoImpl;
import web.dietdiary.dao.impl.FoodItemDao;
import web.dietdiary.dao.impl.FoodItemDaoImpl;
import web.dietdiary.vo.FoodItemVO;

public class FoodItemServiceImpl implements FoodItemService{

	private FoodItemDao foodItemDao;

	public FoodItemServiceImpl() throws NamingException {
		this.foodItemDao = new FoodItemDaoImpl(null);
	}
	
	@Override
	public int insert(FoodItemVO foodItem) {
		return this.foodItemDao.insert(foodItem);
	}

	@Override
	public int update(FoodItemVO foodItem) {
		return this.foodItemDao.update(foodItem);
	}

	@Override
	public int updateMealCategoryId(FoodItemVO foodItem) {
		return this.foodItemDao.updateMealCategoryId(foodItem);
	}

	@Override
	public int tryToInsert(FoodItemVO foodItem) {
		int affectedRows = -1;
		ArrayList<FoodItemVO> foodItems = this.foodItemDao.selectByDiaryId(foodItem);
		System.out.println("In tryToInsert method call,foodItems:"+foodItems);
		if(foodItems == null) {
			return -1;
		}
		if(foodItems.isEmpty()) {
			affectedRows = this.foodItemDao.insert(foodItem);
			return affectedRows;
		}
		affectedRows = this.foodItemDao.update(foodItem);
		return affectedRows;
	}

	@Override
	public ArrayList<FoodItemVO> selectByDiaryId(FoodItemVO foodItem) {
		return this.foodItemDao.selectByDiaryId(foodItem);
	}

	@Override
	public ArrayList<FoodItemVO> selectByDiaryIdAndMealCategoryId(FoodItemVO foodItem) {
		return this.foodItemDao.selectByDiaryIdAndMealCategoryId(foodItem);
	}
}

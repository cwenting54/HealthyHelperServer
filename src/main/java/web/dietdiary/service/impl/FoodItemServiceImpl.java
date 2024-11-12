package web.dietdiary.service.impl;

import java.util.ArrayList;

import javax.naming.NamingException;

import web.dietdiary.dao.impl.FoodDao;
import web.dietdiary.dao.impl.FoodDaoImpl;
import web.dietdiary.dao.impl.FoodItemDao;
import web.dietdiary.dao.impl.FoodItemDaoImpl;
import web.dietdiary.vo.FoodItemVO;

public class FoodItemServiceImpl implements FoodItemService {

	private FoodItemDao foodItemDao;

	public FoodItemServiceImpl() throws NamingException {
		this.foodItemDao = new FoodItemDaoImpl(null);
	}

	@Override
	public int insert(FoodItemVO foodItem) {
		return this.foodItemDao.insert(foodItem);
	}

	@Override
	public int updateByFoodId(FoodItemVO foodItem) {
		System.out.println("------------------------------------------------------------");
		System.out.println("FoodItemServiceImpl class updateByFoodId method was called.`");
		
		System.out.println();
        System.out.println();
        System.out.println("foodItem:"+foodItem);
        System.out.println();
        System.out.println();
        
		System.out.println("FoodItemServiceImpl class updateByFoodId method was finished to called.`");
		System.out.println("------------------------------------------------------------");
		return this.foodItemDao.updateByFoodId(foodItem);
	}

	@Override
	public int updateByDiaryIdAndFoodId(FoodItemVO foodItem) {
		System.out.println("------------------------------------------------------------");
		System.out.println("FoodItemServiceImpl class updateByDiaryIdAndFoodId method was called.`");
		
		System.out.println();
        System.out.println();
        System.out.println("foodItem:"+foodItem);
        System.out.println();
        System.out.println();
        
		System.out.println("FoodItemServiceImpl class updateByDiaryIdAndFoodId method was finished to called.`");
		System.out.println("------------------------------------------------------------");
		return this.foodItemDao.updateByDiaryIdAndFoodId(foodItem);
	}

	@Override
	public int tryToInsert(FoodItemVO foodItem) {
		System.out.println("---------------------------------------------------------------");
		System.out.println("FoodItemServiceImpl class tryToInsert method was called.");
		int affectedRows = -1;
		ArrayList<FoodItemVO> queriedFoodItems = this.foodItemDao.selectByDiaryIdAndFoodId(foodItem);

		System.out.println();
		System.out.println();
		System.out.println("queriedFoodItems:" + queriedFoodItems);
		System.out.println();
		System.out.println();

		if (queriedFoodItems == null) {
			System.out.println("FoodItemServiceImpl class tryToInsert method was finished to called.");
			System.out.println("---------------------------------------------------------------");
			return -1;
		}
		if (queriedFoodItems.isEmpty()) {
			affectedRows = this.foodItemDao.insert(foodItem);
			System.out.println("FoodItemServiceImpl class tryToInsert method was finished to called.");
			System.out.println("---------------------------------------------------------------");
			return affectedRows;
		}
		affectedRows = this.foodItemDao.updateByDiaryIdAndFoodId(foodItem);
		System.out.println("FoodItemServiceImpl class tryToInsert method was finished to called.");
		System.out.println("---------------------------------------------------------------");
		return affectedRows;
	}

	@Override
	public ArrayList<FoodItemVO> selectByDiaryId(FoodItemVO foodItem) {
		System.out.println("---------------------------------------------------------------");		
		System.out.println("FoodItemServiceImpl class selectByDiaryId method was called.");

		System.out.println();
		System.out.println();
		System.out.println("foodItem:"+foodItem);
		System.out.println();
		System.out.println();
		
		System.out.println("FoodItemServiceImpl class selectByDiaryId method was finished to called.");
		System.out.println("---------------------------------------------------------------");
		
		return this.foodItemDao.selectByDiaryId(foodItem);
	}

	@Override
	public ArrayList<FoodItemVO> selectByDiaryIdAndMealCategoryId(FoodItemVO foodItem) {
		System.out.println("---------------------------------------------------------------");		
		System.out.println("FoodItemServiceImpl class selectByDiaryIdAndMealCategoryId method was called.");

		System.out.println();
		System.out.println();
		System.out.println("foodItem:"+foodItem);
		System.out.println();
		System.out.println();
		
		System.out.println("FoodItemServiceImpl class selectByDiaryIdAndMealCategoryId method was finished to called.");
		System.out.println("---------------------------------------------------------------");
		
		return this.foodItemDao.selectByDiaryIdAndMealCategoryId(foodItem);
	}

	@Override
	public int deleteByDiaryIdAndFoodId(FoodItemVO foodItem) {
		System.out.println("---------------------------------------------------------------");		
		System.out.println("FoodItemServiceImpl class deleteByDiaryIdAndFoodId method was called.");

		System.out.println();
		System.out.println();
		System.out.println("foodItem:"+foodItem);
		System.out.println();
		System.out.println();
		
		System.out.println("FoodItemServiceImpl class deleteByDiaryIdAndFoodId method was finished to called.");
		System.out.println("---------------------------------------------------------------");
		return this.foodItemDao.deleteByDiaryIdAndFoodId(foodItem);
	}
}

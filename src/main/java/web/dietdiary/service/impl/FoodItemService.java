package web.dietdiary.service.impl;

import web.dietdiary.vo.FoodItemVO;

public interface FoodItemService {
	int insert(FoodItemVO foodItem);
	int update(FoodItemVO foodItem);
	int tryToInsert(FoodItemVO foodItem);
	int updateMealCategoryId(FoodItemVO foodItem);
	
}

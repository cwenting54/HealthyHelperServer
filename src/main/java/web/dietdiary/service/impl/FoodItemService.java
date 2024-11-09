package web.dietdiary.service.impl;

import web.dietdiary.vo.FoodItemVO;

public interface FoodItemService {
	String insert(FoodItemVO foodItem);
	String update(FoodItemVO foodItem);
	int updateMealCategoryId(FoodItemVO foodItem);
}

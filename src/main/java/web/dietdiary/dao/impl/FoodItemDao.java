package web.dietdiary.dao.impl;

import web.dietdiary.vo.FoodItem;

public interface FoodItemDao {
	FoodItem select(int foodId);
	int insert(FoodItem foodItem);
	int delete(FoodItem foodItem);
	int update(FoodItem foodItem);
}

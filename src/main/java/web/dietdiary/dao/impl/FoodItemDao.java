package web.dietdiary.dao.impl;

import web.dietdiary.vo.FoodItem;

public interface FoodItemDao {
	FoodItem select(int foodId);
}

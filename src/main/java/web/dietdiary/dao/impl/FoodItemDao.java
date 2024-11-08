package web.dietdiary.dao.impl;

import web.dietdiary.vo.FoodItemVO;

public interface FoodItemDao {
	FoodItemVO select(int foodId);
	int insert(FoodItemVO foodItemVO);
	int delete(FoodItemVO foodItemVO);
	int update(FoodItemVO foodItemVO);
}

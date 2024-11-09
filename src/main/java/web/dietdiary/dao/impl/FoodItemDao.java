package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodItemVO;

public interface FoodItemDao {
	ArrayList<FoodItemVO> selectById(int foodId);
	int insert(FoodItemVO foodItem);
	int delete(FoodItemVO foodItem);
	int update(FoodItemVO foodItem);
	int updateMealCategoryId(FoodItemVO foodItem);
}

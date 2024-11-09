package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodItemVO;

public interface FoodItemDao {
	ArrayList<FoodItemVO> selectByDiaryId(FoodItemVO food);
	int insert(FoodItemVO foodItem);
	int delete(FoodItemVO foodItem);
	int update(FoodItemVO foodItem);
	int updateMealCategoryId(FoodItemVO foodItem);
	ArrayList<FoodItemVO> selectByFoodId(FoodItemVO foodItem);
	ArrayList<FoodItemVO> selectByDiaryIdAndFoodId(FoodItemVO foodItem);
	ArrayList<FoodItemVO> selectByDiaryIdAndMealCategoryId(FoodItemVO foodItem);
}

package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodItemVO;

public interface FoodItemDao {
	int insert(FoodItemVO foodItem);
	int deleteByDiaryIdAndFoodId(FoodItemVO foodItem);
	ArrayList<FoodItemVO> selectByFoodId(FoodItemVO foodItem);
	ArrayList<FoodItemVO> selectByDiaryId(FoodItemVO food);
	ArrayList<FoodItemVO> selectByDiaryIdAndFoodId(FoodItemVO foodItem);
	ArrayList<FoodItemVO> selectByDiaryIdAndMealCategoryId(FoodItemVO foodItem);
	int updateByFoodId(FoodItemVO foodItem);
	int updateByDiaryIdAndFoodId(FoodItemVO foodItem);
}

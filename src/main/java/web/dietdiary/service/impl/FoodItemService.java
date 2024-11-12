package web.dietdiary.service.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodItemVO;

public interface FoodItemService {
	int insert(FoodItemVO foodItem);
	int tryToInsert(FoodItemVO foodItem);
	ArrayList<FoodItemVO> selectByDiaryId(FoodItemVO foodItem);
	ArrayList<FoodItemVO> selectByDiaryIdAndMealCategoryId(FoodItemVO foodItem);
	int updateByFoodId(FoodItemVO foodItem);
	int updateByDiaryIdAndFoodId(FoodItemVO foodItem);
	int deleteByDiaryIdAndFoodId(FoodItemVO foodItem);
}

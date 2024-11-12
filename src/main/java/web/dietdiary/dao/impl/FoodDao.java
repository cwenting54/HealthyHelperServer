package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodVO;

public interface FoodDao {
	ArrayList<FoodVO> selectByFoodName(String name);
	FoodVO selectByFoodId(int foodId);
	ArrayList<FoodVO> listAvailableFoods();
	int insert(FoodVO food);
	int delete(FoodVO food);
	ArrayList<FoodVO> selectByFoodId(FoodVO food);
}

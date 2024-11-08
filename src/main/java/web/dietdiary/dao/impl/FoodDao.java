package web.dietdiary.dao.impl;

import java.util.ArrayList;


import web.dietdiary.vo.FoodVO;
import web.dietdiary.vo.FoodNameAndGramsVO;

public interface FoodDao {
	FoodVO selectByFoodName(String name);
	FoodVO selectByFoodId(int foodId);
	ArrayList<FoodVO> listAvailableFoods();
	int insert(FoodVO foodVO);
	int delete(FoodVO foodVO);
}

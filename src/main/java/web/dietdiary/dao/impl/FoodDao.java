package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.Food;

public interface FoodDao {
	Food selectByFoodName(String name);
	Food selectByFoodId(int foodId);
	ArrayList<Food> listAvailableFoods();
	int insert(Food food);
	int delete(Food food);
}

package web.dietdiary.dao.impl;

import web.dietdiary.vo.Food;
import web.dietdiary.vo.Nutrition;

public interface NutritionDao {
	Nutrition getNutritionFromFood(Food food);
}

package web.dietdiary.dao.impl;

import web.dietdiary.vo.FoodVO;
import web.dietdiary.vo.NutritionVO;

public interface NutritionDao {
	NutritionVO getNutritionFromFood(FoodVO foodVO);
}

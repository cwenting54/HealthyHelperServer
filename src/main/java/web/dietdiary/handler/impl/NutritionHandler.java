package web.dietdiary.handler.impl;

import web.dietdiary.vo.DietDiaryVO;
import web.dietdiary.vo.FoodVO;
import web.dietdiary.vo.NutritionVO;

public interface NutritionHandler {
	NutritionVO add(NutritionVO self, NutritionVO other);
	NutritionVO multiply(NutritionVO nutrition,double grams);
	NutritionVO getNutritionFromFood(FoodVO food);
	DietDiaryVO returnNutritionVO(NutritionVO self);
}

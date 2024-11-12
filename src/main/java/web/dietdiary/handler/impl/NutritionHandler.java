package web.dietdiary.handler.impl;

import web.dietdiary.vo.NutritionVO;

public interface NutritionHandler {
	NutritionVO multiply(NutritionVO nutrition,double grams);
}

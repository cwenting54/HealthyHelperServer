package web.dietdiary.handler.impl;

import web.dietdiary.vo.Nutrition;

public interface NutritionHandler {
	Nutrition multiply(Nutrition nutrition,double grams);
}
package web.dietdiary.service.impl;

import java.util.ArrayList;

import web.dietdiary.vo.MealTimeRangeCategoryVO;

public interface MealTimeRangeCategoryService {
	String change(MealTimeRangeCategoryVO mealTimeRangeCategoryVO);
	String insert(MealTimeRangeCategoryVO mealTimeRangeCategoryVO);
	String update(MealTimeRangeCategoryVO mealTimeRangeCategoryVO);
	ArrayList<MealTimeRangeCategoryVO> select(MealTimeRangeCategoryVO mealTimeRangeCategoryVO);
}

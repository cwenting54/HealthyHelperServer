package web.dietdiary.service.impl;

import java.util.ArrayList;

import web.dietdiary.vo.MealTimeRangeCategoryVO;

public interface MealTimeRangeCategoryService {
	String change(MealTimeRangeCategoryVO mealTimeRangeCategory);
	String insert(MealTimeRangeCategoryVO mealTimeRangeCategory);
	String update(MealTimeRangeCategoryVO mealTimeRangeCategory);
	ArrayList<MealTimeRangeCategoryVO> select(MealTimeRangeCategoryVO mealTimeRangeCategory);
}

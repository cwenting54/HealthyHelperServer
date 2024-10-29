package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.MealTimeRangeCategory;

public interface MealTimeRangeCategoryDao {
	int insert(MealTimeRangeCategory mealTimeRangeCategory);
	int update(MealTimeRangeCategory mealTimeRangeCategory);
	ArrayList<MealTimeRangeCategory> selectByUserId(int userId);
}

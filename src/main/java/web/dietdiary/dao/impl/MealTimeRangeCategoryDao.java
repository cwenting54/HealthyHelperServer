package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.MealTimeRangeCategoryVO;

public interface MealTimeRangeCategoryDao {
	int insert(MealTimeRangeCategoryVO mealTimeRangeCategoryVO);
	int update(MealTimeRangeCategoryVO mealTimeRangeCategoryVO);
	ArrayList<MealTimeRangeCategoryVO> selectByUserId(int userId);
}

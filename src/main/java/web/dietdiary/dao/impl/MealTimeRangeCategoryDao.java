package web.dietdiary.dao.impl;

import java.util.ArrayList;
import web.dietdiary.vo.MealTimeRangeCategoryVO;

public interface MealTimeRangeCategoryDao {
	int insert(MealTimeRangeCategoryVO mealTimeRangeCategory);
	int update(MealTimeRangeCategoryVO mealTimeRangeCategory);
	ArrayList<MealTimeRangeCategoryVO> selectByUserId(int userId);
}

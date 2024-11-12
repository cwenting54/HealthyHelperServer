package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodNameVO;

public interface FoodNameDao {
	ArrayList<FoodNameVO> listAvailableFoodName();
}

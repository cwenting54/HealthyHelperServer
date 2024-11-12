package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodNameAndGramsVO;

public interface FoodNameAndGramsDao {
	ArrayList<FoodNameAndGramsVO> listAvailableFoodsNameAndGrams();
}

package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodNameAndGrams;

public interface FoodNameAndGramsDao {
	ArrayList<FoodNameAndGrams> listAvailableFoodsNameAndGrams();
}

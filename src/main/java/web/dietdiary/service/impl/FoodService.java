package web.dietdiary.service.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodVO;

public interface FoodService {
	ArrayList<FoodVO> listAvailableFoods();
	ArrayList<FoodVO> selectIdByName(FoodVO food);
	ArrayList<FoodVO> selectNameById(FoodVO food);
	String insert(FoodVO food);
}

package web.dietdiary.service.impl;

import java.util.ArrayList;

import web.dietdiary.vo.FoodVO;

public interface FoodService {
	ArrayList<FoodVO> listAvailableFoods();
	String insert(FoodVO food);
}

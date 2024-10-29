package web.dietdiary.service.impl;

import java.util.ArrayList;

import javax.naming.NamingException;

import web.dietdiary.dao.impl.FoodDao;
import web.dietdiary.dao.impl.FoodDaoImpl;
import web.dietdiary.vo.Food;

public class FoodServiceImpl implements FoodService{

	private FoodDao foodDao;
	
	public FoodServiceImpl() throws NamingException {
		this.foodDao = new FoodDaoImpl(null);
	}
	
	@Override
	public ArrayList<Food> listAvailableFoods() {
		return this.foodDao.listAvailableFoods();
	}
}

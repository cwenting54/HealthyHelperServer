package web.dietdiary.service.impl;

import java.util.ArrayList;

import javax.naming.NamingException;

import web.dietdiary.dao.impl.FoodNameDao;
import web.dietdiary.dao.impl.FoodNameDaoImpl;
import web.dietdiary.vo.FoodNameVO;

public class FoodNameServiceImpl implements FoodNameService{

	private FoodNameDao foodNameDao;
	
	public FoodNameServiceImpl() throws NamingException {
		this.foodNameDao = new FoodNameDaoImpl(null);
	}
	
	@Override
	public ArrayList<FoodNameVO> listAvailableFoodsName() {
		return this.foodNameDao.listAvailableFoodName();
	}

}

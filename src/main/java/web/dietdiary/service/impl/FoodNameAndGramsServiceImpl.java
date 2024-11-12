package web.dietdiary.service.impl;

import java.util.ArrayList;

import javax.naming.NamingException;

import web.dietdiary.dao.impl.FoodNameAndGramsDao;
import web.dietdiary.dao.impl.FoodNameAndGramsDaoImpl;
import web.dietdiary.vo.FoodNameAndGramsVO;

public class FoodNameAndGramsServiceImpl implements FoodNameAndGramsService{

	private FoodNameAndGramsDao foodNameAndGramsDao ;

	public FoodNameAndGramsServiceImpl() throws NamingException {
		this.foodNameAndGramsDao = new FoodNameAndGramsDaoImpl(null);
	}
	
	@Override
	public ArrayList<FoodNameAndGramsVO> listAvailableFoodsNameAndGrams() {
		return this.foodNameAndGramsDao.listAvailableFoodsNameAndGrams();
	}
}

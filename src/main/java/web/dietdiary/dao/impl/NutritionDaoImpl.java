package web.dietdiary.dao.impl;

import web.dietdiary.vo.FoodVO;
import web.dietdiary.vo.NutritionVO;

public class NutritionDaoImpl implements NutritionDao {

	@Override
	public NutritionVO getNutritionFromFood(FoodVO foodVO) {
		NutritionVO nutritionVO = new NutritionVO();
		
		nutritionVO.setFat(foodVO.getFat());
		nutritionVO.setProtein(foodVO.getProtein());
		nutritionVO.setFiber(foodVO.getFiber());
		nutritionVO.setCarbon(foodVO.getCarbon());
		nutritionVO.setSodium(foodVO.getSodium());
		nutritionVO.setSugar(foodVO.getSugar());
		nutritionVO.setCalories(foodVO.getCalories());
		
		return nutritionVO;
	}

}

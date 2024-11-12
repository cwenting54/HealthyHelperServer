package web.dietdiary.dao.impl;

import web.dietdiary.vo.FoodVO;
import web.dietdiary.vo.NutritionVO;

public class NutritionDaoImpl implements NutritionDao {

	@Override
	public NutritionVO getNutritionFromFood(FoodVO food) {
		NutritionVO nutrition = new NutritionVO();
		
		nutrition.setFat(food.getFat());
		nutrition.setProtein(food.getProtein());
		nutrition.setFiber(food.getFiber());
		nutrition.setCarbon(food.getCarbon());
		nutrition.setSodium(food.getSodium());
		nutrition.setSugar(food.getSugar());
		nutrition.setCalories(food.getCalories());
		
		return nutrition;
	}

}

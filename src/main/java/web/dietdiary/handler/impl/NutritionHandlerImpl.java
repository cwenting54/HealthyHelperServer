package web.dietdiary.handler.impl;

import web.dietdiary.vo.NutritionVO;

public class NutritionHandlerImpl implements NutritionHandler {

	@Override
	public NutritionVO multiply(NutritionVO nutrition, double grams) {
		NutritionVO newNutrition = new NutritionVO();
		newNutrition.setFat(nutrition.getFat() * grams);
		newNutrition.setFiber(nutrition.getFiber() * grams);
		newNutrition.setCarbon(nutrition.getCarbon() * grams);
		newNutrition.setProtein(nutrition.getProtein() * grams);
		newNutrition.setSugar(nutrition.getSugar() * grams);
		newNutrition.setSodium(nutrition.getSodium() * grams);
		newNutrition.setCalories(nutrition.getCalories() * grams);		
		return newNutrition;
	}
}

package web.dietdiary.handler.impl;

import web.dietdiary.vo.NutritionVO;

public class NutritionHandlerImpl implements NutritionHandler {

	@Override
	public NutritionVO multiply(NutritionVO nutritionVO, double grams) {
		NutritionVO newNutrition = new NutritionVO();
		newNutrition.setFat(nutritionVO.getFat() * grams);
		newNutrition.setFiber(nutritionVO.getFiber() * grams);
		newNutrition.setCarbon(nutritionVO.getCarbon() * grams);
		newNutrition.setProtein(nutritionVO.getProtein() * grams);
		newNutrition.setSugar(nutritionVO.getSugar() * grams);
		newNutrition.setSodium(nutritionVO.getSodium() * grams);
		newNutrition.setCalories(nutritionVO.getCalories() * grams);		
		return newNutrition;
	}
}

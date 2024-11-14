package web.dietdiary.handler.impl;

import web.dietdiary.vo.DietDiaryVO;
import web.dietdiary.vo.FoodVO;
import web.dietdiary.vo.NutritionVO;

public class NutritionHandlerImpl implements NutritionHandler {

	@Override
	public NutritionVO add(NutritionVO self, NutritionVO other) {
		NutritionVO newNutrition = new NutritionVO();
		newNutrition.setFat(self.getFat() + other.getFat());
		newNutrition.setFiber(self.getFiber() + other.getFiber());
		newNutrition.setProtein(self.getProtein() + other.getProtein());
		newNutrition.setCarbon(self.getCarbon() + other.getCarbon());
		newNutrition.setSodium(self.getSodium() + other.getSodium());
		newNutrition.setSugar(self.getSugar() + other.getSugar());
		newNutrition.setCalories(self.getCalories() + other.getCalories());
		return newNutrition;
	}
	
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

	@Override
	public DietDiaryVO returnNutritionVO(NutritionVO self) {
		DietDiaryVO dietDiary = new DietDiaryVO();
		
		dietDiary.setTotalCalories(self.getCalories());
		dietDiary.setTotalCarbon(self.getCarbon());
		dietDiary.setTotalFat(self.getFat());
		dietDiary.setTotalFiber(self.getFiber());
		dietDiary.setTotalProtein(self.getProtein());
		dietDiary.setTotalSodium(self.getSodium());
		dietDiary.setTotalSugar(self.getSugar());
		
		return dietDiary;
	}
}

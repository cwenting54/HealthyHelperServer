package web.dietdiary.checker.impl;

import web.dietdiary.vo.DietDiary;

public class DietDiaryCheckerImpl implements DietDiaryChecker{

	@Override
	public boolean check(DietDiary dietDiary) {
		if(dietDiary.getTotalFat() <= 0.0) {
			return false;
		}
		if(dietDiary.getTotalCarbon() <= 0.0) {
			return false;
		}
		if(dietDiary.getTotalFiber() <= 0.0) {
			return false;
		}
		if(dietDiary.getTotalProtein() <= 0.0) {
			return false;
		}
		if(dietDiary.getTotalSodium() <= 0.0) {
			return false;
		}
		if(dietDiary.getTotalSugar() <= 0.0) {
			return false;
		}
		if(dietDiary.getTotalCalories() <= 0.0) {
			return false;
		}
		return true;
	}
}

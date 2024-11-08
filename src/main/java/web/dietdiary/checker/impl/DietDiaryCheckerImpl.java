package web.dietdiary.checker.impl;

import web.dietdiary.vo.DietDiaryVO;

public class DietDiaryCheckerImpl implements DietDiaryChecker{

	@Override
	public boolean check(DietDiaryVO dietDiaryVO) {
		if(dietDiaryVO.getTotalFat() <= 0.0) {
			return false;
		}
		if(dietDiaryVO.getTotalCarbon() <= 0.0) {
			return false;
		}
		if(dietDiaryVO.getTotalFiber() <= 0.0) {
			return false;
		}
		if(dietDiaryVO.getTotalProtein() <= 0.0) {
			return false;
		}
		if(dietDiaryVO.getTotalSodium() <= 0.0) {
			return false;
		}
		if(dietDiaryVO.getTotalSugar() <= 0.0) {
			return false;
		}
		if(dietDiaryVO.getTotalCalories() <= 0.0) {
			return false;
		}
		return true;
	}
}

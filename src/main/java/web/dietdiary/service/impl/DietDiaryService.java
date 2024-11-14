package web.dietdiary.service.impl;

import java.util.ArrayList;

import web.dietdiary.vo.DietDiaryVO;
import web.dietdiary.vo.NutritionVO;


public interface DietDiaryService {
	public ArrayList<DietDiaryVO> search(DietDiaryVO dietDiary, int mode);
	public ArrayList<DietDiaryVO> searchByDateAndUserId(DietDiaryVO dietDiary);
	public ArrayList<DietDiaryVO> searchByTimeAndUserId(DietDiaryVO dietDiary);
	public ArrayList<DietDiaryVO> searchByDateAndTimeAndUserId(DietDiaryVO dietDiary);
		
	public DietDiaryVO plusNutrition(DietDiaryVO dietDiary, NutritionVO nutrition);
	public int insert(DietDiaryVO dietDiary);
	
	public ArrayList<DietDiaryVO> sort(ArrayList<DietDiaryVO> dietDiaries, int mode,boolean isAscending);
	public ArrayList<DietDiaryVO> sortByDate(ArrayList<DietDiaryVO> dietDiaries,boolean isAscending);
	
	public ArrayList<DietDiaryVO> selectByUserIdAndDate(DietDiaryVO dietDiary);
	int updateDietDiary(DietDiaryVO dietDiary);
}

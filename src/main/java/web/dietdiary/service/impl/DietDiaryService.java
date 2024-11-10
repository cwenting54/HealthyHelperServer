package web.dietdiary.service.impl;

import java.sql.Date;
import java.util.ArrayList;

import web.dietdiary.vo.DietDiaryVO;
import web.dietdiary.vo.NutritionVO;


public interface DietDiaryService {
	public ArrayList<DietDiaryVO> search(DietDiaryVO dietDiary, int mode);
	public ArrayList<DietDiaryVO> searchByDate(DietDiaryVO dietDiary);
	public ArrayList<DietDiaryVO> searchByTime(DietDiaryVO dietDiary);
	public ArrayList<DietDiaryVO> searchByDateAndTime(DietDiaryVO dietDiary);
		
	public DietDiaryVO plusNutrition(DietDiaryVO dietDiary, NutritionVO nutrition);
	public String updateDietDiary(int foodId, Date date);
	public String insert(DietDiaryVO dietDiary);
	
	public ArrayList<DietDiaryVO> sort(ArrayList<DietDiaryVO> dietDiaries, int mode,boolean isAscending);
	public ArrayList<DietDiaryVO> sortByDate(ArrayList<DietDiaryVO> dietDiaries,boolean isAscending);
	
	public ArrayList<DietDiaryVO> selectByUserIdAndDate(DietDiaryVO dietDiary);
}

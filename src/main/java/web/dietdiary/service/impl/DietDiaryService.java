package web.dietdiary.service.impl;

import java.sql.Date;
import java.util.ArrayList;

import web.dietdiary.vo.DietDiaryVO;
import web.dietdiary.vo.NutritionVO;

public interface DietDiaryService {
	public ArrayList<DietDiaryVO> search(DietDiaryVO dietDiaryVO, int mode);
	public ArrayList<DietDiaryVO> searchByDate(DietDiaryVO dietDiaryVO);
	public ArrayList<DietDiaryVO> searchByTime(DietDiaryVO dietDiaryVO);
	public ArrayList<DietDiaryVO> searchByDateAndTime(DietDiaryVO dietDiaryVO);
		
	public DietDiaryVO plusNutrition(DietDiaryVO dietDiaryVO, NutritionVO nutritionVO);
	public String updateDietDiary(int foodId, Date date);
	public String insert(DietDiaryVO dietDiaryVO);
	
	public ArrayList<DietDiaryVO> sort(ArrayList<DietDiaryVO> dietDiaryVOs, int mode,boolean isAscending);
	public ArrayList<DietDiaryVO> sortByDate(ArrayList<DietDiaryVO> dietDiaryVOs,boolean isAscending);

}

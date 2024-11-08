package web.dietdiary.dao.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import web.dietdiary.vo.DietDiaryVO;
import web.dietdiary.vo.NutritionVO;

public interface DietDiaryDao {
	String insert(DietDiaryVO dietDiaryVO);
	
	ArrayList<DietDiaryVO> selectByTime(int userId,Time time);
	ArrayList<DietDiaryVO> selectByDate(int userId,Date date);
	ArrayList<DietDiaryVO> selectByDateAndTime(int userId,Date date,Time time);
		
	int updateByDiaryId(DietDiaryVO dietDiaryVO);
	
	DietDiaryVO selectByDiaryIdAndDate(int diaryId, Date date);
}

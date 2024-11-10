package web.dietdiary.dao.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import web.dietdiary.vo.DietDiaryVO;

public interface DietDiaryDao {
	String insert(DietDiaryVO dietDiary);
	
	ArrayList<DietDiaryVO> selectByTime(int userId,Time time);
	ArrayList<DietDiaryVO> selectByDate(int userId,Date date);
	ArrayList<DietDiaryVO> selectByDateAndTime(int userId,Date date,Time time);
		
	int updateByDiaryId(DietDiaryVO dietDiary);
	
	DietDiaryVO selectByDiaryIdAndDate(int diaryId, Date date);
	ArrayList<DietDiaryVO> selectByUserIdAndDate(DietDiaryVO dietDiary);
}

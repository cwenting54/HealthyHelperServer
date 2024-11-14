package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.DietDiaryVO;

public interface DietDiaryDao {
	int insert(DietDiaryVO dietDiary);
			
	int updateByDiaryId(DietDiaryVO dietDiary);
	
	ArrayList<DietDiaryVO> selectByUserIdAndDate(DietDiaryVO dietDiary);

	ArrayList<DietDiaryVO> selectByDateAndTimeAndUserId(DietDiaryVO dietDiary);

	ArrayList<DietDiaryVO> selectByTimeAndUserId(DietDiaryVO dietDiary);

	ArrayList<DietDiaryVO> selectByDateAndUserId(DietDiaryVO dietDiary);

	ArrayList<DietDiaryVO> selectByDiaryIdAndDate(DietDiaryVO dietDiary);
	
	ArrayList<DietDiaryVO> selectByFoodId(DietDiaryVO dietDiary);
}

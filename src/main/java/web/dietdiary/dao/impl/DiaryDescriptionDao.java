package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.DiaryDescriptionVO;

public interface DiaryDescriptionDao {
	int insert(DiaryDescriptionVO diaryDescriptionVO);
	ArrayList<DiaryDescriptionVO> selectByDiaryIdAndMealCategoryId(DiaryDescriptionVO diaryDescriptionVO);
	int updateByDiaryIdAndMealCategoryId(DiaryDescriptionVO diaryDescriptionVO);
}

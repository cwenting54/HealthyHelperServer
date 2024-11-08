package web.dietdiary.dao.impl;

import java.util.ArrayList;

import web.dietdiary.vo.DiaryDescriptionVO;

public interface DiaryDescriptionDao {
	int insert(DiaryDescriptionVO diaryDescriptionVO);
	int updateById(DiaryDescriptionVO diaryDescriptionVO);
	ArrayList<DiaryDescriptionVO> selectById(DiaryDescriptionVO diaryDescriptionVO);
}

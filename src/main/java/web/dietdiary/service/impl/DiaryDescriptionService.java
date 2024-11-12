package web.dietdiary.service.impl;

import java.util.ArrayList;

import web.dietdiary.vo.DiaryDescriptionVO;

public interface DiaryDescriptionService {
	int insert(DiaryDescriptionVO diaryDescriptionVO);
	int updateById(DiaryDescriptionVO diaryDescriptionVO);
	ArrayList<DiaryDescriptionVO> selectById(DiaryDescriptionVO diaryDescriptionVO);
	int tryToInsert(DiaryDescriptionVO diaryDescriptionVO);
}

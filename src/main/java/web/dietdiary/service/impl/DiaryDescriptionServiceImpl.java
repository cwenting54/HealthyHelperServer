package web.dietdiary.service.impl;

import java.util.ArrayList;

import javax.naming.NamingException;

import web.dietdiary.dao.impl.DiaryDescriptionDao;
import web.dietdiary.dao.impl.DiaryDescriptionDaoImpl;
import web.dietdiary.vo.DiaryDescriptionVO;

public class DiaryDescriptionServiceImpl implements DiaryDescriptionService{

	private DiaryDescriptionDao diaryDescriptionDao;
	
	public DiaryDescriptionServiceImpl() throws NamingException {
		this.diaryDescriptionDao = new DiaryDescriptionDaoImpl(null);
	}
	
	@Override
	public int insert(DiaryDescriptionVO diaryDescriptionVO) {
		return this.diaryDescriptionDao.insert(diaryDescriptionVO);
	}

	@Override
	public int updateById(DiaryDescriptionVO diaryDescriptionVO) {
		return this.diaryDescriptionDao.updateById(diaryDescriptionVO);
	}

	@Override
	public ArrayList<DiaryDescriptionVO> selectById(DiaryDescriptionVO diaryDescriptionVO) {
		return this.diaryDescriptionDao.selectById(diaryDescriptionVO);
	}

	@Override
	public int tryToInsert(DiaryDescriptionVO diaryDescriptionVO) {
		ArrayList<DiaryDescriptionVO> queriedDiaryDescriptionVO = new ArrayList<DiaryDescriptionVO>();
		Boolean hasRecord = true;
		int affectedRows = 1;
		queriedDiaryDescriptionVO = this.diaryDescriptionDao.selectById(diaryDescriptionVO);
		hasRecord = queriedDiaryDescriptionVO != null && queriedDiaryDescriptionVO.size() > 0;
		if(hasRecord) {
			affectedRows = this.diaryDescriptionDao.updateById(diaryDescriptionVO);
		}else {
			affectedRows = this.diaryDescriptionDao.insert(diaryDescriptionVO);
		}
		return affectedRows;
	}

}

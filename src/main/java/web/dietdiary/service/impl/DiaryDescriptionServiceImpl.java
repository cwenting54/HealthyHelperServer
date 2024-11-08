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
		return this.updateById(diaryDescriptionVO);
	}

	@Override
	public ArrayList<DiaryDescriptionVO> selectById(DiaryDescriptionVO diaryDescriptionVO) {
		return this.selectById(diaryDescriptionVO);
	}

}

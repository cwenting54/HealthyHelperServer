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
		return this.diaryDescriptionDao.updateByDiaryIdAndMealCategoryId(diaryDescriptionVO);
	}

	@Override
	public ArrayList<DiaryDescriptionVO> selectById(DiaryDescriptionVO diaryDescriptionVO) {
		return this.diaryDescriptionDao.selectByDiaryIdAndMealCategoryId(diaryDescriptionVO);
	}

	@Override
	public int tryToInsert(DiaryDescriptionVO diaryDescriptionVO) {
		System.out.println("-------------------------------------------");
		System.out.println("DiaryDescriptionServiceImpl class, tryToInsert method was called.");
		
		ArrayList<DiaryDescriptionVO> queriedDiaryDescriptionVO = new ArrayList<DiaryDescriptionVO>();
		Boolean hasRecord = true;
		int affectedRows = 1;
		queriedDiaryDescriptionVO = this.diaryDescriptionDao.selectByDiaryIdAndMealCategoryId(diaryDescriptionVO);
		System.out.println();
		System.out.println();
		System.out.println("queriedDiaryDescriptionVO:"+queriedDiaryDescriptionVO);
		System.out.println();
		System.out.println();
		if(queriedDiaryDescriptionVO == null) {
			System.out.println("DiaryDescriptionServiceImpl class, tryToInsert method was finished to called.");
			System.out.println("-------------------------------------------");
			return -1;
		}
		hasRecord = queriedDiaryDescriptionVO.size() > 0;
		System.out.println();
		System.out.println();
		System.out.println("hasRecord:"+hasRecord);
		System.out.println();
		System.out.println();
		if(hasRecord) {
			affectedRows = this.diaryDescriptionDao.updateByDiaryIdAndMealCategoryId(diaryDescriptionVO);
		}else {
			affectedRows = this.diaryDescriptionDao.insert(diaryDescriptionVO);
		}
		System.out.println();
		System.out.println();
		System.out.println("affectedRows:"+affectedRows);
		System.out.println();
		System.out.println();
		
		System.out.println("DiaryDescriptionServiceImpl class, tryToInsert method was finished to called.");
		System.out.println("-------------------------------------------");
		return affectedRows;
	}
}

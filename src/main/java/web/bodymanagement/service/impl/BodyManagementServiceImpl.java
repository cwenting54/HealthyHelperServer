package web.bodymanagement.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;

import web.bodymanagement.dao.BodyManagementDao;
import web.bodymanagement.dao.impl.BodyManagementDaoImpl;
import web.bodymanagement.service.BodyManagementService;
import web.bodymanagement.vo.BodyManagement;

public class BodyManagementServiceImpl implements BodyManagementService {
	private BodyManagementDao bodyManagementDao;

	public BodyManagementServiceImpl() throws NamingException {
		bodyManagementDao = new BodyManagementDaoImpl();
	}

	@Override
	public String insertBodyData(BodyManagement bodyManagement) {

		if (bodyManagement.getHeight() == null || bodyManagement.getWeight() == null || bodyManagement.getHeight() <= 0
				|| bodyManagement.getWeight() <= 0) {
			return "體重和身高不可以為0";
		}
		int result = bodyManagementDao.insertBodyData(bodyManagement);

		return result > 0 ? null : "體重紀錄新增失敗";
	}

	@Override
	public String updateBodyData(BodyManagement bodyManagement) {
		if (bodyManagement.getHeight() == null || bodyManagement.getWeight() == null || bodyManagement.getHeight() <= 0
				|| bodyManagement.getWeight() <= 0) {
			return "體重和身高不可以為0";
		}
		int result = bodyManagementDao.updateBodyDate(bodyManagement);

		return result > 0 ? null : "體重紀錄修改失敗";
	}

	@Override
	public String deleteBodyDate(int recordId) {
		if (recordId <=0) {
			return "無法刪除";
		}

		int result = bodyManagementDao.deleteBodyDataByRecordId(recordId);
		return result > 0 ? null : "刪除失敗";
	}

	@Override
	public List<BodyManagement> selectBodyData(int userId, Timestamp startDate, Timestamp endDate) {		
		return bodyManagementDao.selectByUserIdAndDateRange(userId, startDate, endDate);
	}

}

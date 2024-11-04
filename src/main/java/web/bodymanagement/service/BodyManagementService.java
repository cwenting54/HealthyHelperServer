package web.bodymanagement.service;

import java.sql.Timestamp;
import java.util.List;

import web.bodymanagement.vo.BodyManagement;

public interface BodyManagementService {
	String insertBodyData(BodyManagement bodyManagement);

	String updateBodyData(BodyManagement bodyManagement);

	String deleteBodyDate(int recordId);

	List<BodyManagement> selectBodyData(int userId, Timestamp startDate, Timestamp endDate);
}

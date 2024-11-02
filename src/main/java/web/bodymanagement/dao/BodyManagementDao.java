package web.bodymanagement.dao;

import java.sql.Timestamp;
import java.util.List;

import web.bodymanagement.vo.BodyManagement;

public interface BodyManagementDao {
	int insertBodyData(BodyManagement bodyManagement);

	int updateBodyDate(BodyManagement bodyManagement);

	int deleteBodyDataByRecordId(int recordId);

	List<BodyManagement> selectByUserIdAndDateRange(int userId, Timestamp startDate, Timestamp endDate);

	boolean isRecordDateExists(int integer, Timestamp recordDate);
}

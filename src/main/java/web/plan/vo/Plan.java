package web.plan.vo;

import java.sql.Timestamp;

public class Plan {
	private Integer userDietPlanId;
	private Integer categoryId;
	private Integer userId;
	private Timestamp startDateTime;
	private Timestamp endDateTime;
	private Integer finishstate;
	
	public Plan() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Plan(Integer struserDietPlanId, Integer categoryId, Integer userId, Timestamp startDateTime,
			Timestamp endDateTime, Integer finishstate) {
		super();
		this.userDietPlanId = struserDietPlanId;
		this.categoryId = categoryId;
		this.userId = userId;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.finishstate = finishstate;
	}




	public Integer getStruserDietPlanId() {
		return userDietPlanId;
	}
	public void setStruserDietPlanId(Integer struserDietPlanId) {
		this.userDietPlanId = struserDietPlanId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Timestamp getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
	}
	public Integer getFinishstate() {
		return finishstate;
	}
	public void setFinishstate(Integer finishstate) {
		this.finishstate = finishstate;
	}
	
	
}

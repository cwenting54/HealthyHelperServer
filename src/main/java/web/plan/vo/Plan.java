package web.plan.vo;

import java.sql.Date;

public class Plan {
	private Integer userId;
	private Date startDate;
	private Date endDate;
	
	public Plan() {
		super();
	}

	public Plan(Integer userId, Date startDate, Date endDate) {
		super();
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}

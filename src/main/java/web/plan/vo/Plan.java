package web.plan.vo;

import java.sql.Timestamp;

public class Plan {
	private Integer userDietPlanId;
	private Integer categoryId;
	private Integer userId;
	private Timestamp startDateTime;
	private Timestamp endDateTime;
	private Integer finishstate;
	private Float fatgoal;
	private Float carbongoal;
	private Float proteingoal;
	private Float Caloriesgoal;
	
	public Plan() {
		
	}

	public Plan(Integer userDietPlanId, Integer categoryId, Integer userId, Timestamp startDateTime,
			Timestamp endDateTime, Integer finishstate, Float fatgoal, Float carbongoal, Float proteingoal,
			Float caloriesgoal) {
		super();
		this.userDietPlanId = userDietPlanId;
		this.categoryId = categoryId;
		this.userId = userId;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.finishstate = finishstate;
		this.fatgoal = fatgoal;
		this.carbongoal = carbongoal;
		this.proteingoal = proteingoal;
		Caloriesgoal = caloriesgoal;
	}

	public Integer getUserDietPlanId() {
		return userDietPlanId;
	}

	public void setUserDietPlanId(Integer userDietPlanId) {
		this.userDietPlanId = userDietPlanId;
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

	public Float getFatgoal() {
		return fatgoal;
	}

	public void setFatgoal(Float fatgoal) {
		this.fatgoal = fatgoal;
	}

	public Float getCarbongoal() {
		return carbongoal;
	}

	public void setCarbongoal(Float carbongoal) {
		this.carbongoal = carbongoal;
	}

	public Float getProteingoal() {
		return proteingoal;
	}

	public void setProteingoal(Float proteingoal) {
		this.proteingoal = proteingoal;
	}

	public Float getCaloriesgoal() {
		return Caloriesgoal;
	}

	public void setCaloriesgoal(Float caloriesgoal) {
		Caloriesgoal = caloriesgoal;
	}
	
		
}

package web.plan.vo;

import java.sql.Time;
import java.util.Date;

public class DiaryAll {
	private int diaryId;
	private int userId;
	private Date createDate;
	private Time createTime;
	private Float totalFat;
	private Float totalCarbon;
	private Float totalProtein;
	private Float totalFiber;
	private Float totalSugar;
	private Float totalSodium;
	private Float totalCalories;
	
	public DiaryAll() {
		super();
	}
	
	public DiaryAll(int diaryId, int userId, Date createDate, Time createTime, Float totalFat, Float totalCarbon,
			Float totalProtein, Float totalFiber, Float totalSugar, Float totalSodium, Float totalCalories) {
		super();
		this.diaryId = diaryId;
		this.userId = userId;
		this.createDate = createDate;
		this.createTime = createTime;
		this.totalFat = totalFat;
		this.totalCarbon = totalCarbon;
		this.totalProtein = totalProtein;
		this.totalFiber = totalFiber;
		this.totalSugar = totalSugar;
		this.totalSodium = totalSodium;
		this.totalCalories = totalCalories;
	}

	public int getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Time getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}

	public Float getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(Float totalFat) {
		this.totalFat = totalFat;
	}

	public Float getTotalCarbon() {
		return totalCarbon;
	}

	public void setTotalCarbon(Float totalCarbon) {
		this.totalCarbon = totalCarbon;
	}

	public Float getTotalProtein() {
		return totalProtein;
	}

	public void setTotalProtein(Float totalProtein) {
		this.totalProtein = totalProtein;
	}

	public Float getTotalFiber() {
		return totalFiber;
	}

	public void setTotalFiber(Float totalFiber) {
		this.totalFiber = totalFiber;
	}

	public Float getTotalSugar() {
		return totalSugar;
	}

	public void setTotalSugar(Float totalSugar) {
		this.totalSugar = totalSugar;
	}

	public Float getTotalSodium() {
		return totalSodium;
	}

	public void setTotalSodium(Float totalSodium) {
		this.totalSodium = totalSodium;
	}

	public Float getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(Float totalCalories) {
		this.totalCalories = totalCalories;
	}
	
	

}

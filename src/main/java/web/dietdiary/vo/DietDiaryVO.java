package web.dietdiary.vo;

import java.sql.Date;
import java.sql.Time;

public class DietDiaryVO {
	private int diaryID;
	private int userID;
	private Date createDate;
	private Time createTime;
	private Double totalFat;
	private Double totalCarbon;
	private Double totalProtein;
	private Double totalFiber;
	private Double totalSugar;
	private Double totalSodium;
	private Double totalCalories;
	
	public int getDiaryID() {
		return diaryID;
	}
	public void setDiaryID(int diaryID) {
		this.diaryID = diaryID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
	public Double getTotalFat() {
		return totalFat;
	}
	public void setTotalFat(Double totalFat) {
		this.totalFat = totalFat;
	}
	public Double getTotalCarbon() {
		return totalCarbon;
	}
	public void setTotalCarbon(Double totalCarbon) {
		this.totalCarbon = totalCarbon;
	}
	public Double getTotalProtein() {
		return totalProtein;
	}
	public void setTotalProtein(Double totalProtein) {
		this.totalProtein = totalProtein;
	}
	public Double getTotalFiber() {
		return totalFiber;
	}
	public void setTotalFiber(Double totalFiber) {
		this.totalFiber = totalFiber;
	}
	public Double getTotalSugar() {
		return totalSugar;
	}
	public void setTotalSugar(Double totalSugar) {
		this.totalSugar = totalSugar;
	}
	public Double getTotalSodium() {
		return totalSodium;
	}
	public void setTotalSodium(Double totalSodium) {
		this.totalSodium = totalSodium;
	}
	public Double getTotalCalories() {
		return totalCalories;
	}
	public void setTotalCalories(Double totalCalories) {
		this.totalCalories = totalCalories;
	}
	@Override
	public String toString() {
		return "DietDiaryVO [diaryID=" + diaryID + ", userID=" + userID + ", createDate=" + createDate + ", createTime="
				+ createTime + ", totalFat=" + totalFat + ", totalCarbon=" + totalCarbon + ", totalProtein="
				+ totalProtein + ", totalFiber=" + totalFiber + ", totalSugar=" + totalSugar + ", totalSodium="
				+ totalSodium + ", totalCalories=" + totalCalories + "]";
	}

}

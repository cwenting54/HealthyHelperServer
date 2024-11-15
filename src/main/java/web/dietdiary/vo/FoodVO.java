package web.dietdiary.vo;

public class FoodVO {
	private int  foodID;
	private String foodName;
	private double fat;
	private double carbon;
	private double protein;
	private double fiber;
	private double sugar;
	private double sodium;
	private double calories;
	
	public int getFoodID() {
		return foodID;
	}
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getCarbon() {
		return carbon;
	}
	public void setCarbon(double carbon) {
		this.carbon = carbon;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getFiber() {
		return fiber;
	}
	public void setFiber(double fiber) {
		this.fiber = fiber;
	}
	public double getSugar() {
		return sugar;
	}
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}
	public double getSodium() {
		return sodium;
	}
	public void setSodium(double sodium) {
		this.sodium = sodium;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	@Override
	public String toString() {
		return "FoodVO [foodID=" + foodID + ", foodName=" + foodName + ", fat=" + fat + ", carbon=" + carbon
				+ ", protein=" + protein + ", fiber=" + fiber + ", sugar=" + sugar + ", sodium=" + sodium
				+ ", calories=" + calories + "]";
	}
}

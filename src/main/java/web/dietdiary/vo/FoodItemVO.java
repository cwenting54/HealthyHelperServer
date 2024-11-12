package web.dietdiary.vo;

public class FoodItemVO {
	private int diaryID;
	private int foodID;
	private int mealCategoryID;
	private Double grams;
	
	public int getDiaryID() {
		return diaryID;
	}
	public void setDiaryID(int diaryID) {
		this.diaryID = diaryID;
	}
	public int getFoodID() {
		return foodID;
	}
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	public int getMealCategoryID() {
		return mealCategoryID;
	}
	public void setMealCategoryID(int mealCategoryID) {
		this.mealCategoryID = mealCategoryID;
	}
	public Double getGrams() {
		return grams;
	}
	public void setGrams(Double grams) {
		this.grams = grams;
	}
	@Override
	public String toString() {
		return "FoodItemVO [diaryID=" + diaryID + ", foodID=" + foodID + ", mealCategoryID=" + mealCategoryID
				+ ", grams=" + grams + "]";
	}
}

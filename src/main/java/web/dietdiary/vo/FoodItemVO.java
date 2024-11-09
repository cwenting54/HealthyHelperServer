package web.dietdiary.vo;

public class FoodItemVO {
	private int diaryId;
	private int foodId;
	private int mealCategoryId;
	public int getMealCategoryId() {
		return mealCategoryId;
	}
	public void setMealCategoryId(int mealCategoryId) {
		this.mealCategoryId = mealCategoryId;
	}
	private Double grams;
	
	public int getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public Double getGrams() {
		return grams;
	}
	public void setGrams(Double grams) {
		this.grams = grams;
	}
	@Override
	public String toString() {
		return "FoodItemVO [diaryId=" + diaryId + ", foodId=" + foodId + ", mealCategoryId=" + mealCategoryId
				+ ", grams=" + grams + "]";
	}
}

package web.dietdiary.vo;

public class FoodNameAndGramsVO {
	private String foodName;
	private Double grams;
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Double getGrams() {
		return grams;
	}
	public void setGrams(Double grams) {
		this.grams = grams;
	}
	
	@Override
	public String toString() {
		return "FoodNameAndGrams [foodName=" + foodName + ", grams=" + grams + "]";
	}
}

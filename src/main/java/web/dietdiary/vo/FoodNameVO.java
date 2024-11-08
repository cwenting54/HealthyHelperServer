package web.dietdiary.vo;

public class FoodNameVO {
	private String foodName;

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	@Override
	public String toString() {
		return "FoodName [foodName=" + foodName + "]";
	}
	
}

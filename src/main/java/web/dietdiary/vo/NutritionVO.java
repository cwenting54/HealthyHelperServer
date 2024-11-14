package web.dietdiary.vo;

public class NutritionVO {
	private Double fat;
	private Double carbon;
	private Double protein;
	private Double fiber;
	private Double sugar;
	private Double sodium;
	private Double calories;
	
	public NutritionVO(Double fat, Double carbon, Double protein, Double fiber, Double sugar, Double sodium,
			Double calories) {
		this.fat = fat;
		this.carbon = carbon;
		this.protein = protein;
		this.fiber = fiber;
		this.sugar = sugar;
		this.sodium = sodium;
		this.calories = calories;
	}
	
	public NutritionVO() {
		this(0.0,0.0,0.0,0.0,0.0,0.0,0.0);
	}
	
	public Double getFat() {
		return fat;
	}
	public void setFat(Double fat) {
		this.fat = fat;
	}
	public Double getCarbon() {
		return carbon;
	}
	public void setCarbon(Double carbon) {
		this.carbon = carbon;
	}
	public Double getProtein() {
		return protein;
	}
	public void setProtein(Double protein) {
		this.protein = protein;
	}
	public Double getFiber() {
		return fiber;
	}
	public void setFiber(Double fiber) {
		this.fiber = fiber;
	}
	public Double getSugar() {
		return sugar;
	}
	public void setSugar(Double sugar) {
		this.sugar = sugar;
	}
	public Double getSodium() {
		return sodium;
	}
	public void setSodium(Double sodium) {
		this.sodium = sodium;
	}
	public Double getCalories() {
		return calories;
	}
	public void setCalories(Double calories) {
		this.calories = calories;
	}
}

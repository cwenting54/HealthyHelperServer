package web.plan.vo;

public class Categories {
	private Integer categoryID;
	private String categoryName;
	private Float fatgoal;
	private Float carbongoal;
	private Float proteingoal;
	private Float Caloriesgoal;
	
	
	
	public Categories() {
		// TODO Auto-generated constructor stub
	}



	public Categories(Integer categoryID, String categoryName, Float fatgoal, Float carbongoal, Float proteingoal,
			Float caloriesgoal) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.fatgoal = fatgoal;
		this.carbongoal = carbongoal;
		this.proteingoal = proteingoal;
		Caloriesgoal = caloriesgoal;
	}



	public Integer getCategoryID() {
		return categoryID;
	}



	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

package web.plan.vo;

public class Categories {
	private Integer categoryID;
	private String categoryName;
	
	
	
	public Categories() {
		// TODO Auto-generated constructor stub
	}



	public Categories(Integer categoryID, String categoryName, Float fatgoal, Float carbongoal, Float proteingoal,
			Float caloriesgoal) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
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

	
}

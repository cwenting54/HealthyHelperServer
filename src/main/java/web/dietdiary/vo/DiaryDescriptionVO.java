package web.dietdiary.vo;

public class DiaryDescriptionVO {
	private int diaryID;
	private String foodIconUri;
	private String foodDescription;
	
	
	public int getDiaryID() {
		return diaryID;
	}
	public void setDiaryID(int diaryID) {
		this.diaryID = diaryID;
	}
	
	public String getFoodIconUri() {
		return foodIconUri;
	}
	public void setFoodIconUri(String foodIconUri) {
		this.foodIconUri = foodIconUri;
	}
	public String getFoodDescription() {
		return foodDescription;
	}
	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}
	@Override
	public String toString() {
		return "DiaryDescriptionVO [diaryID=" + diaryID + ", foodIconUri=" + foodIconUri + ", foodDescription="
				+ foodDescription + "]";
	}
}

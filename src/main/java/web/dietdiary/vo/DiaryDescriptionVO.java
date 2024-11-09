package web.dietdiary.vo;

public class DiaryDescriptionVO {
	private int diaryId;
	private String foodIconUri;
	private String foodDescription;
	public int getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
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
		return "DiaryDescriptionVO [diaryId=" + diaryId + ", foodIconUri=" + foodIconUri + ", foodDescription="
				+ foodDescription + "]";
	}
}

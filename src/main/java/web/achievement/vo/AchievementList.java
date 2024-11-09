package web.achievement.vo;

import java.sql.Timestamp;

public class AchievementList {
	 private Integer userId;
	    private Integer aId;
	    private Timestamp finishtime;

	    public AchievementList() {
	    }

	    public AchievementList(Integer userId, Integer aId, Timestamp finishtime) {
	        this.userId = userId;
	        this.aId = aId;
	        this.finishtime = finishtime;
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public Integer getAId() {
	        return aId;
	    }

	    public void setAId(Integer aId) {
	        this.aId = aId;
	    }

	    public Timestamp getFinishtime() {
	        return finishtime;
	    }

	    public void setFinishtime(Timestamp finishtime) {
	        this.finishtime = finishtime;
	    }
}

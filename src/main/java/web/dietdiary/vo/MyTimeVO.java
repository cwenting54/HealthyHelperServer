package web.dietdiary.vo;

import java.sql.Time;

public class MyTimeVO {
	private Time myTime;

	public Time getMyTime() {
		return myTime;
	}

	public void setMyTime(Time myTime) {
		this.myTime = myTime;
	}

	@Override
	public String toString() {
		return "MyTimeVO [myTime=" + myTime + "]";
	}
	
}

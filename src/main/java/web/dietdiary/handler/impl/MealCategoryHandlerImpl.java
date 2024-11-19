package web.dietdiary.handler.impl;

import java.util.ArrayList;
import java.sql.Time;

// use my defined Pair instead of third-party package `javafx.util.Pair` 
// there is no built-in package to do operations for pair of data. 
import web.dietdiary.ownerclass.Pair;

public class MealCategoryHandlerImpl {
	private ArrayList<Pair<Time,Time>> mealCategories = new ArrayList<Pair<Time, Time>>();
	
	public ArrayList<Pair<Time,Time>> getMealCategories() {
		return this.mealCategories;
	}
	
	public MealCategoryHandlerImpl(){
		this.setMealCategories();
	}
	
	@SuppressWarnings("deprecation")
	private void setMealCategories() {
		Time zeroTime = new Time(0,0,0);
		Time midnightTime = new Time(23,59,59);
		Time breakfastStartTime = new Time(3,0,0);
		Time lunchStartTime = new Time(12,0,0);
		Time dinnerStartTime = new Time(18,0,0);
		Time supperStartTime = new Time(22,0,0);
		
		this.mealCategories.clear();
	
		this.mealCategories.add(new Pair(zeroTime,breakfastStartTime));
		this.mealCategories.add(new Pair(breakfastStartTime,lunchStartTime));
		this.mealCategories.add(new Pair(lunchStartTime,dinnerStartTime));
		this.mealCategories.add(new Pair(dinnerStartTime,supperStartTime));
		this.mealCategories.add(new Pair(supperStartTime,midnightTime));

		return;
	}
	
	public int getMealCategoryId(Time time) {
		ArrayList<Pair<Time, Time>> mealCategories = this.getMealCategories();
		int size = mealCategories.size();
		int id = -1;
		for(int index = 0 ; index <= size - 1 ; index++ ) {
			Pair<Time, Time> pair = mealCategories.get(index);
			Time startTime = pair.getKey();
			Time endTime = pair.getValue();
			if(time.after(startTime) && time.before(endTime)) {
				id = index;
				break;
			}
		}
		
		if(id == -1) {
			return -1;
		}
		
		if(id == 0) {
			return size - 1;
		}
		
		return id;
	}
}

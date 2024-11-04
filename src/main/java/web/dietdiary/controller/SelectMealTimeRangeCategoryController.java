package web.dietdiary.controller;

import java.io.IOException;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import web.dietdiary.datetime.DateTimeFormatterImpl;
import web.dietdiary.service.impl.MealTimeRangeCategoryService;
import web.dietdiary.service.impl.MealTimeRangeCategoryServiceImpl;
import web.dietdiary.util.gson.GsonForSqlDateAndSqlTime;
import web.dietdiary.vo.MealTimeRangeCategory;

@WebServlet("/dietDiary/mealTimeRangeCategory/select")
public class SelectMealTimeRangeCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MealTimeRangeCategoryService mealTimeRangeCategoryService;

	@Override
	public void init() throws ServletException {
		try {
			this.mealTimeRangeCategoryService = new MealTimeRangeCategoryServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
		Gson gson = GsonForSqlDateAndSqlTime.gson;
		JsonArray jsonArray = new JsonArray();
		DateTimeFormatterImpl dateTimeFormatterImpl = new DateTimeFormatterImpl();
		String result = "";
		String errorMessage = "";
		int affectedRows = 1;
		ArrayList<MealTimeRangeCategory> mealTimeRangeCategories = new ArrayList<MealTimeRangeCategory>();
		MealTimeRangeCategory targetMealTimeRangeCategory = gson.fromJson(req.getReader(), MealTimeRangeCategory.class);
		mealTimeRangeCategories = this.mealTimeRangeCategoryService.select(targetMealTimeRangeCategory);
		if(mealTimeRangeCategories == null) {
			JsonObject jsonObject = new JsonObject();
			errorMessage = "Unknown error!!!";
			affectedRows = -1;
			jsonObject.addProperty("result", false);
			jsonObject.addProperty("errorMessage", errorMessage);
			jsonObject.addProperty("affectedRows", affectedRows);
			resp.getWriter().write(jsonObject.toString());
			return;
		}
		for(MealTimeRangeCategory mealTimeRangeCategory:mealTimeRangeCategories){
			JsonObject jsonObject = new JsonObject();
			
			jsonObject.addProperty("userId", mealTimeRangeCategory.getUserId());
			jsonObject.addProperty("breakfastStartTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategory.getBreakfastStartTime()));
			jsonObject.addProperty("breakfastEndTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategory.getBreakfastEndTime()));
			jsonObject.addProperty("lunchStartTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategory.getLunchStartTime()));
			jsonObject.addProperty("lunchEndTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategory.getLunchEndTime()));
			jsonObject.addProperty("dinnerStartTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategory.getDinnerStartTime()));
			jsonObject.addProperty("dinnerEndTime",dateTimeFormatterImpl.TimeToString(mealTimeRangeCategory.getBreakfastStartTime());
			jsonObject.addProperty("supperStartTime", mealTimeRangeCategory.getUserId());
			jsonObject.addProperty("supperEndTime", mealTimeRangeCategory.getUserId());
		}
		resp.getWriter().write(jsonArray.toString());
		return;
	}
}

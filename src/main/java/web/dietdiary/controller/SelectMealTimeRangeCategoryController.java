package web.dietdiary.controller;

import java.io.IOException;
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
import web.dietdiary.vo.MealTimeRangeCategoryVO;

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
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
		Gson gson = GsonForSqlDateAndSqlTime.gson;
		JsonArray jsonArray = new JsonArray();
		DateTimeFormatterImpl dateTimeFormatterImpl = new DateTimeFormatterImpl();
		String errorMessage = "";
		ArrayList<MealTimeRangeCategoryVO> mealTimeRangeCategoryVOs = new ArrayList<MealTimeRangeCategoryVO>();
		MealTimeRangeCategoryVO targetMealTimeRangeCategory = gson.fromJson(req.getReader(), MealTimeRangeCategoryVO.class);
		mealTimeRangeCategoryVOs = this.mealTimeRangeCategoryService.select(targetMealTimeRangeCategory);
		if(mealTimeRangeCategoryVOs == null) {
			JsonObject jsonObject = new JsonObject();
			errorMessage = "Unknown error!!!";
			jsonObject.addProperty("result", false);
			jsonObject.addProperty("errorMessage", errorMessage);
			resp.getWriter().write(jsonObject.toString());
			return;
		}

		for(MealTimeRangeCategoryVO mealTimeRangeCategoryVO:mealTimeRangeCategoryVOs){
			JsonObject jsonObject = new JsonObject();
			
			jsonObject.addProperty("userId", mealTimeRangeCategoryVO.getUserId());
			jsonObject.addProperty("breakfastStartTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategoryVO.getBreakfastStartTime()));
			jsonObject.addProperty("breakfastEndTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategoryVO.getBreakfastEndTime()));
			jsonObject.addProperty("lunchStartTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategoryVO.getLunchStartTime()));
			jsonObject.addProperty("lunchEndTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategoryVO.getLunchEndTime()));
			jsonObject.addProperty("dinnerStartTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategoryVO.getDinnerStartTime()));
			jsonObject.addProperty("dinnerEndTime",dateTimeFormatterImpl.TimeToString(mealTimeRangeCategoryVO.getDinnerEndTime()));
			jsonObject.addProperty("supperStartTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategoryVO.getSupperStartTime()));
			jsonObject.addProperty("supperEndTime", dateTimeFormatterImpl.TimeToString(mealTimeRangeCategoryVO.getSupperEndTime()));
			
			jsonArray.add(jsonObject);
		}
		resp.getWriter().write(jsonArray.toString());
		return;
	}
}

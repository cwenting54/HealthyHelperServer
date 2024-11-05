package web.dietdiary.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.dietdiary.service.impl.MealTimeRangeCategoryService;
import web.dietdiary.service.impl.MealTimeRangeCategoryServiceImpl;
import web.dietdiary.util.gson.GsonForSqlDateAndSqlTime;
import web.dietdiary.vo.MealTimeRangeCategory;

@WebServlet("/dietDiary/mealTimeRangeCategory/insert")
public class InsertMealTimeRangeCategoryController extends HttpServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		Gson gson = GsonForSqlDateAndSqlTime.gson;
		JsonObject jsonObject = new JsonObject();
		String result = "";
		String errorMessage = "";
		MealTimeRangeCategory mealTimeRangeCategory;
		mealTimeRangeCategory = gson.fromJson(req.getReader(), MealTimeRangeCategory.class);
		errorMessage = this.mealTimeRangeCategoryService.insert(mealTimeRangeCategory);

		if (errorMessage != "") {
			jsonObject.addProperty("result", false);
			jsonObject.addProperty("errorMessage", errorMessage);
			resp.getWriter().write(jsonObject.toString());
			return;
		}

		jsonObject.addProperty("result", true);
		jsonObject.addProperty("errorMessage", errorMessage);
		resp.getWriter().write(jsonObject.toString());
		return;
	}

}
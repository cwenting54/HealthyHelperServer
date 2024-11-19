package web.dietdiary.handler.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.dietdiary.util.gson.GsonForSqlDateAndSqlTime;
import web.dietdiary.vo.MyTimeVO;

@WebServlet("/dietDiary/MealCategoryHandlerImplController")
public class MealCategoryHandlerImplController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private MealCategoryHandlerImpl mealCategoryHandlerImpl;
	
	@Override
	public void init() throws ServletException {
		try {
			this.mealCategoryHandlerImpl = new MealCategoryHandlerImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");

		System.out.println("-----------------------------------------");
		System.out.println(
				"`doPost` method in class with annotation `@WebServlet(\"/dietDiary/MealCategoryHandlerImplController\")` was called.");

		Gson gson = GsonForSqlDateAndSqlTime.gson;
		MyTimeVO targetTime = gson.fromJson(req.getReader(), MyTimeVO.class);
		int affectedRows = -1;

		System.out.println();
		System.out.println();
		System.out.println("targetTime:" + targetTime);
		System.out.println();
		System.out.println();

		affectedRows = this.mealCategoryHandlerImpl.getMealCategoryId(targetTime.getMyTime());

		System.out.println();
		System.out.println();
		System.out.println("affectedRows:" + affectedRows);
		System.out.println();
		System.out.println();

		resp.getWriter().write(gson.toJson(affectedRows));

		System.out.println(
				"`doPost` method in class with annotation `@WebServlet(\"/dietDiary/MealCategoryHandlerImplController\")` was finished to called.");
		System.out.println("-----------------------------------------");

		return;
	}
}

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

import web.dietdiary.service.impl.FoodItemService;
import web.dietdiary.service.impl.FoodItemServiceImpl;
import web.dietdiary.vo.FoodItemVO;

@WebServlet("/dietDiary/foodItem/insert")
public class InsertFoodItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FoodItemService foodItemService;

	@Override
	public void init() throws ServletException {
		try {
			this.foodItemService = new FoodItemServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override 
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		int affectedRows = -1;
		String errorMessage = "";
		FoodItemVO foodItem = gson.fromJson(req.getReader(), FoodItemVO.class);
		if(foodItem == null) {
			errorMessage = "NPE!!! foodItem is null";
			jsonObject.addProperty("result", false);
			jsonObject.addProperty("errorMessage", errorMessage);
			resp.getWriter().write(jsonObject.toString());
			return;
		}
		affectedRows = this.foodItemService.insert(foodItem);
		if(affectedRows!=1) {
			errorMessage = "Unknown Error!!!";
			jsonObject.addProperty("result", false);
			jsonObject.addProperty("errorMessage", errorMessage);
			resp.getWriter().write(jsonObject.toString());
			return;
		}
		errorMessage = "";
		jsonObject.addProperty("result", true);
		jsonObject.addProperty("errorMessage", errorMessage);
		resp.getWriter().write(jsonObject.toString());
		return;
	}
}

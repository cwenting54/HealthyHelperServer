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
import com.google.gson.JsonObject;

import web.dietdiary.service.impl.FoodItemService;
import web.dietdiary.service.impl.FoodItemServiceImpl;
import web.dietdiary.service.impl.FoodService;
import web.dietdiary.service.impl.FoodServiceImpl;
import web.dietdiary.vo.FoodItemVO;
import web.dietdiary.vo.FoodVO;

@WebServlet("/dietDiary/fooditem/tryToInsert/byFoodName")
public class TryToInsertFoodItemByFoodNameController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private FoodItemService foodItemService;
	private FoodService foodService;
	
	@Override
	public void init() throws ServletException {
		try {
			this.foodItemService = new FoodItemServiceImpl();
			this.foodService = new FoodServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        String errorMessage = "";
        int affectedRows = -1;
        FoodVO targetFood = gson.fromJson(req.getReader(), FoodVO.class);
        System.out.println("targetFood:"+targetFood);
        int foodId = this.foodService.selectIdByName(targetFood);
        FoodItemVO foodItemOnlyWithId = new FoodItemVO();
        foodItemOnlyWithId.setFoodId(foodId);
        ArrayList<FoodItemVO> targetFoodItems = this.foodItemService.selectById(foodItemOnlyWithId);
        FoodItemVO targetFoodItem = new FoodItemVO();
        if(!targetFoodItems.isEmpty()) {
        	targetFoodItem = targetFoodItems.get(0);
        }
        affectedRows = this.foodItemService.tryToInsert(targetFoodItem);
        if(affectedRows != 1) {
        	errorMessage = "Unknown Error!!!";
        	jsonObject.addProperty("errorMessage", errorMessage);
        	jsonObject.addProperty("result", false);
        	resp.getWriter().write(jsonObject.toString());
        	return;
        }
        errorMessage = "";
    	jsonObject.addProperty("errorMessage", errorMessage);
    	jsonObject.addProperty("result", true);
    	resp.getWriter().write(jsonObject.toString());
    	return;
	}
}

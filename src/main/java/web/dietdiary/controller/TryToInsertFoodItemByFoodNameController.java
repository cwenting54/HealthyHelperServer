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
        
        System.out.println("---------------------------------------------------------------");
        System.out.println("`doPost` method class with annotation `@WebServlet(\"/dietDiary/fooditem/tryToInsert/byFoodName\")` was called.");
		
        Gson gson = new Gson();
        int affectedRows = -1;
        FoodVO targetFood = gson.fromJson(req.getReader(), FoodVO.class);
        System.out.println();
        System.out.println();
        System.out.println("targetFood:"+targetFood);
        System.out.println();
        System.out.println();
        ArrayList<FoodVO> queriedFoods = this.foodService.selectIdByName(targetFood);
        System.out.println();
        System.out.println();
        System.out.println("queriedFoods:"+queriedFoods);
        System.out.println();
        System.out.println();
        FoodItemVO foodItemOnlyWithId = new FoodItemVO();
        foodItemOnlyWithId.setFoodID(queriedFoods.get(0).getFoodID());
        ArrayList<FoodItemVO> queriedFoodItems = this.foodItemService.selectByDiaryId(foodItemOnlyWithId);
        System.out.println();
        System.out.println();
        System.out.println("queriedFoodItems:"+queriedFoodItems);
        System.out.println();
        System.out.println();
        FoodItemVO queriedFoodItem = new FoodItemVO();
        if(!queriedFoodItems.isEmpty()) {
        	queriedFoodItem = queriedFoodItems.get(0);
        }
        affectedRows = this.foodItemService.tryToInsert(queriedFoodItem);
        
        System.out.println();
        System.out.println();
        System.out.println("affectedRows:"+affectedRows);
        System.out.println();
        System.out.println();
        
        System.out.println("`doPost` method class with annotation `@WebServlet(\"/dietDiary/fooditem/tryToInsert/byFoodName\")` was finished to called.");
        System.out.println("---------------------------------------------------------------");
		
    	resp.getWriter().write(gson.toJson(affectedRows));
    	return;
	}
}

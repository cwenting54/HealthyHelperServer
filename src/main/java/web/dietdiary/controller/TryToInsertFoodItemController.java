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

@WebServlet("/dietDiary/foodItem/insert/tryToInsertFoodItem")
public class TryToInsertFoodItemController extends HttpServlet{
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
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        System.out.println("------------------------------------------------");
        System.out.println("`doPost` method in class with annotation `@WebServlet(\"/dietDiary/foodItem/tryToInsert\")` was called.");
        Gson gson = new Gson();
        int affectedRows = -1;
        FoodItemVO targetFoodItem = gson.fromJson(req.getReader(), FoodItemVO.class);
        System.out.println();
        System.out.println();
        System.out.println("targetFoodItem:"+targetFoodItem);
        System.out.println();
        System.out.println();
        affectedRows = this.foodItemService.tryToInsert(targetFoodItem);
        System.out.println();
        System.out.println();
        System.out.println("affectedRows:"+affectedRows);
        System.out.println();
        System.out.println();
    	resp.getWriter().write(gson.toJson(affectedRows));
        System.out.println("`doPost` method in class with annotation `@WebServlet(\"/dietDiary/foodItem/tryToInsert\")` was finished to called.");
    	System.out.println("------------------------------------------------");
    	return;
	}
}

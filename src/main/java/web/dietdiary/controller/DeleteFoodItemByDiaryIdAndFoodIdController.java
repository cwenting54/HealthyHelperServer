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
import web.dietdiary.service.impl.FoodItemService;
import web.dietdiary.service.impl.FoodItemServiceImpl;
import web.dietdiary.util.gson.GsonForSqlDateAndSqlTime;
import web.dietdiary.vo.FoodItemVO;

@WebServlet("/dietDiary/foodItem/delete/ByDiaryIdAndFoodId")
public class DeleteFoodItemByDiaryIdAndFoodIdController extends HttpServlet{
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
        
        System.out.println("----------------------------------------------");
		System.out.println("`doPost` method of class with annotation @WebServlet(\"/dietDiary/foodItem/delete/ByDiaryIdAndFoodId\"): was called.");

		Gson gson = GsonForSqlDateAndSqlTime.gson;
		FoodItemVO targetFoodItem = gson.fromJson(req.getReader(), FoodItemVO.class);
		
		System.out.println();
		System.out.println();
		System.out.println("targetFoodItem:"+targetFoodItem);
		System.out.println();
		System.out.println();
		
		int affectedRows = this.foodItemService.deleteByDiaryIdAndFoodId(targetFoodItem);

		System.out.println();
		System.out.println();
		System.out.println("affectedRows:"+affectedRows);
		System.out.println();
		System.out.println();
		
		resp.getWriter().write(gson.toJson(affectedRows));
		
		System.out.println("`doPost` method of class with annotation @WebServlet(\"/dietDiary/foodItem/delete/ByDiaryIdAndFoodId\"): was called.");
		System.out.println("----------------------------------------------");
		return;
	}
}

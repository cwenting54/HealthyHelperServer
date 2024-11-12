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

import web.dietdiary.service.impl.FoodService;
import web.dietdiary.service.impl.FoodServiceImpl;
import web.dietdiary.vo.FoodVO;

@WebServlet("/dietDiary/food/select/selectFoodIdByFoodName")
public class SelectFoodIdByFoodNameController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private FoodService foodService;
	
	@Override
	public void init() throws ServletException {
		try {
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
        FoodVO targetFood = gson.fromJson(req.getReader(), FoodVO.class);
        ArrayList<FoodVO> queriedFoods = this.foodService.selectIdByName(targetFood);
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("In @WebServlet(\"/dietDiary/food/select/selectFoodIdByFoodName\"), gson.toJson(queriedFoods):"+gson.toJson(queriedFoods));
    	System.out.println();
    	System.out.println();
    	System.out.println();
        resp.getWriter().write(gson.toJson(queriedFoods));
    	return;
	}
}
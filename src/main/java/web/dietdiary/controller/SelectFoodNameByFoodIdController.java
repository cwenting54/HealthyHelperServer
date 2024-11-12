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

import web.dietdiary.service.impl.FoodService;
import web.dietdiary.service.impl.FoodServiceImpl;
import web.dietdiary.vo.FoodVO;

@WebServlet("/dietDiary/foodName/selectFoodNameByFoodId")
public class SelectFoodNameByFoodIdController extends HttpServlet{
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
        ArrayList<FoodVO> foods = this.foodService.selectNameById(targetFood);
    	resp.getWriter().write(gson.toJson(foods));
    	return;
	}
}
package web.dietdiary.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import web.dietdiary.service.impl.FoodNameAndGramsService;
import web.dietdiary.service.impl.FoodNameAndGramsServiceImpl;
import web.dietdiary.vo.FoodNameAndGrams;

@WebServlet("/dietDiary/foodName/listAvailableFoodsNameAndGrams")
public class ListAvailableFoodsNameAndGramsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private FoodNameAndGramsService foodNameAndGramsService;

	@Override
	public void init() throws ServletException {
		try {
			this.foodNameAndGramsService = new FoodNameAndGramsServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");

			Gson gson = new Gson();
			ArrayList<FoodNameAndGrams> foodNameAndGramses = new ArrayList<FoodNameAndGrams>();
			foodNameAndGramses = this.foodNameAndGramsService.listAvailableFoodsNameAndGrams();
			
			System.out.println("Ready to deserialize data.");
			System.out.println("foodNameAndGramses:"+foodNameAndGramses);
			
			if(foodNameAndGramses==null) {
				throw new Exception("Unknown exception!!!");
			}
			
			
			resp.getWriter().write(gson.toJson(foodNameAndGramses));
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}

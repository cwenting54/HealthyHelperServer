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

import web.dietdiary.service.impl.FoodNameAndGramsService;
import web.dietdiary.service.impl.FoodNameAndGramsServiceImpl;
import web.dietdiary.vo.FoodNameAndGramsVO;

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
			ArrayList<FoodNameAndGramsVO> foodNameAndGramses = new ArrayList<FoodNameAndGramsVO>();
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

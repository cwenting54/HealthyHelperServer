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

import web.dietdiary.service.impl.FoodNameService;
import web.dietdiary.service.impl.FoodNameServiceImpl;
import web.dietdiary.vo.FoodNameVO;

@WebServlet("/dietDiary/food/listAvailableFoodsName")
public class ListAvailableFoodsNameController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FoodNameService foodNameService;

	@Override
	public void init() throws ServletException {
		try {
			this.foodNameService = new FoodNameServiceImpl();
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
			ArrayList<FoodNameVO> foodNames = new ArrayList<FoodNameVO>();
			
			foodNames = this.foodNameService.listAvailableFoodsName();
			
			System.out.println("foodNames:"+foodNames.toString());
			if (foodNames == null) {
				throw new Exception("Unknown error!!!");
			}

			resp.getWriter().write(gson.toJson(foodNames));
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}

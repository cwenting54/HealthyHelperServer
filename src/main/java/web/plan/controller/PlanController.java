package web.plan.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.plan.service.PlanService;
import web.plan.service.impl.PlanServiceImpl;
import web.plan.vo.PlanWithCategory;



@WebServlet("/Plan")
public class PlanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private PlanService planService;
	
	@Override
	public void init() throws ServletException {
		try {
			planService = new PlanServiceImpl();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用
		System.out.println("output: " + outText);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		PlanWithCategory planWithCategory = gson.fromJson(req.getReader(), PlanWithCategory.class);
		System.out.println("dataIn: " + planWithCategory.toString()); //requestIn
		
		List<PlanWithCategory> planlist = planService.getUnFinishedPlan(planWithCategory);
		writeText(resp, gson.toJson(planlist));
	}

}

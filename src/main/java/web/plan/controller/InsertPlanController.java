package web.plan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.plan.Common;
import web.plan.service.PlanManageService;
import web.plan.service.PlanService;
import web.plan.service.impl.PlanManageServiceImpl;
import web.plan.service.impl.PlanServiceImpl;
import web.plan.usecase.GsonTimeBuilder;
import web.plan.vo.PlanWithCategory;


@WebServlet("/Plan/AddPlan")
public class InsertPlanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlanService planService;
	private PlanManageService planManageService;
	
	@Override
	public void init() throws ServletException {
		try {
			planService = new PlanServiceImpl();
			planManageService = new PlanManageServiceImpl();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(Common.CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.write(outText);
		// 將輸出資料列印出來除錯用
		System.out.println("output: " + outText);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = GsonTimeBuilder.gson;
		try {
			PlanWithCategory planWithCategory = gson.fromJson(req.getReader(), PlanWithCategory.class);
			System.out.println("dataIn: " + planWithCategory.toString()); //requestIn
			
			String errMsg = planService.insertPlan(planWithCategory);			
			JsonObject resbody = new JsonObject();
			resbody.addProperty("result", errMsg == null);
			resbody.addProperty("errMsg", errMsg);
			writeText(resp, resbody.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("postErr: " + e.toString());
		}
		
	}

}

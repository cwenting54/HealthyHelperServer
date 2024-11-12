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

import web.plan.Common;
import web.plan.service.PlanManageService;
import web.plan.service.impl.PlanManageServiceImpl;
import web.plan.vo.PlanWithCategory;


@WebServlet("/Plan/Manage")
public class ManagePlanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private PlanManageService planManageService;
	
	@Override
	public void init() throws ServletException {
		try {
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
		Gson gson = new Gson();
		System.out.println("dataIn: " + req.toString()); //requestIn
		try {
			PlanWithCategory planWithCategory = gson.fromJson(req.getReader(), PlanWithCategory.class);
			List<PlanWithCategory> planlist = planManageService.getPlanList(planWithCategory);
			if (planlist == null) {
				writeText(resp, "");
			}
			writeText(resp, gson.toJson(planlist));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("postErr: " + e.toString());
		}
		
	}
    
}

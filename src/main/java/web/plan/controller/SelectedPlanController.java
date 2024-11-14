package web.plan.controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.plan.Common;
import web.plan.service.CheckPlanService;
import web.plan.service.PlanManageService;
import web.plan.service.impl.CheckPlanServiceImpl;
import web.plan.service.impl.PlanManageServiceImpl;
import web.plan.vo.PlanWithCategory;


@WebServlet("/Plan/SelectPlan")
public class SelectedPlanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private CheckPlanService checkPlanService;
	
	@Override
	public void init() throws ServletException {
		try {
			checkPlanService = new CheckPlanServiceImpl();
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
			System.out.println(planWithCategory.getUserId());
			System.out.println(planWithCategory.getUserDietPlanId());
			PlanWithCategory selectplan = checkPlanService.GetSelectedPlan(planWithCategory.getUserId(), planWithCategory.getUserDietPlanId());
			if(selectplan == null) {
				System.out.println("doPost: selectPlanfailed");
				writeText(resp, "doPost: selectPlanfailed");
			}	
//			String jsonStr = gson.toJson(selectplan);
			JsonObject respbody = new JsonObject();
			respbody.addProperty("userDietPlanId", selectplan.getUserDietPlanId());
			respbody.addProperty("categoryId", selectplan.getCategoryID());
			respbody.addProperty("userId", selectplan.getUserId());
			respbody.addProperty("startDatetime", selectplan.getStartDateTime().toString());
			respbody.addProperty("endDatetime", selectplan.getEndDateTime().toString());
			respbody.addProperty("finishstate", selectplan.getFinishstate());
			respbody.addProperty("fatgoal", selectplan.getFatgoal());
			respbody.addProperty("carbongoal", selectplan.getCarbongoal());
			respbody.addProperty("proteingoal", selectplan.getProteingoal());
			respbody.addProperty("Caloriesgoal", selectplan.getCaloriesgoal());
			System.out.println("doPost: " + respbody.toString());
			writeText(resp, respbody.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("postErr: " + e.toString());
		}
	}

}

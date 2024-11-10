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
import web.plan.service.CheckPlanService;
import web.plan.service.impl.CheckPlanServiceImpl;
import web.plan.usecase.GsonTimeBuilder;
import web.plan.vo.DiaryAll;
import web.plan.vo.Plan;



@WebServlet("/Plan/DiaryList")
public class GetDiaryController extends HttpServlet {
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
		
		Gson gson = GsonTimeBuilder.gsontodate;
		System.out.println("dataIn: " + req.toString()); //requestIn
		
		try {
			Plan plan = gson.fromJson(req.getReader(), Plan.class);
			// 解析傳入的 startDate 和 endDate 字串為 java.sql.Date
	        // 假設傳入的是 "2024-11-06" 格式的字串
//	        java.sql.Date startDate = java.sql.Date.valueOf(plan.getStartDate().toString()); 
//	        java.sql.Date endDate = java.sql.Date.valueOf(plan.getEndDate().toString());
	        
			List<DiaryAll> getdiary = checkPlanService.GetDiaryWithDate(plan.getUserId(), plan.getStartDate(), plan.getEndDate());
			if(getdiary == null) {
				System.out.println("doPost: selectDiaryfailed");
				writeText(resp, "doPost: selectDiaryfailed");
			}
			String jsonStr = gson.toJson(getdiary);
			System.out.println("doPost: " + jsonStr);
			writeText(resp, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("postErr: " + e.toString());
		}
	}

}

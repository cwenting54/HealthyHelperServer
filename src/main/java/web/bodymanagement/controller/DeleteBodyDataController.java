package web.bodymanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.bodymanagement.service.BodyManagementService;
import web.bodymanagement.service.impl.BodyManagementServiceImpl;

@WebServlet("/deleteBodyData")
public class DeleteBodyDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private BodyManagementService bodyManagementService;

	@Override
	public void init() throws ServletException {
		try {
			bodyManagementService = new BodyManagementServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);

		int recordId = reqBody.get("recordId").getAsInt();
		System.out.println("recordId: " + recordId);

		String errMsg  = bodyManagementService.deleteBodyDate(recordId);
		
		JsonObject respBody = new JsonObject();		
		respBody.addProperty("result", errMsg == null);
		respBody.addProperty("errMsg", errMsg);		
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());
	}

}

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
import web.bodymanagement.vo.BodyManagement;

@WebServlet("/updateBodyData")
public class UpdateBodyDataController extends HttpServlet {
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
		BodyManagement bodyManagement = new BodyManagement();
		bodyManagement.setRecordId(reqBody.get("recordId").getAsInt());
	    bodyManagement.setWeight(reqBody.get("weight").getAsFloat());
	    bodyManagement.setHeight(reqBody.get("height").getAsFloat());
	    bodyManagement.setBodyFat(reqBody.get("bodyFat").getAsFloat());
	    bodyManagement.setBmi(reqBody.get("bmi").getAsFloat());		
		
		System.out.println("dataIn: " + bodyManagement.toString());

		String errMsg = bodyManagementService.updateBodyData(bodyManagement);

		JsonObject respBody = new JsonObject();
		respBody.addProperty("result", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.setContentType(CONTENT_TYPE);
		resp.getWriter().write(respBody.toString());
		System.out.println("dataOut: " + respBody.toString());
	}

}

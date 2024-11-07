package web.bodymanagement.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import web.bodymanagement.service.BodyManagementService;
import web.bodymanagement.service.impl.BodyManagementServiceImpl;
import web.bodymanagement.vo.BodyManagement;

@WebServlet("/selectBodyData")
public class SelectBodyDataController extends HttpServlet {
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

	    Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
	    JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
	    

	    String startDateStr = reqBody.get("startDate").getAsString();
	    String endDateStr = reqBody.get("endDate").getAsString();

	    Timestamp startDate = null;
	    Timestamp endDate = null;

	    try {	     
	        Date parsedStartDate = (Date) new SimpleDateFormat("yyyy/MM/dd").parse(startDateStr);
	        Date parsedEndDate = (Date) new SimpleDateFormat("yyyy/MM/dd").parse(endDateStr);

	        startDate = new Timestamp(parsedStartDate.getTime());
	        endDate = new Timestamp(parsedEndDate.getTime());

	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    int userId = reqBody.get("userId").getAsInt();
	    System.out.println("startDate: " + startDate + ", endDate: " + endDate);


	    List<BodyManagement> bodyDataList = bodyManagementService.selectBodyData(userId, startDate, endDate);
	    JsonArray dataArray = new JsonArray();

	    for (BodyManagement data : bodyDataList) {
	        JsonObject dataJson = new JsonObject();
	        dataJson.addProperty("recordId", data.getRecordId());
	        dataJson.addProperty("weight", data.getWeight());
	        dataJson.addProperty("height", data.getHeight());
	        dataJson.addProperty("bodyFat", data.getBodyFat());
	        dataJson.addProperty("bmi", data.getBmi());
	        dataJson.addProperty("recordDate", new SimpleDateFormat("yyyy/MM/dd").format(data.getRecordDate()));
	        dataArray.add(dataJson);
	    }


	    JsonObject respBody = new JsonObject();
	    respBody.add("data", dataArray);
	    respBody.addProperty("result", !dataArray.isEmpty());
	    respBody.addProperty("errMsg", dataArray.isEmpty() ? "無資料" : null);

	    resp.setContentType(CONTENT_TYPE);
	    resp.getWriter().write(respBody.toString());
	    System.out.println("dataOut: " + respBody.toString());
	}

}

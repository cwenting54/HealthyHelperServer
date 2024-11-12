package web.alarmManager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import web.alarmManager.dao.AlarmManagerDao;
import web.alarmManager.dao.impl.AlarmManagerDaoImpl;
import web.alarmManager.vo.AlarmManager;

@WebServlet("/selectAlarm")
public class SelectAlarmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	private AlarmManagerDao alarmManagerDao;

	@Override
	public void init() throws ServletException {
		try {
			alarmManagerDao = new AlarmManagerDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");

	    Gson gson = new Gson();
	    JsonObject reqBody = gson.fromJson(req.getReader(), JsonObject.class);
	    

	    int userId = reqBody.get("userId").getAsInt();


	    List<AlarmManager> alarmManagerList = alarmManagerDao.selectAlarmByUserId(userId);
	    JsonArray dataArray = new JsonArray();

	    for (AlarmManager data : alarmManagerList) {
	        JsonObject dataJson = new JsonObject();
	        dataJson.addProperty("alarmId", data.getAlarmId());
	        dataJson.addProperty("userId", data.getUserId());
	        dataJson.addProperty("alarmTime", data.getAlarmTime().toString());

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

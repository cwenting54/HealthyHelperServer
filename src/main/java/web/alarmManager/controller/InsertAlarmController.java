package web.alarmManager.controller;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import web.alarmManager.dao.AlarmManagerDao;
import web.alarmManager.dao.impl.AlarmManagerDaoImpl;
import web.alarmManager.util.TimeDeserializer;
import web.alarmManager.util.TimeSerializer;
import web.alarmManager.vo.AlarmManager;

@WebServlet("/insertAlarm")
public class InsertAlarmController extends HttpServlet {
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

	    Gson gson = new GsonBuilder()
	            .registerTypeAdapter(Time.class, new TimeSerializer())
	            .registerTypeAdapter(Time.class, new TimeDeserializer())
	            .create();
	    
	    AlarmManager alarmManager = gson.fromJson(req.getReader(), AlarmManager.class);

	    int result = alarmManagerDao.insertAlarmManager(alarmManager);
	    
	    JsonObject respBody = new JsonObject();
	    
	    if (result < 0) {
	        respBody.addProperty("result", false);
	        respBody.addProperty("errMsg", "新增失敗");
	    } else {
	        respBody.addProperty("result", true);
	        respBody.addProperty("errMsg", result);
	    }

	    resp.setContentType(CONTENT_TYPE);
	    resp.getWriter().write(respBody.toString());
	    System.out.println("dataOut: " + respBody.toString());
	}

}

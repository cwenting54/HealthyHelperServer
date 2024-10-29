package web.dietdiary.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.dietdiary.util.gson.GsonForSqlDateAndSqlTime;
import web.dietdiary.vo.DietDiary;


@WebServlet("/dietDiary/test")
public class TestController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {	
		Gson gson = GsonForSqlDateAndSqlTime.gson;
		DietDiary dietDiary = gson.fromJson(req.getReader(), DietDiary.class);
		
		System.out.println("Ready to deserialize.");
		System.out.println("dietDiary:"+dietDiary);
	}
}

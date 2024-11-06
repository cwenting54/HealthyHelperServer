//package web.user.controller;
//
//import java.io.IOException;
//
//import javax.naming.NamingException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
//import web.user.service.UserService;
//import web.user.service.impl.UserServiceImpl;
//import web.user.vo.User;
//
//
//@WebServlet("/user/getUser")
//public class GetUserController extends HttpServlet {
//    private UserService service;
//
//    @Override
//    public void init() throws ServletException {
//        try {
//            service = new UserServiceImpl();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
//            throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json;charset=UTF-8");
//        
//        JsonObject requestData = new Gson().fromJson(req.getReader(), JsonObject.class);
//        int userId = requestData.get("userId").getAsInt();
//        
//        User user = service.getUserById(userId);
//        if (user != null) {
//            resp.getWriter().write(new Gson().toJson(user));
//        } else {
//            JsonObject error = new JsonObject();
//            error.addProperty("success", false);
//            error.addProperty("message", "找不到用戶資料");
//            resp.getWriter().write(error.toString());
//        }
//    }
//}
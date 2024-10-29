package web.user.controller;

import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import web.user.service.UserService;
import web.user.service.impl.UserServiceImpl;
import web.user.vo.User;

@WebServlet("/user/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service;
    
    @Override
    public void init() throws ServletException {
        try {
            service = new UserServiceImpl();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        User user = gson.fromJson(req.getReader(), User.class);
        String errorMsg = service.register(user);

        JsonObject respBody = new JsonObject();
        if (errorMsg == null) {
            // 註冊成功
            respBody.addProperty("success", true);
            respBody.addProperty("message", "註冊成功");
        } else {
            // 註冊失敗
            respBody.addProperty("success", false);
            respBody.addProperty("message", errorMsg);
        }
        
        resp.getWriter().write(respBody.toString());
    }
}
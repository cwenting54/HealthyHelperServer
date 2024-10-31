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

@WebServlet("/user/login")
public class LoginController extends HttpServlet {
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
        User loginUser = service.login(user);

        JsonObject respBody = new JsonObject();
        if (loginUser != null) {
        	
        	req.getSession().setAttribute("userId", loginUser.getUserId());
            req.getSession().setAttribute("account", loginUser.getAccount());
           
            respBody.addProperty("success", true);
            respBody.addProperty("userId", loginUser.getUserId());
            respBody.addProperty("account", loginUser.getAccount());
            respBody.addProperty("username", loginUser.getUsername());
            respBody.addProperty("roleID", loginUser.getRoleID());
            respBody.addProperty("userEmail", loginUser.getUserEmail());
        } else {
           
            respBody.addProperty("success", false);
            respBody.addProperty("message", "帳號或密碼錯誤");
        }
        
        resp.getWriter().write(respBody.toString());
    }
}
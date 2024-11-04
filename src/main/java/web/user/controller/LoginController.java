package web.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

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
        
  
        if (user == null || user.getAccount() == null || user.getPassword() == null) {
            JsonObject errorResp = new JsonObject();
            errorResp.addProperty("success", false);
            errorResp.addProperty("message", "請輸入帳號和密碼");
            resp.getWriter().write(errorResp.toString());
            return;
        }

        User loginUser = service.login(user);
        
        System.out.println(loginUser);
        
        JsonObject respBody = new JsonObject();

        if (loginUser != null) { 
            respBody.addProperty("success", true);
            respBody.addProperty("userId", loginUser.getUserId());
            respBody.addProperty("account", loginUser.getAccount());
            respBody.addProperty("username", loginUser.getUsername());
            respBody.addProperty("userEmail", loginUser.getUserEmail());
            respBody.addProperty("phoneno", loginUser.getPhoneno());
            respBody.addProperty("gender", loginUser.getGender());
            respBody.addProperty("roleID", loginUser.getRoleID());
            
            if (loginUser.getBirthday() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                respBody.addProperty("birthday", dateFormat.format(loginUser.getBirthday()));
            }
            
            System.out.println("回應內容：" + respBody.toString());
        } else {
            respBody.addProperty("success", false);
            respBody.addProperty("message", "帳號或密碼錯誤");
        }

        // 確保有這行
        System.out.println("Response Body: " + respBody.toString());

        }
    
}
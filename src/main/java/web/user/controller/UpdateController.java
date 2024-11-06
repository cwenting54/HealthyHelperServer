package web.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

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


@WebServlet("/user/update")
public class UpdateController extends HttpServlet {
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
        
       

        try {
            JsonObject requestData = new Gson().fromJson(req.getReader(), JsonObject.class);
            System.out.println("收到的更新請求資料: " + requestData.toString());
            
            User user = new User();
            
            // 設置必要的 account
            if (!requestData.has("account")) {
                sendError(resp, "缺少account參數");
                return;
            }
            user.setAccount(requestData.get("account").getAsString());
           // String result = service.userUpdate(user);
            
            
            if (requestData.has("account")) {
                user.setUsername(requestData.get("account").getAsString());
            }
        
            if (requestData.has("username")) {
                user.setUsername(requestData.get("username").getAsString());
            }
            if (requestData.has("gender")) {
                user.setGender(requestData.get("gender").getAsInt());
            }
            if (requestData.has("phoneno")) {
                user.setPhoneno(requestData.get("phoneno").getAsString());
            }
            if (requestData.has("userEmail")) {
                user.setUserEmail(requestData.get("userEmail").getAsString());
            }
            if (requestData.has("birthday") && !requestData.get("birthday").isJsonNull()) {
                user.setBirthday(Date.valueOf(requestData.get("birthday").getAsString()));
            }

            
            System.out.println("準備更新用戶資料: " + user.getAccount());
            String result = service.userUpdate(user);
            System.out.println("更新結果: " + result);

            JsonObject respBody = new JsonObject();
            if (result == null) {
                respBody.addProperty("success", true);
                respBody.addProperty("message", "更新成功");
            } else {
                respBody.addProperty("success", false);
                respBody.addProperty("message", result);
            }

            resp.getWriter().write(respBody.toString());
            System.out.println("回應內容: " + respBody.toString());

        } catch (Exception e) {
            e.printStackTrace();
            sendError(resp, "更新過程發生錯誤: " + e.getMessage());
        }
    }

    private void sendError(HttpServletResponse resp, String message) throws IOException {
        JsonObject errorResp = new JsonObject();
        errorResp.addProperty("success", false);
        errorResp.addProperty("message", message);
        resp.getWriter().write(errorResp.toString());
    }
}
    
    
    




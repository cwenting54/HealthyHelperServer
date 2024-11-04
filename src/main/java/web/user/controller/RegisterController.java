package web.user.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Base64;
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

        try {
            // 從請求中讀取 JSON
            JsonObject requestData = new Gson().fromJson(req.getReader(), JsonObject.class);
            
            // 創建 User 物件並設置基本資料
            User user = new User();
            user.setAccount(getStringFromJson(requestData, "account"));
            user.setPassword(getStringFromJson(requestData, "password"));
            user.setUsername(getStringFromJson(requestData, "username"));
            user.setUserEmail(getStringFromJson(requestData, "email"));
            user.setPhoneno(getStringFromJson(requestData, "phone"));
            
            String errorMsg = service.register(user);
       

            // 處理性別
            String genderStr = getStringFromJson(requestData, "gender");
            if (genderStr != null) {
                int gender;
                switch (genderStr) {
                    case "男":
                        gender = 0;
                        break;
                    case "女":
                        gender = 1;
                        break;
                    default:
                        gender = 2;
                }
                user.setGender(gender);
            }

            // 處理生日
            String birthDateStr = getStringFromJson(requestData, "birthDate");
            if (birthDateStr != null && !birthDateStr.isEmpty()) {
                try {
                	Date birthday = Date.valueOf(birthDateStr);
                    user.setBirthday(birthday);
                } catch (IllegalArgumentException e) {
                    sendError(resp, "生日格式不正確");
                    return;
                }
            }

            // 處理角色
            String roleIdStr = getStringFromJson(requestData, "roleId");
            if (roleIdStr != null) {
                user.setRoleID(Integer.parseInt(roleIdStr));
            }

         // 處理營養師證書 (BASE64)
            if ("2".equals(roleIdStr)) {
                String certificateBase64 = getStringFromJson(requestData, "certificate");
                if (certificateBase64 != null && !certificateBase64.isEmpty()) {
                    try {
                        
                        if (certificateBase64.contains(",")) {
                            certificateBase64 = certificateBase64.split(",")[1];
                        }
                        byte[] certificateBytes = Base64.getDecoder().decode(certificateBase64);
                        user.setCertificate(certificateBytes);
                    } catch (IllegalArgumentException e) {
                        sendError(resp, "證書檔案格式不正確");
                        return;
                    }
                }
            }
            // 設置註冊時間
            user.setRegistrationDate(new Timestamp(System.currentTimeMillis()));

      

            JsonObject respBody = new JsonObject();
            if (errorMsg == null) {
                respBody.addProperty("success", true);
                respBody.addProperty("message", "註冊成功");
            } else {
                respBody.addProperty("success", false);
                respBody.addProperty("message", errorMsg);
            }

            resp.getWriter().write(respBody.toString());

        } catch (Exception e) {
            e.printStackTrace();
            sendError(resp, "註冊過程發生錯誤: " + e.getMessage());
        }
    }

    private String getStringFromJson(JsonObject json, String key) {
        return json.has(key) && !json.get(key).isJsonNull() ? 
               json.get(key).getAsString() : null;
    }

    private void sendError(HttpServletResponse resp, String message) throws IOException {
        JsonObject errorResp = new JsonObject();
        errorResp.addProperty("success", false);
        errorResp.addProperty("message", message);
        resp.getWriter().write(errorResp.toString());
    }
}
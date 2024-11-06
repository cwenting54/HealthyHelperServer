package web.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
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
                // 從請求中讀取 JSON，與註冊相同的方式
                JsonObject requestData = new Gson().fromJson(req.getReader(), JsonObject.class);
                
                // 創建 User 物件並設置基本資料
                User user = new User();
                user.setAccount(getStringFromJson(requestData, "account"));
                user.setUserEmail(getStringFromJson(requestData, "userEmail"));
                user.setPhoneno(getStringFromJson(requestData, "phoneno"));
                
                // 處理性別，與註冊相同的方式
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

                // 處理生日，與註冊相同的方式
                String birthDateStr = getStringFromJson(requestData, "birthday");
                if (birthDateStr != null && !birthDateStr.isEmpty()) {
                    try {
                        Date birthday = Date.valueOf(birthDateStr);
                        user.setBirthday(birthday);
                    } catch (IllegalArgumentException e) {
                        sendError(resp, "生日格式不正確");
                        return;
                    }
                }

                // 執行更新
                String result = service.userUpdate(user);

                // 處理響應，與註冊相同的方式
                JsonObject respBody = new JsonObject();
                if (result == null) {
                    respBody.addProperty("success", true);
                    respBody.addProperty("message", "更新成功");
                } else {
                    respBody.addProperty("success", false);
                    respBody.addProperty("message", result);
                }

                resp.getWriter().write(respBody.toString());

            } catch (Exception e) {
                e.printStackTrace();
                sendError(resp, "更新過程發生錯誤: " + e.getMessage());
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
    


package web.society.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.society.service.PostService;
import web.society.service.impl.PostServiceImpl;
import web.society.vo.Post;

@WebServlet("/post")
public class GetPostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService;
@Override
    public void init() throws ServletException {
	try {
		postService = new PostServiceImpl();
	} catch (NamingException e) {
		e.printStackTrace();
	}
    }
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("application/json; charset=UTF-8");

    // 使用 Service 層取得所有貼文
    List<Post> posts = postService.selectPost();

    // 使用 Gson 將資料轉換成 JSON 格式
    Gson gson = new Gson();
    String json = gson.toJson(posts);

    // 將 JSON 回傳到回應中
    response.getWriter().write(json);
}

}

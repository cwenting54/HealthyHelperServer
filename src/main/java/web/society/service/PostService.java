package web.society.service;

import java.util.List;

import web.society.vo.Post;

public interface PostService {
	List<Post> selectPost();
	List<Post> selectPostByUserId(int userId);
	int insertPost(Post post, int userId);
}

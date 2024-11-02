package web.society.dao;

import java.util.List;

import web.society.vo.Post;

public interface PostDao {
	List<Post> selectPost();
	
	List<Post> selectPostByUserId(int userId);
	
	int insertPost(Post post, int userId);
}

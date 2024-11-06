package web.societySophie.service;

import java.util.List;

import java.util.Optional;
import web.societySophie.vo.Post;

public interface PostService {
	List<Post> selectPost();

	List<Post> selectPostByUserId(int userId);

	String insertPost(Post post, int userId);

	String updatePost(Post post);
	
	String deletePost(int postId);

	String likePost(int userId, int postId);

	String deleteLikePost(int userId, int postId);

}

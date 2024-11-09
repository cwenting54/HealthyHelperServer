package web.societySophie.dao;

import java.util.List;

import web.societySophie.vo.Post;

public interface PostDao {
	List<Post> selectPost();

	List<Post> selectPostByUserId(int userId);

	int insertPost(Post post, int userId);

	int updatePost(Post post);
	
	int deletePost(int postId);

	Boolean isPostLikeByUserId(int userId, int postId);

	int likePost(int userId, int postId);
	
	int deletePostLike(int userId, int postId);
}

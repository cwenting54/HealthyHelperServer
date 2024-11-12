package web.societySophie.service.impl;

import java.util.List;
import java.util.Optional;
import javax.naming.NamingException;
import web.societySophie.dao.PostDao;
import web.societySophie.dao.impl.PostDaoImpl;
import web.societySophie.service.PostService;
import web.societySophie.vo.Post;

public class PostServiceImpl implements PostService {
	private PostDao postDao;

	public PostServiceImpl() throws NamingException {
		postDao = new PostDaoImpl();
	}

	@Override
	public List<Post> selectPost() {
		return postDao.selectPost();
	}

	@Override
	public List<Post> selectPostByUserId(int userId) {
		return postDao.selectPostByUserId(userId);
	}

	@Override
	public String insertPost(Post post, int userId) {
		String title = post.getTitle();
		if (title == null ) {
			return "標題長度不可為空";
		} else if(title.length() > 30) {
			return "標題不可大於30個字";
		}
		
		String content = post.getContent();
		if (content == null ) {
			return "內容不可為空";
		}
		int result = postDao.insertPost(post, userId);

		return result > 0 ? null : "新增貼文失敗";
	}

	@Override
	public String updatePost(Post post) {
		String title = post.getTitle();
		if (title == null ) {
			return "標題長度不可為空";
		} else if(title.length() > 30) {
			return "標題不可大於30個字";
		}
		
		String content = post.getTitle();
		if (content == null ) {
			return "內容不可為空";
		}
		int result = postDao.updatePost(post);

		return result > 0 ? null : "更新貼文失敗";
	}
	
	@Override
	public String deletePost(int postId) {
		if (postId <= 0) {
			return "無法刪除";
		}
		int result = postDao.deletePost(postId);
		return result > 0 ? null : "刪除貼文失敗";
	}

	@Override
	public String likePost(int userId, int postId) {
		if(postDao.isPostLikeByUserId(userId, postId) == true) {
			return "重複新增";
		}
		int result = postDao.likePost(userId, postId);
		return result > 0 ? null : "新增貼文讚數失敗";
	}
	
	@Override
	public String deleteLikePost(int userId, int postId) {
		if (postId <= 0) {
			return "無法刪除";
		}

		int result = postDao.deletePostLike(userId, postId);
		return result > 0 ? null : "刪除讚數失敗";
	}
	
	

}

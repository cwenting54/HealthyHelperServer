package web.society.service.impl;

import java.util.List;

import javax.naming.NamingException;

import web.society.dao.PostDao;
import web.society.dao.impl.PostDaoImpl;
import web.society.service.PostService;
import web.society.vo.Post;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertPost(Post post, int userId) {
		// TODO Auto-generated method stub
		return 0;
	}
}

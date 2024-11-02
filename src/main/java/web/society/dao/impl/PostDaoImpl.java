package web.society.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import web.society.dao.PostDao;
import web.society.vo.Post;

public class PostDaoImpl implements PostDao {
	private DataSource ds;

	public PostDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public List<Post> selectPost() {
		String sql = "select * from post";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			var list = new ArrayList<Post>();
			while (rs.next()) {
				Post post = new Post();
				post.setUserId(rs.getInt("userId"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setPicture(rs.getBytes("picture"));
				post.setPostDate(rs.getTimestamp("postDate"));
				post.setLikePost(rs.getInt("likepost"));
				post.setLikeComm(rs.getInt("likecomm"));

				list.add(post);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Post> selectPostByUserId(int userId) {
		String sql = "select * from post where userId = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			try(ResultSet rs = pstmt.executeQuery();) {
				pstmt.setInt(1, userId);
				var list = new ArrayList<Post>();
				while (rs.next()) {
					Post post = new Post();
					post.setUserId(rs.getInt("userId"));
					post.setTitle(rs.getString("title"));
					post.setContent(rs.getString("content"));
					post.setPicture(rs.getBytes("picture"));
					post.setPostDate(rs.getTimestamp("postDate"));
					post.setLikePost(rs.getInt("likepost"));
					post.setLikeComm(rs.getInt("likecomm"));

					list.add(post);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertPost(Post post, int userId) {
		String sql = "insert into Post(userid, title, content, picture, postDate) values(?, ?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {		
			pstmt.setInt(1, userId);
			pstmt.setString(2, post.getTitle());
			pstmt.setString(3, post.getContent());
			pstmt.setBytes(4, post.getPicture());
			pstmt.setTimestamp(5, post.getPostDate());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
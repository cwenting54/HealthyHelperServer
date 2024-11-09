package web.societySophie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import web.societySophie.dao.PostDao;
import web.societySophie.vo.Post;

public class PostDaoImpl implements PostDao {
	private DataSource ds;

	public PostDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/iHealth");
	}

	@Override
	public List<Post> selectPost() {
		String sql = "select p.postId, p.userId, p.title, p.content, p.picture, p.postDate, p.likepost, u.username, u.photoUrl "
				+ "from post p join user u "
				+ "on p.userId = u.userId oder by postDate desc ";
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			var list = new ArrayList<Post>();
			while (rs.next()) {
				Post post = new Post();
				post.setPostId(rs.getInt("postId"));
				post.setUserId(rs.getInt("userId"));
				post.setTitle(rs.getString("title"));
				post.setContent(rs.getString("content"));
				post.setPicture(rs.getBytes("picture"));
				post.setPostDate(rs.getTimestamp("postDate"));
				post.setLikePost(rs.getInt("likepost"));
				post.setUserName(rs.getString("username"));
				post.setPhotoUrl(rs.getString("photoUrl"));

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
		String sql = "select p.postId, p.userId, p.title, p.content, p.picture, p.postDate, p.likepost, u.username, u.photoUrl "
				+ "from post p join user u "
				+ "on p.userId = u.userId where u.userId = ? oder by postDate desc ";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			try (ResultSet rs = pstmt.executeQuery();) {				
				var list = new ArrayList<Post>();
				while (rs.next()) {
					Post post = new Post();
					post.setPostId(rs.getInt("postId"));
					post.setUserId(rs.getInt("userId"));
					post.setTitle(rs.getString("title"));
					post.setContent(rs.getString("content"));
					post.setPicture(rs.getBytes("picture"));
					post.setPostDate(rs.getTimestamp("postDate"));
					post.setLikePost(rs.getInt("likepost"));
					post.setUserName(rs.getString("username"));
					post.setPhotoUrl(rs.getString("photoUrl"));

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
		String sql = "insert into post(userid, title, content, picture) values(?, ?, ?, ?)";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setString(2, post.getTitle());
			pstmt.setString(3, post.getContent());
			pstmt.setBytes(4, post.getPicture());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public int deletePost(int postId) {
		String sql = "delete from post where postId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, postId);

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updatePost(Post post) {
		String sql = "update post set title = ?, content = ?, picture =? where postId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getContent());
			pstmt.setBytes(3, post.getPicture());
			pstmt.setInt(4, post.getPostId());

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}


	@Override
	public Boolean isPostLikeByUserId(int userId, int postId) {
		String sql = "select count(*) from `like` where userId = ? and postId = ?";
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			pstmt.setInt(2, postId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; 
	}

	@Override
	public int likePost(int userId, int postId) {
		String sqlInsert = "insert into `like` (userId, postId) values (?, ?)";
		String sqlUpdate = "update post set likepost = likepost + 1 where postId = ?";

		try (Connection conn = ds.getConnection()) {
			conn.setAutoCommit(false);

			try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
					PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {

				pstmtInsert.setInt(1, userId);
				pstmtInsert.setInt(2, postId);
				int insertResult = pstmtInsert.executeUpdate();

				if (insertResult > 0) {
					pstmtUpdate.setInt(1, postId);
					pstmtUpdate.executeUpdate();
					conn.commit();
					return 1;
				} else {
					conn.rollback();
					return 0;
				}
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int deletePostLike(int userId, int postId) {
	    String sqlDelete = "delete from `like` where userId = ? and postId = ?";
	    String sqlUpdate = "update post set likepost = likepost - 1 where postId = ?";

	    try (Connection conn = ds.getConnection()) {
	        conn.setAutoCommit(false);

	        try (PreparedStatement pstmtDelete = conn.prepareStatement(sqlDelete);
	             PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate)) {

	            pstmtDelete.setInt(1, userId);
	            pstmtDelete.setInt(2, postId);
	            int deleteResult = pstmtDelete.executeUpdate();

	            if (deleteResult > 0) {
	                pstmtUpdate.setInt(1, postId);
	                pstmtUpdate.executeUpdate();
	                conn.commit();
	                return 1; 
	            } else {
	                conn.rollback();
	                return 0; 
	            }
	        } catch (Exception e) {
	            conn.rollback();
	            e.printStackTrace();
	            return 0;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0; 
	    }
	}


}

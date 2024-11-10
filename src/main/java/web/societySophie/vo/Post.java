package web.societySophie.vo;

import java.sql.Timestamp;

public class Post {
    private Integer postId;
    private Integer userId;
    private String title;
    private String content;
    private byte[] picture;
    private Timestamp postDate;
    private Integer likePost;
    private Integer likeComm;
    private String userName;
    private String photoUrl; 

    public Post() {
    }

    public Post(Integer postId, Integer userId, String title, String content, byte[] picture, Timestamp postDate, Integer likePost, Integer likeComm, String userName, String photoUrl) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.postDate = postDate;
        this.likePost = likePost;
        this.likeComm = likeComm;
        this.userName = userName;
        this.photoUrl = photoUrl;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public Integer getLikePost() {
        return likePost;
    }

    public void setLikePost(Integer likePost) {
        this.likePost = likePost;
    }

    public Integer getLikeComm() {
        return likeComm;
    }

    public void setLikeComm(Integer likeComm) {
        this.likeComm = likeComm;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
    
}

package web.user.dao;

public interface UserPhotoDao {
	int insertUserPhoto(int userId, String photoUrl);
	String selectUserPhoto(int userId);
}

package web.user.service.impl;

import javax.naming.NamingException;
import web.user.dao.UserPhotoDao;
import web.user.dao.impl.UserPhotoDaoImpl;
import web.user.service.UserPhotoService;

public class UserPhotoServiceImpl implements UserPhotoService{
	private UserPhotoDao userPhotoDao;
	public UserPhotoServiceImpl() throws NamingException {
		userPhotoDao = new UserPhotoDaoImpl();
	}
	@Override
	public String inserUserPhotoService(int userId, String photoUrl) {
		
		int result = userPhotoDao.insertUserPhoto(userId, photoUrl);

		return result > 0 ? null : "個人照片新增失敗";
	}

}

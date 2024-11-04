package web.map.service.impl;

import java.util.List;

import javax.naming.NamingException;
import web.map.dao.HealthyMapDao;
import web.map.dao.impl.HealthyMapDaoImpl;
import web.map.service.HealthyMapService;
import web.map.vo.HealthyMap;
import web.map.vo.UserFavoriteList;

public class HealthyMapServiceImpl implements HealthyMapService {
	private HealthyMapDao healthyMapDao;

	public HealthyMapServiceImpl() throws NamingException {
		healthyMapDao = new HealthyMapDaoImpl();
	}

	@Override
	public List<HealthyMap> selectRestaurantByRegion(String rcity, String rregion) {
		return healthyMapDao.selectRestaurantByRegion(rcity, rregion);
	}
	
	@Override
	public List<HealthyMap> selectRestaurantByCity(String rcity) {
		return healthyMapDao.selectRestaurantByCity(rcity);
	}

	@Override
	public List<UserFavoriteList> selectUserFavorList(int userId) {
		return healthyMapDao.selectFavorRestaurants(userId);
	}

	@Override
	public String deleteUserFavorRestaurant(int userId, int rid) {
		if (rid <= 0) {
			return "無法刪除";
		}

		int result = healthyMapDao.deleteFavorRestaurants(userId, rid);
		return result > 0 ? null : "刪除失敗";
	}

	@Override
	public String insertUserFavorRestaurant(int userId, int rid) {
		List<UserFavoriteList> userFavorLists = healthyMapDao.selectFavorRestaurants(userId);
		boolean exists = false;
		for (UserFavoriteList favorite : userFavorLists) {
			if (favorite.getRid() == rid) {
				exists = true;
				break;
			}
		}

		if (exists) {
			return "已重複新增";
		}
		int result = healthyMapDao.insertFavorRestaurants(userId, rid);

		return result > 0 ? null : "餐廳新增失敗";
	}


	

}

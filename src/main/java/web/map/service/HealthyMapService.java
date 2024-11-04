package web.map.service;

import java.sql.Timestamp;
import java.util.List;

import web.bodymanagement.vo.BodyManagement;
import web.map.vo.HealthyMap;
import web.map.vo.UserFavoriteList;

public interface HealthyMapService {
	
	List<HealthyMap> selectRestaurantByRegion(String rcity, String rregion);
	
	List<HealthyMap> selectRestaurantByCity(String rcity);
	
	List<UserFavoriteList> selectUserFavorList(int userId);

	String deleteUserFavorRestaurant(int userId, int rid);
	
	String insertUserFavorRestaurant(int userId, int rid);

}

package web.map.service;

import java.util.List;

import web.map.vo.HealthyMap;

public interface HealthyMapService {
	
	List<HealthyMap> selectRestaurantByRegion(String rcity, String rregion);
	
	List<HealthyMap> selectRestaurantByCity(String rcity);
	
	List<HealthyMap> selectUserFavorList(int userId);

	String deleteUserFavorRestaurant(int userId, int rid);
	
	String insertUserFavorRestaurant(int userId, int rid);

}

package web.map.dao;


import java.util.List;

import web.map.vo.HealthyMap;
import web.map.vo.UserFavoriteList;

public interface HealthyMapDao {
	List<HealthyMap> selectFavorRestaurantsDetail(int userId);	
	List<UserFavoriteList> selectUserFavorList(int userId);
	int insertFavorRestaurants(int userId, int rid);
	int deleteFavorRestaurants(int userId, int rid);
	List<HealthyMap> selectRestaurantByRegion(String rregion, String rcity);
	List<HealthyMap> selectRestaurantByCity(String rcity);
}

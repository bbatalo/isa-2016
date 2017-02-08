package project.service;

import java.util.List;

import project.domain.RestaurantManager;

public interface RestManService {
	List<RestaurantManager> getAll();
	
	RestaurantManager addRestaurantManager(RestaurantManager rm);
}

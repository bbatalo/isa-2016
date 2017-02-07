package project.service;

import java.util.List;

import project.domain.Restaurant;

public interface RestaurantService {
	
	Restaurant getRestaurant(String name);
	
	Restaurant addRestaurant(Restaurant r);
	
	List<Restaurant> getAll();
}
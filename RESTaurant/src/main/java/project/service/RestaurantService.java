package project.service;

import project.domain.Restaurant;

public interface RestaurantService {
	
	Restaurant getRestaurant(String name);
	
	Restaurant addRestaurant(Restaurant r);
	
}

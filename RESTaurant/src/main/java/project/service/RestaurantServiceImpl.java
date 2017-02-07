package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.Restaurant;
import project.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public Restaurant getRestaurant(String name) {
		Assert.notNull(name, "name cannot be null.");
		return this.restaurantRepository.findRestaurant(name);
	}

	@Override
	public Restaurant addRestaurant(Restaurant r) {
		
		return this.restaurantRepository.save(r);
	}

}

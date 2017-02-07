package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Restaurant;

public interface RestaurantRepository extends Repository<Restaurant, Long>{
	
	@Query("select r from Restaurant r where r.name = ?1")
	public Restaurant findRestaurant(String name);
	
	public Restaurant save(Restaurant r);
	
}

package project.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import project.domain.RestaurantManager;

public interface RestManRepository extends Repository<RestaurantManager, Long>{

	public List<RestaurantManager> findAll();
	
	public RestaurantManager save(RestaurantManager rm);
}

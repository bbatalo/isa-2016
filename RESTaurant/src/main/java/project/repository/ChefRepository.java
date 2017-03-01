package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Chef;

public interface ChefRepository extends Repository<Chef, Long> {

	public Chef save(Chef chef);
	
	@Query("select c from Chef c")
	public List<Chef> findAll();
	
	@Query("select c from Chef c where c.id = ?1")
	public Chef findChefById(Long id);
}


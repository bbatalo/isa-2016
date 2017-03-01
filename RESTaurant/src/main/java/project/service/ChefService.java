package project.service;

import java.util.List;

import project.domain.Chef;

public interface ChefService {
	Chef save(Chef chef);
	
	Chef getChefById(Long id);
	
	List<Chef> getAll();
}

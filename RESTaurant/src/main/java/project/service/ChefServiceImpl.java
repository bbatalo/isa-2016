package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Chef;
import project.repository.ChefRepository;

@Service
public class ChefServiceImpl implements ChefService {
	
	@Autowired
	private ChefRepository repository;
	
	@Override
	public Chef save(Chef chef) {
		return repository.save(chef);
	}

	@Override
	public Chef getChefById(Long id) {
		return repository.findChefById(id);
	}

	@Override
	public List<Chef> getAll() {
		return repository.findAll();
	}

}

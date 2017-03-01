package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Visit;
import project.repository.VisitRepository;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitRepository repository;
	
	@Override
	public Visit save(Visit visit) {
		return repository.save(visit);
	}

	@Override
	public Visit findById(Long id) {
		return repository.findById(id);
	}

}

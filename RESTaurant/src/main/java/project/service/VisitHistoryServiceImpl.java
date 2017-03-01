package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.VisitHistory;
import project.repository.VisitHistoryRepository;

@Service
public class VisitHistoryServiceImpl implements VisitHistoryService {

	@Autowired
	private VisitHistoryRepository repository;
	
	@Override
	public VisitHistory save(VisitHistory history) {
		return repository.save(history);
	}

	@Override
	public VisitHistory getById(Long id) {
		return repository.findById(id);
	}

}

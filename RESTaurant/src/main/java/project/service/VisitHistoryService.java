package project.service;

import org.springframework.beans.factory.annotation.Autowired;

import project.domain.VisitHistory;

public interface VisitHistoryService {

	public VisitHistory save(VisitHistory history);
	
	public VisitHistory getById(Long id);
	
}

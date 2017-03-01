package project.service;

import project.domain.Visit;

public interface VisitService {

	public Visit save(Visit visit);
	
	public Visit findById(Long id);
}

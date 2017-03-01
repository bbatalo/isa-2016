package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.VisitHistory;

public interface VisitHistoryRepository extends Repository<VisitHistory, Long>{

	VisitHistory save(VisitHistory history);
	
	@Query("select v from VisitHistory v where v.id = ?1")
	VisitHistory findById(Long id);
}

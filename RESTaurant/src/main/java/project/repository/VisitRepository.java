package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Visit;

public interface VisitRepository extends Repository<Visit, Long> {
	
	Visit save(Visit visit);
	
	@Query("select v from Visit v where v.id = ?1")
	Visit findById(Long id);
}

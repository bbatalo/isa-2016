package project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.SystemManager;

public interface SysManRepository extends Repository<SystemManager, Long> {
	
	public SystemManager save(SystemManager sm);
}

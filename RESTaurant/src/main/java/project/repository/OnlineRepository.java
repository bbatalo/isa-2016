package project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Online;

public interface OnlineRepository extends Repository<Online, Long>{

	public Page<Online> findAll(Pageable page);
	
	@Query("select o from Online o where o.id = ?1")
	public Online findOnline(Long id);
	
	/*
	@Query("insert into Online (usr_id) values (?1)")
	public String addOnline(Long id);
	
	@Query("delete o from Online o where o.id = ?1")
	public String removeOnline(Long id);
	*/
	public Online save(Online online);
}

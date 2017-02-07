package project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.User;

public interface UserRepository extends Repository<User, Long> {

	public Page<User> findAll(Pageable pageable);
	
	@Query("select u from User u where u.email = ?1")
	public User findUser(String email);
	
	public User save(User user);
}

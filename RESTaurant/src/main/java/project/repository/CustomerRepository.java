package project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Customer;

public interface CustomerRepository extends Repository<Customer, Long> {

	public Page<Customer> findAll(Pageable pageable);
	
	@Query("select c from Customer c where c.userID = ?1")
	public Customer findCustomerById(Long id);
	
	@Modifying
	@Query("delete from Customer c where c.userID = ?1")
	public void removeCustomerById(Long id);
	
	public Customer save(Customer customer);
}

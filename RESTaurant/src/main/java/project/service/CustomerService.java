package project.service;

import org.springframework.data.domain.Page;

import project.domain.Customer;

public interface CustomerService {

	Page<Customer> getAll();
	
	Customer getCustomerById(Long id);
	
	void deleteCustomerById(Long id);
	
	Customer addCustomer(Customer customer);
	
	void updateCustomerEmail(Customer customer);
	
	void updateCustomerPassword(Customer customer);
	
	void updateCustomerDetails(Customer customer);
}

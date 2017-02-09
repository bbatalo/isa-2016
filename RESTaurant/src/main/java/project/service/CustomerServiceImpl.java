package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.Customer;
import project.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Page<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerById(Long id) {
		Assert.notNull(id, "ID cannot be null");
		return customerRepository.findCustomerById(id);
	}

	@Override
	public void deleteCustomerById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer addCustomer(Customer customer) {
		Assert.notNull(customer, "Cannot be null");
		return customerRepository.save(customer);
	}

	@Override
	public void updateCustomerEmail(Customer customer) {
		Assert.notNull(customer, "Cannot be null");
		customerRepository.updateEmail(customer.getUserID(), customer.getEmail());
	}

	@Override
	public void updateCustomerPassword(Customer customer) {
		Assert.notNull(customer, "Cannot be null");
		customerRepository.updatePassword(customer.getUserID(), customer.getPassword());
		
	}
	
	@Override
	public void updateCustomerDetails(Customer customer) {
		Assert.notNull(customer, "Cannot be null");
		customerRepository.updateDetails(customer.getUserID(), customer.getName(), customer.getSurname(), customer.getAddress(), customer.getDateBirth());
	}

	

}

package project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.domain.Customer;
import project.domain.Online;
import project.domain.dto.PasswordDTO;
import project.service.CustomerService;

@RequestMapping("/index")
@Controller
public class IndexController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/load",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Customer> loadCustomer(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Customer customer = customerService.getCustomerById(online.getUser().getUserID());
			customer.setPassword("");
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return null;
		}

		
	}
	
	@Transactional
	@RequestMapping(value = "updateEmail",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updateEmail(@Context HttpServletRequest request, @RequestBody Customer customer) {
		
		if (customer != null && !customer.getEmail().equals("")) {
			customerService.updateCustomerEmail(customer);
			return new ResponseEntity<String>(customer.getEmail(), HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("No email sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updatePassword",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updatePassword(@Context HttpServletRequest request, @RequestBody PasswordDTO dto) {
		
		if (dto != null && !dto.getCurrentPass().equals("") && !dto.getNewPass().equals("")) {
			Customer tmp = customerService.getCustomerById(dto.getUserID());
			if (tmp != null && tmp.getPassword().equals(dto.getCurrentPass())) {
				customerService.updateCustomerPassword(dto.generateCustomer());
				return new ResponseEntity<String>("Password successfully changed", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Invalid password", HttpStatus.OK);
			}
			
		}
		
		return new ResponseEntity<String>("Invalid data sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updateDetails",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Customer> updateDetails(@Context HttpServletRequest request, @RequestBody Customer customer) {
		
		if (customer != null) {
			customerService.updateCustomerDetails(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
		
		return new ResponseEntity<Customer>(new Customer(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "loadFriends",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Customer>> loadFriends(@Context HttpServletRequest request, @RequestBody PasswordDTO data) {
		
		if (data.getUserID() != null) {
			Customer customer = customerService.getCustomerById(data.getUserID());
			if (customer != null) {
				List<Customer> friends = customer.getFriends();
				List<Customer> friendOf = customer.getFriendOf();
				friends.addAll(friendOf);
				return new ResponseEntity<List<Customer>>(friends, HttpStatus.OK);
			}
		}
		
		return null;
	}
	
	@RequestMapping(value = "/loadCustomers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Customer> loadCustomers(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Customer customer = customerService.getCustomerById(online.getUser().getUserID());
			customer.setPassword("");
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			return null;
		}

		
	}
}

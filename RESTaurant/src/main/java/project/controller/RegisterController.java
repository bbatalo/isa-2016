package project.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.domain.Customer;
import project.domain.UserType;
import project.service.CustomerService;

@RequestMapping("/register")
@Controller
public class RegisterController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
    private JavaMailSender javaMailSender;
    
	
	@RequestMapping(value="/test",
			method = RequestMethod.GET,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> test() {
		
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("bbatalo@hotmail.com");
        message.setSubject("Pozdrav prijatelju");
        message.setText("http://localhost:8080/");
        javaMailSender.send(message);
		
		return new ResponseEntity<String>("USPJEH HOHO", HttpStatus.OK);
	}
	
	@RequestMapping(value="/customer",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> registerCustomer(@RequestBody Customer cst) {
		System.out.println("usao: " + cst);
		cst.setUserType(UserType.CUSTOMER);
		customerService.addCustomer(cst);
		
		return new ResponseEntity<String>("Uspjeh", HttpStatus.OK);
	}
}

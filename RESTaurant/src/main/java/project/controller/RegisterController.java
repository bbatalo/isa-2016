package project.controller;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.Customer;
import project.domain.Token;
import project.domain.UserType;
import project.service.CustomerService;
import project.service.TokenService;

@RequestMapping("/register")
@Controller
public class RegisterController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@RequestMapping(value = "/customer",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> registerCustomer(@Context HttpServletRequest request, @RequestBody Customer cst) {
		
		String hash = UUID.randomUUID().toString();
		
		Token token = new Token(hash, cst.getEmail(), cst.getPassword(), cst.getName(), cst.getSurname());
		token.setExpiryDate(token.calculateExpiryDate());
		tokenService.addToken(token);
		System.out.println(hash);
		
		String appLocation = getURL(request);
		String url = appLocation + "/register/confirm?token=" + hash;
		
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(cst.getEmail());
        message.setSubject("Registration confirmation");
        message.setText("Please follow the link below to confirm your registration. \n\n" + url);
        javaMailSender.send(message);
        
		return new ResponseEntity<String>("Uspjeh", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirm",
			method = RequestMethod.GET)
	public String confirmRegistration(@Context HttpServletRequest request, @RequestParam("token") String token) {
		
		Token tok = tokenService.getTokenByString(token);
		
		if (tok == null) {
			return "redirect:/register.html";
		}
		
		Calendar cal = Calendar.getInstance();
		if ((tok.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return "redirect:/register.html";
		}
		
		Customer cst = new Customer();
		cst.setEmail(tok.getEmail());
		cst.setPassword(tok.getPassword());
		cst.setUserType(UserType.CUSTOMER);
		if (tok.getName() != "")
			cst.setName(tok.getName());
		if (tok.getSurname() != "")
			cst.setSurname(tok.getSurname());
		
		customerService.addCustomer(cst);
		
		return "redirect:/login.html";
		
		
	}
	
	public static String getURL(HttpServletRequest req) {

	    String scheme = req.getScheme();
	    String serverName = req.getServerName();
	    int serverPort = req.getServerPort();        
	    String contextPath = req.getContextPath();    

	    StringBuilder url = new StringBuilder();
	    url.append(scheme).append("://").append(serverName);

	    if (serverPort != 80 && serverPort != 443) {
	        url.append(":").append(serverPort);
	    }

	    url.append(contextPath);

	    return url.toString();
	}
}

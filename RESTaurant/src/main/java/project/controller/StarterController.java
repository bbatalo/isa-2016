package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.service.UserService;

@Controller
public class StarterController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	@ResponseBody
	public String getUserEmail() {
		return this.userService.getUser("bobi@bobinjo.com").getName();
	}
}

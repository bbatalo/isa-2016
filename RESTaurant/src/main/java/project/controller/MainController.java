package project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.domain.Online;
import project.service.OnlineService;
import project.service.UserService;

@Controller
public class MainController {

	@Autowired
	private OnlineService onlineService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= {"/"},
			method = {RequestMethod.GET, RequestMethod.POST})
	public String authorize(@Context HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			
			return "redirect:/login.html";
		}
		
		return "ok";
	}
}

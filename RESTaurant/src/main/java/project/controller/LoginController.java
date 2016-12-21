package project.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.Online;
import project.domain.User;
import project.service.OnlineService;
import project.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private OnlineService onlineService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/online/add",
			method = RequestMethod.POST,
			consumes= MediaType.APPLICATION_JSON,
			produces= MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addOnlineUser(@RequestBody User usr) {
		//Online usr = onlineService.addOnline(online);
		User user = userService.getUser(usr.getEmail());
		//System.out.println(usr.getEmail());
		
		if (user != null) {
			Online onl = new Online();
			onl.setUser(user);
			onl = onlineService.addOnline(onl);
			return "Id logovanog korisnika: " + onl.getUser().getIdUser();
		}
		
		return "Nema nista brt";
	}
}

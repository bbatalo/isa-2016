package project.controller;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.core.MediaType;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.Restaurant;
import project.domain.SystemManager;
import project.domain.User;
import project.service.RestaurantService;
import project.service.SysManService;
import project.service.UserService;

@Controller
public class SysManController {
	@Autowired
	private SysManService sysManService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@RequestMapping(value="/sysman/addSystemManager",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addSystemManager(@RequestBody SystemManager sm){
		
		User u = userService.getUser(sm.getEmail());
		
		if(u == null){

			sm.setUserType(project.domain.UserType.SYSMANAGER);
			sysManService.addSystemManager(sm);
			
			return "Successfully added: " + userService.getUser(sm.getEmail());
		}
		
		return "User email already exists!";
	}
	
	@RequestMapping(value="/sysman/addRestaurant",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addRestaurant(@RequestBody Restaurant r){
		
		
		Restaurant restaurant = restaurantService.getRestaurant(r.getName());
		
		if(restaurant == null){
			
			restaurantService.addRestaurant(r);
			
			return "Successfully added: " + restaurantService.getRestaurant(r.getName());
		}
		
		return "Restaurant name already exists!";
	}
}

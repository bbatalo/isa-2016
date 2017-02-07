package project.controller;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.domain.Page;
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
@RequestMapping("/sysman")
@Controller
public class SysManController {
	@Autowired
	private SysManService sysManService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Transactional
	@RequestMapping(value="/addSystemManager",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addSystemManager(@RequestBody SystemManager sm){
		
		User u = userService.getUser(sm.getEmail());
		
		if(u == null){

			sm.setUserType(project.domain.UserType.SYSMANAGER);
			sysManService.addSystemManager(sm);
			
			return "OK";
		}
		
		return "User email already exists!";
	}
	
	@RequestMapping(value="/getSystemManagers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<SystemManager> getSystemManagers(){
		return sysManService.getAll();
	}
	
	@RequestMapping(value="/getRestaurants",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Restaurant> getRestaurants(){
		return restaurantService.getAll();
	}
	
	@Transactional
	@RequestMapping(value="/addRestaurant",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addRestaurant(@RequestBody Restaurant r){
		
		
		Restaurant restaurant = restaurantService.getRestaurant(r.getName());
		
		if(restaurant == null){
			
			restaurantService.addRestaurant(r);
			
			return "OK";
		}
		
		return "Restaurant name already exists!";
	}
}
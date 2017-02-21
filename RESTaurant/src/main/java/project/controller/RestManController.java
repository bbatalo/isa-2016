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
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.Dish;
import project.domain.Menu;
import project.domain.Online;
import project.domain.Restaurant;
import project.domain.RestaurantManager;
import project.domain.Supplier;
import project.domain.SystemManager;
import project.domain.User;
import project.service.DishService;
import project.service.OnlineService;
import project.service.RestaurantService;
import project.service.RestManService;

@RequestMapping("/restmanager")
@Controller
public class RestManController {
	@Autowired
	private OnlineService onlineService;
	
	@Autowired
	private RestManService restManService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private DishService dishService;
	
	@RequestMapping(value = "/load",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<RestaurantManager> loadRestManager(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			RestaurantManager restmanager = restManService.getRestaurantManagerById(online.getUser().getUserID());
			restmanager.setPassword("");
			return new ResponseEntity<RestaurantManager>(restmanager, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@Transactional
	@RequestMapping(value = "updateRestaurantName",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updateRestaurantName(@Context HttpServletRequest request, @RequestBody Restaurant restaurant) {
		
		if (restaurant != null && !restaurant.getName().equals("")) {
			if(restaurantService.getRestaurantByName(restaurant.getName()) == null){
				restaurantService.updateRestaurantName(restaurant);
				return new ResponseEntity<String>(restaurant.getName(), HttpStatus.OK);
			}else{
				if(restaurantService.getRestaurantByName(restaurant.getName()).getRestaurantID() == restaurant.getRestaurantID()){
					return new ResponseEntity<String>("same", HttpStatus.OK);
				}
				return new ResponseEntity<String>("taken", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("No name sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updateRestaurantDetails",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Restaurant> updateRestaurantDetails(@Context HttpServletRequest request, @RequestBody Restaurant restaurant) {
		
		if (restaurant != null) {
			restaurantService.updateRestaurantDetails(restaurant);
			return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
		}
		
		return new ResponseEntity<Restaurant>(new Restaurant(), HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value="/addDish",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addDish(@RequestBody Dish dish){
		
		Dish d = dishService.getDishByLabel(dish.getLabel());
		
		if(d == null){

			dishService.addDish(dish);
			
			return "OK";
		}
		
		return "Dish with that label already exists!";
	}
	
	@Transactional
	@RequestMapping(value="/removeDish",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String removeDish(@RequestBody Dish dish){
		
		Dish d = dishService.getDishByLabel(dish.getLabel());
		
		if(d != null){

			dishService.deleteDishById(d.getIdDish());
			
			return "Dish removed.";
		}
		
		return "Dish not found!";
	}
	
	@RequestMapping(value="/getDishes",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Dish> getDishes(@Context HttpServletRequest request, @RequestBody Menu menu){
		return this.dishService.getDishesByMenuId(menu.getIdMenu());
	}
}

package project.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import project.domain.Bid;
import project.domain.Dish;
import project.domain.Drink;
import project.domain.DrinkOffer;
import project.domain.DrinksMenu;
import project.domain.Grocery;
import project.domain.GroceryOffer;
import project.domain.Menu;
import project.domain.Offer;
import project.domain.Online;
import project.domain.RestTable;
import project.domain.Restaurant;
import project.domain.RestaurantManager;
import project.domain.SeatingArrangement;
import project.domain.Segment;
import project.domain.Supplier;
import project.domain.SystemManager;
import project.domain.User;
import project.domain.dto.BidDTO;
import project.domain.dto.OfferAcceptedDTO;
import project.messaging.OfferMessenger;
import project.service.BidService;
import project.service.DishService;
import project.service.DrinkOfferService;
import project.service.DrinkService;
import project.service.GroceryOfferService;
import project.service.GroceryService;
import project.service.OfferService;
import project.service.OnlineService;
import project.service.RestaurantService;
import project.service.SegmentService;
import project.service.TableService;
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
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private GroceryService groceryService;
	
	@Autowired
	private BidService bidService;
	
	@Autowired
	private SegmentService segmentService;
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private DrinkOfferService drinkOfferService;
	
	@Autowired
	private GroceryOfferService groceryOfferService;
	
	@Autowired
	private OfferMessenger offerMessenger;
	
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
	
	@Transactional
	@RequestMapping(value="/addDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addDrink(@RequestBody Drink drink){
		
		Drink d = drinkService.getDrinkByLabel(drink.getLabel());
		
		if(d == null){

			drinkService.addDrink(drink);
			
			return "OK";
		}
		
		return "Drink with that label already exists!";
	}
	
	@Transactional
	@RequestMapping(value="/removeDrink",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String removeDrink(@RequestBody Drink drink){
		
		Drink d = drinkService.getDrinkByLabel(drink.getLabel());
		
		if(d != null){

			drinkService.deleteDrinkById(d.getIdDrink());
			
			return "Drink removed.";
		}
		
		return "Drink not found!";
	}
	
	@RequestMapping(value="/getDrinks",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Drink> getDrinks(@Context HttpServletRequest request, @RequestBody DrinksMenu drinksMenu){
		return this.drinkService.getDrinksByDrinksMenuId(drinksMenu.getIdDrinkMenu());
	}
	
	@RequestMapping(value="/getAllDrinks",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Drink> getAllDrinks(@Context HttpServletRequest request){
		return this.drinkService.getAllDrinks();
	}
	
	@RequestMapping(value="/getAllGroceries",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Grocery> getAllGroceries(@Context HttpServletRequest request){
		return this.groceryService.getAllGroceries();
	}
	
	@RequestMapping(value="/addBid",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addBid(@Context HttpServletRequest request, @RequestBody Bid bid) throws ParseException{
		/*
		Bid realBid = new Bid();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		realBid.setBeginning(sdf.parse(bid.getBeginning()));
		realBid.setEnd(sdf.parse(bid.getEnd()));
		realBid.setGroceries(bid.getGroceries());
		realBid.setDrinks(bid.getDrinks());
		realBid.setManager(bid.getManager());
		*/
		bidService.addBid(bid);
		
		return "OK";
	}
	
	@Transactional
	@RequestMapping(value="/addSegment",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addSegment(@RequestBody Segment segment){
		
		Segment s = segmentService.getSegmentByLabel(segment.getLabel());
		
		if(s == null){

			segmentService.addSegment(segment);
			
			return "OK";
		}
		
		return "Segment with that label already exists!";
	}
	
	@Transactional
	@RequestMapping(value="/removeSegment",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String removeSegment(@RequestBody Segment segment){
		
		Segment s = segmentService.getSegmentByLabel(segment.getLabel());
		
		if(s != null){
			
			segmentService.deleteSegmentById(s.getIdSegment());
			
			return "Segment removed.";
		}
		
		return "Segment not found!";
	}
	
	@RequestMapping(value="/getSegments",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Segment> getSegments(@Context HttpServletRequest request, @RequestBody SeatingArrangement seatingArrangement){
		return this.segmentService.getSegmentsBySeatingId(seatingArrangement.getIdSeating());
	}
	
	@Transactional
	@RequestMapping(value="/addTable",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String addTable(@RequestBody RestTable restTable){
		
		RestTable table = tableService.getTableByCode(restTable.getTableCode());
		
		
		if(table == null){
			Segment s = segmentService.getSegmentByLabel(restTable.getSegment().getLabel());
			
			restTable.setSegment(s);
			
			tableService.addTable(restTable);
		
			return "Table successfully added!";
		}
		
		return "Unexpected error";
	}
	
	@Transactional
	@RequestMapping(value="/removeTable",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String removeTable(@RequestBody RestTable restTable){
		
		tableService.deleteTableByCode(restTable.getTableCode());
		
		return "Table successfully removed!";
	}
	
	@RequestMapping(value="/getTables",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<RestTable> getTables(@Context HttpServletRequest request, @RequestBody Segment segment){
		return tableService.getTablesBySegmentId(segment.getIdSegment());
	}
	
	@RequestMapping(value="/getOffers",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Offer> getOffers(@Context HttpServletRequest request, @RequestBody Restaurant restaurant){
		return offerService.getOffersByManagerId(restaurant.getRestaurantID());
	}
	
	@Transactional
	@RequestMapping(value="/acceptOffer",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	@ResponseBody
	public String acceptOffer(@Context HttpServletRequest request, @RequestBody Offer offer){
		System.out.println(offer.getIdOffer());
		
		System.out.println(offer.getBid().idBid);
		
		Bid realBid = bidService.getBid(offer.getBid().idBid);
		
		List<Offer> realOffers = offerService.getOffersByBidId(realBid.getIdBid());
		
		Offer realOffer = offerService.getOfferById(offer.getIdOffer());
		
		OfferAcceptedDTO dto = new OfferAcceptedDTO();
		dto.setManagerName(realOffer.getBid().getManager().getName());
		dto.setManagerSurname(realOffer.getBid().getManager().getSurname());
		dto.setRestaurantName(realOffer.getBid().getManager().getRestaurant().getName());
		dto.setReceiverID(realOffer.getSupplier().getUserID());
		offerMessenger.sendOfferAcceptedTo(dto);
		
		for(Offer o : realOffers){
			drinkOfferService.removeDrinkOfferByOfferId(o.getIdOffer());
			
			groceryOfferService.removeGroceryOfferByOfferId(o.getIdOffer());
			
			offerService.deleteOfferById(o.getIdOffer());
		}
		
		bidService.deleteBidById(realBid.getIdBid());
		
		return "OK";
	}
}

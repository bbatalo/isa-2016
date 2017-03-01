package project.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.domain.Bartender;
import project.domain.Chef;
import project.domain.Customer;
import project.domain.Dish;
import project.domain.DishOrder;
import project.domain.DishType;
import project.domain.Drink;
import project.domain.DrinkOrder;
import project.domain.EmployeeRole;
import project.domain.Online;
import project.domain.Reservation;
import project.domain.RestOrder;
import project.domain.RestTable;
import project.domain.Restaurant;
import project.domain.Segment;
import project.domain.Shift;
import project.domain.Visit;
import project.domain.VisitHistory;
import project.domain.Waiter;
import project.domain.WorkSchedule;
import project.domain.dto.PasswordDTO;
import project.domain.dto.ReservDTO;
import project.messaging.BarMessenger;
import project.messaging.CookMessenger;
import project.messaging.OrderMessenger;
import project.service.ChefService;
import project.service.CustomerService;
import project.service.DishOrderService;
import project.service.DishService;
import project.service.DrinkOrderService;
import project.service.DrinkService;
import project.service.EmployeeService;
import project.service.OrderService;
import project.service.ReservationService;
import project.service.RestaurantService;
import project.service.SegmentService;
import project.service.TableService;
import project.service.VisitHistoryService;
import project.service.VisitService;

@RequestMapping("/reservations")
@Controller
public class ReservationsController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private SegmentService segmentService;
	
	@Autowired
	private TableService tableService;
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private DishOrderService dishOrderService;
	
	@Autowired
	private DrinkOrderService drinkOrderService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private OrderMessenger orderMessenger;
	
	@Autowired
	private CookMessenger cookMessenger;
	
	@Autowired
	private BarMessenger barMessenger;
	
	@Autowired
	private VisitHistoryService historyService;
	
	@Autowired
	private VisitService visitService;
	
	@RequestMapping(value = "/getRestaurants",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Restaurant>> loadCustomer(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			List<Restaurant> restaurants = restaurantService.getAll();
			return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@Transactional
	@RequestMapping(value = "/clean",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> clean(@Context HttpServletRequest request, @RequestBody PasswordDTO dto) {

		if (dto != null) {
			Customer cst = customerService.getCustomerById(dto.getUserID());
			if (cst != null) {
				reservationService.removeAllByStatusAndUserId(cst.getUserID(), "Setup");
				return new ResponseEntity<String>("Clean", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("No user", HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/term",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<ReservDTO> term(@Context HttpServletRequest request, @RequestBody ReservDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Customer customer = customerService.getCustomerById(online.getUser().getUserID());
			Restaurant rest = restaurantService.getRestaurantById(dto.getRestID());
			
			Date now = new Date();
			if (checkDate(now, dto.getDate()) == -1) {
				dto.setStatus("Wrong date");
				return new ResponseEntity<ReservDTO>(dto, HttpStatus.OK);
			} else if (checkDate(now, dto.getDate()) == 0) {
				if (checkTime(now, dto.getTime()) == -1) {
					dto.setStatus("Wrong time");
					return new ResponseEntity<ReservDTO>(dto, HttpStatus.OK);
				}
			}						
			
			Reservation res = new Reservation();
			res.setCustomer(customer);
			res.setRestaurant(rest);
			res.setStatus("Setup");
			res.setDate(dto.getDate());
			res.setTime(dto.getTime());
			res.setDuration(dto.getDuration());
			
			reservationService.save(res);
			
			ReservDTO tmp = new ReservDTO(res);
			
			return new ResponseEntity<ReservDTO>(tmp, HttpStatus.OK);
		} 
		
		return null;
	}
	
	@RequestMapping(value = "/getSegments",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Segment>> loadSegments(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Restaurant rest = restaurantService.getRestaurantById(id);
			List<Segment> segments = segmentService.getSegmentsBySeatingId(rest.seatingArrangement.getIdSeating());
			return new ResponseEntity<List<Segment>>(segments, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getDrinks",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Set<Drink>> loadDrinks(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Restaurant rest = restaurantService.getRestaurantById(id);
			Set<Drink> drinks = rest.drinksMenu.getDrinks();
			return new ResponseEntity<Set<Drink>>(drinks, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getDishes",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Set<Dish>> loadDishes(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Restaurant rest = restaurantService.getRestaurantById(id);
			Set<Dish> dishes = rest.menu.dishes;
			return new ResponseEntity<Set<Dish>>(dishes, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getReservations",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Reservation>> loadReservations(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Customer cst = customerService.getCustomerById(online.getUser().getUserID());
			List<Reservation> reservations = cst.getReservations();
			
			return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getOrder",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<RestOrder> loadOrder(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Reservation res = reservationService.findById(id);
			Customer cst = customerService.getCustomerById(online.getUser().getUserID());
			for (RestOrder r : res.getOrder()) {
				if (r.getCustomer().getUserID() == cst.getUserID())
					return new ResponseEntity<RestOrder>(r, HttpStatus.OK);
			}
			return new ResponseEntity<RestOrder>(new RestOrder(), HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getOrderDrinks",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<DrinkOrder>> loadOrderDrinks(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			RestOrder order = orderService.getById(id);
			List<DrinkOrder> drinks = order.getDrinkOrders();
			return new ResponseEntity<List<DrinkOrder>>(drinks, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/getOrderDishes",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<DishOrder>> loadOrderDishes(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			RestOrder order = orderService.getById(id);
			List<DishOrder> dishes = order.getDishOrders();
			return new ResponseEntity<List<DishOrder>>(dishes, HttpStatus.OK);
		} else {
			return null;
		}
	}
	@RequestMapping(value = "/getTables",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<RestTable>> loadTables(@Context HttpServletRequest request, @RequestBody ReservDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			List<RestTable> tables = tableService.getTablesBySegmentId(dto.getSegmentID());
			
			Restaurant rest = restaurantService.getRestaurantById(dto.getRestID());
			List<Reservation> tmp = rest.getReservations();
			List<Reservation> reservs = purge(tmp, dto);
			System.out.println(reservs.size());
			List<Long> tableIDs = new ArrayList<Long>();
			if (reservs.size() > 0) {
				for (Reservation r : reservs) {
					for (RestTable t : r.getTables()) {
						tableIDs.add(t.getIdTable());
					}
				}
			}
			
			if (tableIDs.size() > 0) {
				for (RestTable t : tables) {
					if (tableIDs.contains(t.getIdTable())) {
						t.setStatus("taken");
					}
				}
			}
			
			
			return new ResponseEntity<List<RestTable>>(tables, HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@RequestMapping(value = "/selectTable",
			method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML)
	public ResponseEntity<String> selectTable(@Context HttpServletRequest request, @RequestBody ReservDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			Reservation reservation = reservationService.findById(dto.getReservID());
			RestTable table = tableService.getTableByCode(dto.getCurrentTableCode());
			
			//vremenska zauzetost
			if (table != null && reservation != null) {
				List<Reservation> temp = reservation.getRestaurant().getReservations();
				List<Reservation> reservs = purge(temp, dto);
				System.out.println(reservs.size());
				List<Long> tableIDs = new ArrayList<Long>();
				if (reservs.size() > 0) {
					for (Reservation r : reservs) {
						for (RestTable t : r.getTables()) {
							tableIDs.add(t.getIdTable());
						}
					}
				}
				
				if (tableIDs.contains(table.getIdTable()))
					table.setStatus("taken");
				
				if (!table.getStatus().equals("taken")) {
					reservation.getTables().add(table);
					reservationService.save(reservation);
					//table.setStatus("taken");
					//tableService.addTable(table);
					return new ResponseEntity<String>("Success", HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<String>("Null madjija", HttpStatus.OK);
			}
			
			return new ResponseEntity<String>("Unavailable", HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@RequestMapping(value = "/finish",
			method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML)
	public ResponseEntity<String> finish(@Context HttpServletRequest request, @RequestBody ReservDTO dto) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			//DATI MU GA MALO PO
			Reservation res = reservationService.findById(dto.getReservID());
			res.setStatus("Reserved");
			
			RestOrder order = new RestOrder();
			order.setCustomer(res.getCustomer());
			order.setOnArrival(dto.isOnArrival());
			order.setReservation(res);
			order.setTable(tableService.getTableByCode(dto.getCurrentTableCode()));
			order.setStatus("Fresh");
			
			orderService.save(order);
			
			order.setDishOrders(new ArrayList<DishOrder>());
			order.setDrinkOrders(new ArrayList<DrinkOrder>());
			for (Long dishID : dto.getDishes()) {
				DishOrder dOrder = new DishOrder();
				dOrder.setStatus("Fresh");
				dOrder.setDish(dishService.getDishById(dishID));
				dOrder.setOrder(order);
				
				order.getDishOrders().add(dOrder);
				dishOrderService.save(dOrder);
			}
			for (Long drinkID : dto.getDrinks()) {
				DrinkOrder dOrder = new DrinkOrder();
				dOrder.setStatus("Fresh");
				dOrder.setDrink(drinkService.getDrinkById(drinkID));
				dOrder.setOrder(order);
				order.getDrinkOrders().add(dOrder);
				drinkOrderService.save(dOrder);
			}
			
			orderService.save(order);
			reservationService.save(res);
			
			Waiter kelner = null;
			
			RestTable sto = order.getTable();
			Segment seg = sto.getSegment();
			Set<Shift> smene = seg.getShifts();
			for(Shift s : smene){
				Date pocetak = s.getShiftBegins();
				Date kraj = s.getShiftEnds();
				Date trenutak = new Date();
				if( trenutak.before(kraj) && trenutak.after(pocetak) ){
					if(s.getEmployee().getRole()==EmployeeRole.WAITER){
						kelner = (Waiter) s.getEmployee();
						break;
					}
				}
			}
			
			order.setWaiter(kelner);
			orderService.save(order);
			
			if (kelner == null) {
				return new ResponseEntity<String>("Nema kelnera brt", HttpStatus.OK);
			}
			
			orderMessenger.sendRequestTo(kelner,order);
			orderMessenger.sendUpdateTo(order, kelner);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
			
			//ODAVDE POSALJI STA HOCES KOME HOCES
			//za referencu pogledaj metodu sendRequest u IndexControlleru
			//u principu samo napravis Messenger klasu kao onu moju, samo sa cime ti hoces kao topicom
			//i ovde pozoves njenu metodu da posaljes poruku, tipa
			//messenger.sendToKonobar(konobar, order.getId());
			//ili sta ti vec odgovara
			//pocupaj konobara iz smena i segmenata, to nisam gledao kako je implementirano...
			
			
		} else {
			return null;
		}
	}
	
	
	@RequestMapping(value = "/pay",
			method = RequestMethod.GET,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> pay(@Context HttpServletRequest request, @RequestParam("id") Long id) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Customer cst = customerService.getCustomerById(online.getUser().getUserID());
			RestOrder order = orderService.getById(id);
			order.setStatus("Paid");
			
			VisitHistory history = cst.getHistory();
			if (history == null) {
				history = new VisitHistory();
				cst.setHistory(history);
				historyService.save(history);
			}
			
			Visit visit = new Visit();
			visit.setHistory(history);
			visit.setOrder(order);
			
			visitService.save(visit);
			historyService.save(history);
			customerService.save(cst);
			orderService.save(order);
			
			//poslati poruku konobaru
			
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	private int checkDate(Date now, Date reserv) {
		if (reserv.getYear() > now.getYear()) {
			return 1;
		} else if (reserv.getYear() == now.getYear()) {
			if (reserv.getMonth() > now.getMonth()) {
				return 1;
			} else if (reserv.getMonth() == now.getMonth()) {
				if (reserv.getDate() > now.getDate()) {
					return 1;
				} else if (reserv.getDate() == now.getDate()) {
					return 0;
				}
			}
		}
	
		return -1;
	}
	
	@SuppressWarnings("deprecation")
	private int checkTime(Date now, Date reserv) {
		if (reserv.getHours() > now.getHours()) {
			return 1;
		} else if (reserv.getHours() == now.getHours())
			if (reserv.getMinutes() >= now.getMinutes())
				return 1;
		
		return -1;
	}
	
	private List<Reservation> purge(List<Reservation> in, ReservDTO dto) {
		List<Reservation> out = new ArrayList<Reservation>();
		for (Reservation r : in) {
			if (r.getId() == dto.getReservID()) {
				continue;
			}
			
			if (checkDate(r.getDate(), dto.getDate()) == 0) {
				System.out.println("isti datum");
				
				Calendar calDTO1 = Calendar.getInstance();
				calDTO1.setTime(dto.getTime());
				System.out.println(calDTO1.toString());
				
				Calendar calDTO2 = Calendar.getInstance();
				calDTO2.setTime(dto.getTime());
				calDTO2.add(Calendar.HOUR_OF_DAY, dto.getDuration());
				System.out.println(calDTO2.toString());
				
				Calendar calRES1 = Calendar.getInstance();
				calRES1.setTime(r.getTime());
				System.out.println(calRES1.toString());
				
				Calendar calRES2 = Calendar.getInstance();
				calRES2.setTime(r.getTime());
				calRES2.add(Calendar.HOUR_OF_DAY, r.getDuration());
				System.out.println(calRES2.toString());
				
				if ((calDTO1.before(calRES1) && calDTO2.before(calRES1)) || (calRES1.before(calDTO1) && calRES2.before(calDTO1))) {
					System.out.println("vremensko razilazenje");
					continue;
				}
				
				out.add(r);
			}
		}
		
		
		return out;
	}
	
	
	@RequestMapping(value = "/loadOrders",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<RestOrder>> loadRequests(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		if (online != null) {
			Waiter employee = (Waiter) employeeService.getEmployeeById(online.getUser().getUserID());
			List<RestOrder> serving = new ArrayList<RestOrder>();
			for (RestOrder r : employee.getCurrentlyServing()) {
				serving.add(r);
			}
			return new ResponseEntity<List<RestOrder>>(serving, HttpStatus.OK);
		} else {
			return null;
		}
		

	}
	
	@RequestMapping(value = "getDishOrder",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<DishOrder>> getByOrderDi(@Context HttpServletRequest request, @RequestBody Long orderID) {
		Long number = orderID;
		if (number!=null){
			List<DishOrder> retVal = orderService.getById(number).getDishOrders();
			return new ResponseEntity<List<DishOrder>>(retVal, HttpStatus.OK);
		}
		
		return null;
	}
	
	
	@RequestMapping(value = "getDrinkOrder",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<DrinkOrder>> getByOrderDr(@Context HttpServletRequest request, @RequestBody Long orderID) {
		Long number = orderID;
		if (number!=null){
			List<DrinkOrder> retVal = orderService.getById(number).getDrinkOrders();
			return new ResponseEntity<List<DrinkOrder>>(retVal, HttpStatus.OK);
		}
		
		return null;
	}
	
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@RequestMapping(value = "/sendMeal",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_HTML)
	public ResponseEntity<String> sendMeal(@Context HttpServletRequest request, @RequestBody Long mealID) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			//DATI MU GA MALO PO
			DishOrder dish = dishOrderService.getDishById(mealID);
			dish.setStatus("Sent");
			dishOrderService.save(dish);
			RestOrder order = orderService.getById(dish.getOrder().getId());
			
			ArrayList<Chef> chefs = new ArrayList<Chef>();
			
			Restaurant restoran = order.getReservation().getRestaurant();
			WorkSchedule raspored = restoran.getSchedule();
			Set<Shift> smene = raspored.getShifts();
			for(Shift s : smene){
				if(s.getEmployee().getRole()==EmployeeRole.CHEF){
					Date pocetak = s.getShiftBegins();
					Date kraj = s.getShiftEnds();
					Date trenutak = new Date();
					if( trenutak.before(kraj) && trenutak.after(pocetak) ){
						Chef kuhar = (Chef) s.getEmployee();
						if(kuhar.getType()==dish.getDish().getType() || kuhar.getType()==DishType.UNIVERSAL)
							chefs.add(kuhar);

					}
				}
			}
			
			for (int i=0; i<chefs.size(); i++){
				Chef hef = chefs.get(i);
				hef.getDishOrders().add(dish);
				chefService.save(hef);
				dish.getChef().add(hef);
				dishOrderService.save(dish);
			}
			Waiter w = order.getWaiter();
			orderService.save(order);
			
			for(int i=0; i<chefs.size(); i++){
				Chef hef = chefs.get(i);
				cookMessenger.sendRequestTo(hef,dish);
			}
			
			cookMessenger.sendUpdateTo(w, dish);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
			
			//ODAVDE POSALJI STA HOCES KOME HOCES
			//za referencu pogledaj metodu sendRequest u IndexControlleru
			//u principu samo napravis Messenger klasu kao onu moju, samo sa cime ti hoces kao topicom
			//i ovde pozoves njenu metodu da posaljes poruku, tipa
			//messenger.sendToKonobar(konobar, order.getId());
			//ili sta ti vec odgovara
			//pocupaj konobara iz smena i tih sranja, to nisam gledao kako je implementirano...
			
			
		} else {
			return null;
		}
	}
	
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@RequestMapping(value = "/sendDrink",
			method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML)
	public ResponseEntity<String> sendDrink(@Context HttpServletRequest request, @RequestBody Long drinkID) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			DrinkOrder drink = drinkOrderService.getDrinkOrderById(drinkID);
			drink.setStatus("Sent");
			drinkOrderService.save(drink);
			RestOrder order = orderService.getById(drink.getOrder().getId());
			
			Bartender barmen = null;

			Restaurant restoran = order.getReservation().getRestaurant();
			WorkSchedule raspored = restoran.getSchedule();
			Set<Shift> smene = raspored.getShifts();
			for(Shift s : smene){
				if(s.getEmployee().getRole()==EmployeeRole.BARTENDER){
					Date pocetak = s.getShiftBegins();
					Date kraj = s.getShiftEnds();
					Date trenutak = new Date();
					if( trenutak.before(kraj) && trenutak.after(pocetak) ){
						barmen = (Bartender) s.getEmployee();
						

					}
				}
			}
			
			Waiter w = order.getWaiter();
			orderService.save(order);
			drink.setBartender(barmen);
			drinkOrderService.save(drink);
			
			barMessenger.sendRequestTo(barmen,drink);
			
			barMessenger.sendUpdateTo(w, drink);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
			
			//ODAVDE POSALJI STA HOCES KOME HOCES
			//za referencu pogledaj metodu sendRequest u IndexControlleru
			//u principu samo napravis Messenger klasu kao onu moju, samo sa cime ti hoces kao topicom
			//i ovde pozoves njenu metodu da posaljes poruku, tipa
			//messenger.sendToKonobar(konobar, order.getId());
			//ili sta ti vec odgovara
			//pocupaj konobara iz smena i tih sranja, to nisam gledao kako je implementirano...
			
			
		} else {
			return null;
		}
	}
	
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@RequestMapping(value = "/deleteDrink",
			method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML)
	public ResponseEntity<String> deleteDrink(@Context HttpServletRequest request, @RequestBody Long drinkID) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			DrinkOrder drink = drinkOrderService.getDrinkOrderById(drinkID);
			RestOrder order = orderService.getById(drink.getOrder().getId());
			
			order.getDrinkOrders().remove(drink);
			order.setStatus("Updated");
			orderService.save(order);
			drink.setStatus("Deleted");
			drinkOrderService.save(drink);
			
			
			Waiter kelner = null;
			
			RestTable sto = order.getTable();
			Segment seg = sto.getSegment();
			Set<Shift> smene = seg.getShifts();
			for(Shift s : smene){
				Date pocetak = s.getShiftBegins();
				Date kraj = s.getShiftEnds();
				Date trenutak = new Date();
				if( trenutak.before(kraj) && trenutak.after(pocetak) ){
					if(s.getEmployee().getRole()==EmployeeRole.WAITER){
						kelner = (Waiter) s.getEmployee();
						break;
					}
				}
			}
			
			order.setWaiter(kelner);
			orderService.save(order);
			
			orderMessenger.sendRequestTo(order,kelner);
			orderMessenger.sendUpdateTo(kelner, order);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
			
			//ODAVDE POSALJI STA HOCES KOME HOCES
			//za referencu pogledaj metodu sendRequest u IndexControlleru
			//u principu samo napravis Messenger klasu kao onu moju, samo sa cime ti hoces kao topicom
			//i ovde pozoves njenu metodu da posaljes poruku, tipa
			//messenger.sendToKonobar(konobar, order.getId());
			//ili sta ti vec odgovara
			//pocupaj konobara iz smena i tih sranja, to nisam gledao kako je implementirano...
			
			
		} else {
			return null;
		}
	}
	
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	@RequestMapping(value = "/deleteMeal",
			method = RequestMethod.POST,
			produces = MediaType.TEXT_HTML)
	public ResponseEntity<String> deleteMeal(@Context HttpServletRequest request, @RequestBody Long mealID) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			
			DishOrder meal = dishOrderService.getDishById(mealID);
			RestOrder order = orderService.getById(meal.getOrder().getId());
			order.getDishOrders().remove(meal);
			order.setStatus("Updated");
			orderService.save(order);
			meal.setStatus("Deleted");
			dishOrderService.save(meal);
			
			
			
			
			Waiter kelner = null;
			
			RestTable sto = order.getTable();
			Segment seg = sto.getSegment();
			Set<Shift> smene = seg.getShifts();
			for(Shift s : smene){
				Date pocetak = s.getShiftBegins();
				Date kraj = s.getShiftEnds();
				Date trenutak = new Date();
				if( trenutak.before(kraj) && trenutak.after(pocetak) ){
					if(s.getEmployee().getRole()==EmployeeRole.WAITER){
						kelner = (Waiter) s.getEmployee();
						break;
					}
				}
			}
			
			order.setWaiter(kelner);
			orderService.save(order);
			
			orderMessenger.sendRequestTo(order,kelner);
			orderMessenger.sendUpdateTo(kelner, order);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
			
			//ODAVDE POSALJI STA HOCES KOME HOCES
			//za referencu pogledaj metodu sendRequest u IndexControlleru
			//u principu samo napravis Messenger klasu kao onu moju, samo sa cime ti hoces kao topicom
			//i ovde pozoves njenu metodu da posaljes poruku, tipa
			//messenger.sendToKonobar(konobar, order.getId());
			//ili sta ti vec odgovara
			//pocupaj konobara iz smena i tih sranja, to nisam gledao kako je implementirano...
			
			
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/loadCookOrders",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<DishOrder>> loadCookOrders(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		if (online != null) {
			Chef employee = (Chef) employeeService.getEmployeeById(online.getUser().getUserID());
			List<DishOrder> serving = new ArrayList<DishOrder>();
			for (DishOrder r : employee.getDishOrders()) {
				serving.add(r);
			}
			return new ResponseEntity<List<DishOrder>>(serving, HttpStatus.OK);
		} else {
			return null;
		}
		

	}
	
	@RequestMapping(value = "/loadBarOrders",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<DrinkOrder>> loadBarOrders(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		if (online != null) {
			Bartender employee = (Bartender) employeeService.getEmployeeById(online.getUser().getUserID());
			List<DrinkOrder> serving = new ArrayList<DrinkOrder>();
			for (DrinkOrder r : employee.getDrinkOrder()) {
				serving.add(r);
			}
			return new ResponseEntity<List<DrinkOrder>>(serving, HttpStatus.OK);
		} else {
			return null;
		}
		

	}
}

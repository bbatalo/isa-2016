package project.controller;

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

import project.domain.Customer;
import project.domain.Online;
import project.domain.Reservation;
import project.domain.Restaurant;
import project.domain.dto.PasswordDTO;
import project.domain.dto.ReservDTO;
import project.service.CustomerService;
import project.service.ReservationService;
import project.service.RestaurantService;

@RequestMapping("/reservations")
@Controller
public class ReservationsController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ReservationService reservationService;
	
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
			if (!checkDate(now, dto.getDate())) {
				dto.setStatus("Wrong date");
				return new ResponseEntity<ReservDTO>(dto, HttpStatus.OK);
			}
			
			if (!checkTime(now, dto.getTime())) {
				dto.setStatus("Wrong time");
				return new ResponseEntity<ReservDTO>(dto, HttpStatus.OK);
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
	
	@SuppressWarnings("deprecation")
	private boolean checkDate(Date now, Date reserv) {
		if (reserv.getYear() > now.getYear())
			return true;
		else if (reserv.getYear() == now.getYear())
			if (reserv.getMonth() > now.getMonth())
				return true;
			else if (reserv.getMonth() == now.getMonth())
				if (reserv.getDate() >= now.getDate())
					return true;
			
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private boolean checkTime(Date now, Date reserv) {
		if (reserv.getHours() > now.getHours()) {
			return true;
		} else if (reserv.getHours() == now.getHours())
			if (reserv.getMinutes() >= now.getMinutes())
				return true;
		
		return false;
	}
}

package project.controller;

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

import project.domain.Online;
import project.domain.Supplier;
import project.domain.dto.PasswordDTO;
import project.service.OnlineService;
import project.service.SupplierService;

@RequestMapping("/supplier")
@Controller
public class SupplierController {
	@Autowired
	private OnlineService onlineService;
	
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(value = "/load",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Supplier> loadSupplier(@Context HttpServletRequest request) {
		
		Online online = (Online) request.getSession().getAttribute("user");
		
		if (online != null) {
			Supplier supplier = supplierService.getSupplierById(online.getUser().getUserID());
			supplier.setPassword("");
			return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		} else {
			return null;
		}

		
	}
	
	@Transactional
	@RequestMapping(value = "updateEmail",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updateEmail(@Context HttpServletRequest request, @RequestBody Supplier supplier) {
		
		if (supplier != null && !supplier.getEmail().equals("")) {
			if(supplierService.getSupplierByEmail(supplier.getEmail()) == null){
				supplierService.updateSupplierEmail(supplier);
				return new ResponseEntity<String>(supplier.getEmail(), HttpStatus.OK);
			}else{
				if(supplierService.getSupplierByEmail(supplier.getEmail()).getUserID().equals(supplier.getUserID())){
					return new ResponseEntity<String>("same", HttpStatus.OK);
				}
				return new ResponseEntity<String>("taken", HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("No email sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updatePassword",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.TEXT_PLAIN)
	public ResponseEntity<String> updatePassword(@Context HttpServletRequest request, @RequestBody PasswordDTO dto) {
		
		if (dto != null && !dto.getCurrentPass().equals("") && !dto.getNewPass().equals("")) {
			Supplier tmp = supplierService.getSupplierById(dto.getUserID());
			if (tmp != null && tmp.getPassword().equals(dto.getCurrentPass())) {
				supplierService.updateSupplierPassword(dto.generateSupplier());
				return new ResponseEntity<String>("Password successfully changed", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Invalid password", HttpStatus.OK);
			}
			
		}
		
		return new ResponseEntity<String>("Invalid data sent", HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "updateDetails",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Supplier> updateDetails(@Context HttpServletRequest request, @RequestBody Supplier supplier) {
		
		if (supplier != null) {
			supplierService.updateSupplierDetails(supplier);
			return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
		}
		
		return new ResponseEntity<Supplier>(new Supplier(), HttpStatus.OK);
	}
}

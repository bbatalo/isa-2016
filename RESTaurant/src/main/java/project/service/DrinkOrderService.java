package project.service;

import java.util.List;

import project.domain.DrinkOrder;

public interface DrinkOrderService {

	DrinkOrder save(DrinkOrder drinkOrder);
	
	DrinkOrder getDrinkOrderById(Long id);
	
	void deleteDrinkOrderById(Long id);
	
	List<DrinkOrder> getAll();
}

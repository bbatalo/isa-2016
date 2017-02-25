package project.repository;

import org.springframework.data.repository.Repository;

import project.domain.GroceryOffer;

public interface GroceryOfferRepository extends Repository<GroceryOffer, Long>{
	public GroceryOffer save(GroceryOffer groceryOffer);
}

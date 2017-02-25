package project.repository;

import org.springframework.data.repository.Repository;

import project.domain.DrinkOffer;

public interface DrinkOfferRepository extends Repository<DrinkOffer, Long>{
	public DrinkOffer save(DrinkOffer drinkOffer);
}

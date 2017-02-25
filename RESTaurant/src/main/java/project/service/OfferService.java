package project.service;

import java.util.List;

import project.domain.Offer;

public interface OfferService {
	Offer addOffer(Offer offer);
		
	void deleteOfferById(Long id);
	
	List<Offer> getAllOffers();
}

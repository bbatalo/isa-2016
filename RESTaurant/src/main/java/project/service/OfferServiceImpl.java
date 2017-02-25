package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Offer;
import project.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService{
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Override
	public Offer addOffer(Offer offer) {
		return offerRepository.save(offer);
	}

	@Override
	public void deleteOfferById(Long id) {
		offerRepository.removeOfferById(id);
	}

	@Override
	public List<Offer> getAllOffers() {
		return offerRepository.findAllOffers();
	}

}

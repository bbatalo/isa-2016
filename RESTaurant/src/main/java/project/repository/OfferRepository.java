package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Offer;

public interface OfferRepository extends Repository<Offer, Long>{
	public Offer save(Offer offer);
	
	@Modifying
	@Query("delete from Offer o where o.idOffer = ?1")
	public void removeOfferById(Long id);
	
	@Query("select o from Offer o")
	public List<Offer> findAllOffers();
}

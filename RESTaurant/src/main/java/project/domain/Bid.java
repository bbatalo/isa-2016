/***********************************************************************
 * Module:  Bid.java
 * Author:  Bojan
 * Purpose: Defines the Class Bid
 ***********************************************************************/
package project.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bid {
	@Id
	@GeneratedValue
	@Column(name="bid_id", nullable = false)
	public long idBid;
	
	@Column(name="bid_begin")
	public Date beginning;
	
	@Column(name="big_end")
	public Date end;
	
	@ManyToMany(mappedBy="bids")
	@JsonIgnore
	public Set<Drink> drinks;
	
	@ManyToMany(mappedBy="bids")
	@JsonIgnore
	public Set<Grocery> groceries;
	
	@ManyToOne
	@JoinColumn(name="usr_id")
	public RestaurantManager manager;
	//public Set<Offer> offers;
   
   /*
   public java.util.Collection<Drink> getDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<Drink>();
      return drinks;
   }
   
   public java.util.Iterator getIteratorDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<Drink>();
      return drinks.iterator();
   }
   
   public void setDrinks(java.util.Collection<Drink> newDrinks) {
      removeAllDrinks();
      for (java.util.Iterator iter = newDrinks.iterator(); iter.hasNext();)
         addDrinks((Drink)iter.next());
   }
   
   public void addDrinks(Drink newDrink) {
      if (newDrink == null)
         return;
      if (this.drinks == null)
         this.drinks = new java.util.ArrayList<Drink>();
      if (!this.drinks.contains(newDrink))
         this.drinks.add(newDrink);
   }
   
   public void removeDrinks(Drink oldDrink) {
      if (oldDrink == null)
         return;
      if (this.drinks != null)
         if (this.drinks.contains(oldDrink))
            this.drinks.remove(oldDrink);
   }
   
   public void removeAllDrinks() {
      if (drinks != null)
         drinks.clear();
   }
   public java.util.Collection<Grocery> getGroceries() {
      if (groceries == null)
         groceries = new java.util.ArrayList<Grocery>();
      return groceries;
   }
   
   public java.util.Iterator getIteratorGroceries() {
      if (groceries == null)
         groceries = new java.util.ArrayList<Grocery>();
      return groceries.iterator();
   }
   
   public void setGroceries(java.util.Collection<Grocery> newGroceries) {
      removeAllGroceries();
      for (java.util.Iterator iter = newGroceries.iterator(); iter.hasNext();)
         addGroceries((Grocery)iter.next());
   }
   
   public void addGroceries(Grocery newGrocery) {
      if (newGrocery == null)
         return;
      if (this.groceries == null)
         this.groceries = new java.util.ArrayList<Grocery>();
      if (!this.groceries.contains(newGrocery))
         this.groceries.add(newGrocery);
   }
   
   public void removeGroceries(Grocery oldGrocery) {
      if (oldGrocery == null)
         return;
      if (this.groceries != null)
         if (this.groceries.contains(oldGrocery))
            this.groceries.remove(oldGrocery);
   }
   
   public void removeAllGroceries() {
      if (groceries != null)
         groceries.clear();
   }
   public RestaurantManager getManager() {
      return manager;
   }
   
   public void setManager(RestaurantManager newRestorauntManager) {
      if (this.manager == null || !this.manager.equals(newRestorauntManager))
      {
         if (this.manager != null)
         {
            RestaurantManager oldRestorauntManager = this.manager;
            this.manager = null;
            //oldRestorauntManager.removeBids(this);
         }
         if (newRestorauntManager != null)
         {
            this.manager = newRestorauntManager;
            //this.manager.addBids(this);
         }
      }
   }
   public java.util.Collection<Offer> getOffers() {
      if (offers == null)
         offers = new java.util.ArrayList<Offer>();
      return offers;
   }
   
   public java.util.Iterator getIteratorOffers() {
      if (offers == null)
         offers = new java.util.ArrayList<Offer>();
      return offers.iterator();
   }
   
   public void setOffers(java.util.Collection<Offer> newOffers) {
      removeAllOffers();
      for (java.util.Iterator iter = newOffers.iterator(); iter.hasNext();)
         addOffers((Offer)iter.next());
   }
   
   public void addOffers(Offer newOffer) {
      if (newOffer == null)
         return;
      if (this.offers == null)
         this.offers = new java.util.ArrayList<Offer>();
      if (!this.offers.contains(newOffer))
      {
         this.offers.add(newOffer);
         newOffer.addBids(this);      
      }
   }
   
   public void removeOffers(Offer oldOffer) {
      if (oldOffer == null)
         return;
      if (this.offers != null)
         if (this.offers.contains(oldOffer))
         {
            this.offers.remove(oldOffer);
            oldOffer.removeBids(this);
         }
   }
   
   public void removeAllOffers() {
      if (offers != null)
      {
         Offer oldOffer;
         for (java.util.Iterator iter = getIteratorOffers(); iter.hasNext();)
         {
            oldOffer = (Offer)iter.next();
            iter.remove();
            oldOffer.removeBids(this);
         }
      }
   }
   */
}
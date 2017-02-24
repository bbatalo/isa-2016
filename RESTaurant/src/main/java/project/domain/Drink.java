/***********************************************************************
 * Module:  Drink.java
 * Author:  Bojan
 * Purpose: Defines the Class Drink
 ***********************************************************************/
package project.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Drink {
	@Id
	@GeneratedValue
	@Column(name="drink_id", nullable = false)
	public long idDrink;
	
	@Column(name="drink_label", nullable = false)
	public String label;
	
	@Column(name="drink_description")
	public String description;
	
	@Column(name="drink_price", nullable = false)
	public float price;
	
	@ManyToOne
	@JoinColumn(name="drinks_menu_id")
	public DrinksMenu drinksMenu;
	
	@ManyToMany
	@JoinColumn(name="bid_id")
	public Set<Bid> bids;
	
	public Drink() {}

	public long getIdDrink() {
		return idDrink;
	}

	public void setIdDrink(long idDrink) {
		this.idDrink = idDrink;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
   /*
   public java.util.Collection<DrinkRating> ratings;
   public java.util.Collection<DrinksMenu> drinkMenu;
   public java.util.Collection<DrinksOrder> drinkOrders;
   
   
   public java.util.Collection<DrinkRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<DrinkRating>();
      return ratings;
   }
   
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<DrinkRating>();
      return ratings.iterator();
   }
   
   public void setRatings(java.util.Collection<DrinkRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((DrinkRating)iter.next());
   }
   
   public void addRatings(DrinkRating newDrinkRating) {
      if (newDrinkRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<DrinkRating>();
      if (!this.ratings.contains(newDrinkRating))
      {
         this.ratings.add(newDrinkRating);
         newDrinkRating.setDrink(this);      
      }
   }
   
   public void removeRatings(DrinkRating oldDrinkRating) {
      if (oldDrinkRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldDrinkRating))
         {
            this.ratings.remove(oldDrinkRating);
            oldDrinkRating.setDrink((Drink)null);
         }
   }
   
   public void removeAllRatings() {
      if (ratings != null)
      {
         DrinkRating oldDrinkRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldDrinkRating = (DrinkRating)iter.next();
            iter.remove();
            oldDrinkRating.setDrink((Drink)null);
         }
      }
   }
   public java.util.Collection<DrinksMenu> getDrinkMenu() {
      if (drinkMenu == null)
         drinkMenu = new java.util.ArrayList<DrinksMenu>();
      return drinkMenu;
   }
   
   public java.util.Iterator getIteratorDrinkMenu() {
      if (drinkMenu == null)
         drinkMenu = new java.util.ArrayList<DrinksMenu>();
      return drinkMenu.iterator();
   }
   
   public void setDrinkMenu(java.util.Collection<DrinksMenu> newDrinkMenu) {
      removeAllDrinkMenu();
      for (java.util.Iterator iter = newDrinkMenu.iterator(); iter.hasNext();)
         addDrinkMenu((DrinksMenu)iter.next());
   }
   
   public void addDrinkMenu(DrinksMenu newDrinksMenu) {
      if (newDrinksMenu == null)
         return;
      if (this.drinkMenu == null)
         this.drinkMenu = new java.util.ArrayList<DrinksMenu>();
      if (!this.drinkMenu.contains(newDrinksMenu))
      {
         this.drinkMenu.add(newDrinksMenu);
         newDrinksMenu.addDrinks(this);      
      }
   }
   
   public void removeDrinkMenu(DrinksMenu oldDrinksMenu) {
      if (oldDrinksMenu == null)
         return;
      if (this.drinkMenu != null)
         if (this.drinkMenu.contains(oldDrinksMenu))
         {
            this.drinkMenu.remove(oldDrinksMenu);
            oldDrinksMenu.removeDrinks(this);
         }
   }
   
   public void removeAllDrinkMenu() {
      if (drinkMenu != null)
      {
         DrinksMenu oldDrinksMenu;
         for (java.util.Iterator iter = getIteratorDrinkMenu(); iter.hasNext();)
         {
            oldDrinksMenu = (DrinksMenu)iter.next();
            iter.remove();
            oldDrinksMenu.removeDrinks(this);
         }
      }
   }
   public java.util.Collection<DrinksOrder> getDrinkOrders() {
      if (drinkOrders == null)
         drinkOrders = new java.util.ArrayList<DrinksOrder>();
      return drinkOrders;
   }
   
   public java.util.Iterator getIteratorDrinkOrders() {
      if (drinkOrders == null)
         drinkOrders = new java.util.ArrayList<DrinksOrder>();
      return drinkOrders.iterator();
   }
   
   public void setDrinkOrders(java.util.Collection<DrinksOrder> newDrinkOrders) {
      removeAllDrinkOrders();
      for (java.util.Iterator iter = newDrinkOrders.iterator(); iter.hasNext();)
         addDrinkOrders((DrinksOrder)iter.next());
   }
   
   public void addDrinkOrders(DrinksOrder newDrinksOrder) {
      if (newDrinksOrder == null)
         return;
      if (this.drinkOrders == null)
         this.drinkOrders = new java.util.ArrayList<DrinksOrder>();
      if (!this.drinkOrders.contains(newDrinksOrder))
      {
         this.drinkOrders.add(newDrinksOrder);
         newDrinksOrder.addDrinks(this);      
      }
   }
   
   public void removeDrinkOrders(DrinksOrder oldDrinksOrder) {
      if (oldDrinksOrder == null)
         return;
      if (this.drinkOrders != null)
         if (this.drinkOrders.contains(oldDrinksOrder))
         {
            this.drinkOrders.remove(oldDrinksOrder);
            oldDrinksOrder.removeDrinks(this);
         }
   }
   
   public void removeAllDrinkOrders() {
      if (drinkOrders != null)
      {
         DrinksOrder oldDrinksOrder;
         for (java.util.Iterator iter = getIteratorDrinkOrders(); iter.hasNext();)
         {
            oldDrinksOrder = (DrinksOrder)iter.next();
            iter.remove();
            oldDrinksOrder.removeDrinks(this);
         }
      }
   }
	*/
}
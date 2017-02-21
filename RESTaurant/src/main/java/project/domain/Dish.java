/***********************************************************************
 * Module:  Dish.java
 * Author:  Bojan
 * Purpose: Defines the Class Dish
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dish {
	
	@Id
	@GeneratedValue
	@Column(name="dish_id", nullable = false)
	public long idDish;
	
	@Column(name="dish_label", nullable = false)
	public String label;
	
	@Column(name="dish_description")
	public String description;
	
	@Column(name="dish_price", nullable = false)
	public float price;
   
	//public java.util.Collection<DishesOrder> dishOrders;
	
	//public java.util.Collection<DishRating> ratings;
	
	@ManyToOne
	@JoinColumn(name="menu_id")
	public Menu menu;
	
	public Dish() {}
   /*
   public java.util.Collection<DishesOrder> getDishOrders() {
      if (dishOrders == null)
         dishOrders = new java.util.ArrayList<DishesOrder>();
      return dishOrders;
   }
   
   public java.util.Iterator getIteratorDishOrders() {
      if (dishOrders == null)
         dishOrders = new java.util.ArrayList<DishesOrder>();
      return dishOrders.iterator();
   }
   
   public void setDishOrders(java.util.Collection<DishesOrder> newDishOrders) {
      removeAllDishOrders();
      for (java.util.Iterator iter = newDishOrders.iterator(); iter.hasNext();)
         addDishOrders((DishesOrder)iter.next());
   }
   
   public void addDishOrders(DishesOrder newDishesOrder) {
      if (newDishesOrder == null)
         return;
      if (this.dishOrders == null)
         this.dishOrders = new java.util.ArrayList<DishesOrder>();
      if (!this.dishOrders.contains(newDishesOrder))
      {
         this.dishOrders.add(newDishesOrder);
         newDishesOrder.addDishes(this);      
      }
   }
   
   public void removeDishOrders(DishesOrder oldDishesOrder) {
      if (oldDishesOrder == null)
         return;
      if (this.dishOrders != null)
         if (this.dishOrders.contains(oldDishesOrder))
         {
            this.dishOrders.remove(oldDishesOrder);
            oldDishesOrder.removeDishes(this);
         }
   }
   
   public void removeAllDishOrders() {
      if (dishOrders != null)
      {
         DishesOrder oldDishesOrder;
         for (java.util.Iterator iter = getIteratorDishOrders(); iter.hasNext();)
         {
            oldDishesOrder = (DishesOrder)iter.next();
            iter.remove();
            oldDishesOrder.removeDishes(this);
         }
      }
   }
   public java.util.Collection<DishRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<DishRating>();
      return ratings;
   }
   
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<DishRating>();
      return ratings.iterator();
   }
   
   public void setRatings(java.util.Collection<DishRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((DishRating)iter.next());
   }
   
   public void addRatings(DishRating newDishRating) {
      if (newDishRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<DishRating>();
      if (!this.ratings.contains(newDishRating))
      {
         this.ratings.add(newDishRating);
         newDishRating.setDish(this);      
      }
   }

   public void removeRatings(DishRating oldDishRating) {
      if (oldDishRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldDishRating))
         {
            this.ratings.remove(oldDishRating);
            oldDishRating.setDish((Dish)null);
         }
   }
   
   public void removeAllRatings() {
      if (ratings != null)
      {
         DishRating oldDishRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldDishRating = (DishRating)iter.next();
            iter.remove();
            oldDishRating.setDish((Dish)null);
         }
      }
   }
   public java.util.Collection<Menu> getMenu() {
      if (menu == null)
         menu = new java.util.ArrayList<Menu>();
      return menu;
   }
   
   public java.util.Iterator getIteratorMenu() {
      if (menu == null)
         menu = new java.util.ArrayList<Menu>();
      return menu.iterator();
   }
   public void setMenu(java.util.Collection<Menu> newMenu) {
      removeAllMenu();
      for (java.util.Iterator iter = newMenu.iterator(); iter.hasNext();)
         addMenu((Menu)iter.next());
   }
   
   public void addMenu(Menu newMenu) {
      if (newMenu == null)
         return;
      if (this.menu == null)
         this.menu = new java.util.ArrayList<Menu>();
      if (!this.menu.contains(newMenu))
      {
         this.menu.add(newMenu);
         newMenu.addDishes(this);      
      }
   }
   
   public void removeMenu(Menu oldMenu) {
      if (oldMenu == null)
         return;
      if (this.menu != null)
         if (this.menu.contains(oldMenu))
         {
            this.menu.remove(oldMenu);
            oldMenu.removeDishes(this);
         }
   }
   
   public void removeAllMenu() {
      if (menu != null)
      {
         Menu oldMenu;
         for (java.util.Iterator iter = getIteratorMenu(); iter.hasNext();)
         {
            oldMenu = (Menu)iter.next();
            iter.remove();
            oldMenu.removeDishes(this);
         }
      }
   }
	*/

	public long getIdDish() {
		return idDish;
	}

	public void setIdDish(long idDish) {
		this.idDish = idDish;
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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
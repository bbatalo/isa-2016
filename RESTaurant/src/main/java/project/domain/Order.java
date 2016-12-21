/***********************************************************************
 * Module:  Order.java
 * Author:  Bojan
 * Purpose: Defines the Class Order
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 6c5f179e-6f3f-4396-8759-a4e240e7f56c */
public class Order {
   /** @pdOid 37ee0092-d2d4-4f66-a5eb-286d66785829 */
   public long idOrder;
   /** @pdOid 90e2df86-36fe-4277-b132-221c0a3a8020 */
   public java.util.Date time;
   /** @pdOid 90c4633b-1b5b-4e88-8daa-8cfa97ae655d */
   public java.lang.String status;
   
   /** @pdRoleInfo migr=no name=DishesOrder assc=orderedDishes coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<DishesOrder> dishes;
   /** @pdRoleInfo migr=no name=DrinksOrder assc=orderedDrinks coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<DrinksOrder> drinks;
   /** @pdRoleInfo migr=no name=Reservation assc=order mult=0..1 side=A */
   public Reservation reservation;
   /** @pdRoleInfo migr=no name=Restaurant assc=ordersRest mult=0..1 side=A */
   public Restaurant restaurant;
   /** @pdRoleInfo migr=no name=Customer assc=orders mult=0..1 side=A */
   public Customer customer;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<DishesOrder> getDishes() {
      if (dishes == null)
         dishes = new java.util.ArrayList<DishesOrder>();
      return dishes;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDishes() {
      if (dishes == null)
         dishes = new java.util.ArrayList<DishesOrder>();
      return dishes.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDishes */
   public void setDishes(java.util.Collection<DishesOrder> newDishes) {
      removeAllDishes();
      for (java.util.Iterator iter = newDishes.iterator(); iter.hasNext();)
         addDishes((DishesOrder)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDishesOrder */
   public void addDishes(DishesOrder newDishesOrder) {
      if (newDishesOrder == null)
         return;
      if (this.dishes == null)
         this.dishes = new java.util.ArrayList<DishesOrder>();
      if (!this.dishes.contains(newDishesOrder))
         this.dishes.add(newDishesOrder);
   }
   
   /** @pdGenerated default remove
     * @param oldDishesOrder */
   public void removeDishes(DishesOrder oldDishesOrder) {
      if (oldDishesOrder == null)
         return;
      if (this.dishes != null)
         if (this.dishes.contains(oldDishesOrder))
            this.dishes.remove(oldDishesOrder);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDishes() {
      if (dishes != null)
         dishes.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<DrinksOrder> getDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<DrinksOrder>();
      return drinks;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<DrinksOrder>();
      return drinks.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrinks */
   public void setDrinks(java.util.Collection<DrinksOrder> newDrinks) {
      removeAllDrinks();
      for (java.util.Iterator iter = newDrinks.iterator(); iter.hasNext();)
         addDrinks((DrinksOrder)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrinksOrder */
   public void addDrinks(DrinksOrder newDrinksOrder) {
      if (newDrinksOrder == null)
         return;
      if (this.drinks == null)
         this.drinks = new java.util.ArrayList<DrinksOrder>();
      if (!this.drinks.contains(newDrinksOrder))
         this.drinks.add(newDrinksOrder);
   }
   
   /** @pdGenerated default remove
     * @param oldDrinksOrder */
   public void removeDrinks(DrinksOrder oldDrinksOrder) {
      if (oldDrinksOrder == null)
         return;
      if (this.drinks != null)
         if (this.drinks.contains(oldDrinksOrder))
            this.drinks.remove(oldDrinksOrder);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDrinks() {
      if (drinks != null)
         drinks.clear();
   }
   /** @pdGenerated default parent getter */
   public Restaurant getRestaurant() {
      return restaurant;
   }
   
   /** @pdGenerated default parent setter
     * @param newRestaurant */
   public void setRestaurant(Restaurant newRestaurant) {
      if (this.restaurant == null || !this.restaurant.equals(newRestaurant))
      {
         if (this.restaurant != null)
         {
            Restaurant oldRestaurant = this.restaurant;
            this.restaurant = null;
//            oldRestaurant.removeOrders(this);
         }
         if (newRestaurant != null)
         {
            this.restaurant = newRestaurant;
//            this.restaurant.addOrders(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Customer getCustomer() {
      return customer;
   }
   
   /** @pdGenerated default parent setter
     * @param newCustomer */
   public void setCustomer(Customer newCustomer) {
      if (this.customer == null || !this.customer.equals(newCustomer))
      {
         if (this.customer != null)
         {
            Customer oldCustomer = this.customer;
            this.customer = null;
            //oldCustomer.removeOrders(this);
         }
         if (newCustomer != null)
         {
            this.customer = newCustomer;
            //this.customer.addOrders(this);
         }
      }
   }

}
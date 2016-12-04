/***********************************************************************
 * Module:  Drink.java
 * Author:  Bojan
 * Purpose: Defines the Class Drink
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 239f6de0-7c1c-43ee-a806-79a7db4c199d */
public class Drink {
   /** @pdOid 4ff28a33-a7e6-484b-acd6-2565fd9406fe */
   public long idDrink;
   /** @pdOid 9ef9acbb-47c0-4316-be50-25826ac6e8b4 */
   public java.lang.String label;
   /** @pdOid 2e45e04d-2c0d-48e2-aaf4-ed0e4e3fdd5d */
   public java.lang.String description;
   /** @pdOid c10e39e6-a96e-42eb-abb8-ccdd5254d5a7 */
   public float price;
   
   /** @pdRoleInfo migr=no name=DrinkRating assc=drinkRating coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<DrinkRating> ratings;
   /** @pdRoleInfo migr=no name=DrinksMenu assc=pica coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<DrinksMenu> drinkMenu;
   /** @pdRoleInfo migr=no name=DrinksOrder assc=drinksInAnOrder coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<DrinksOrder> drinkOrders;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<DrinkRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<DrinkRating>();
      return ratings;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<DrinkRating>();
      return ratings.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRatings */
   public void setRatings(java.util.Collection<DrinkRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((DrinkRating)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrinkRating */
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
   
   /** @pdGenerated default remove
     * @param oldDrinkRating */
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
   
   /** @pdGenerated default removeAll */
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
   /** @pdGenerated default getter */
   public java.util.Collection<DrinksMenu> getDrinkMenu() {
      if (drinkMenu == null)
         drinkMenu = new java.util.ArrayList<DrinksMenu>();
      return drinkMenu;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrinkMenu() {
      if (drinkMenu == null)
         drinkMenu = new java.util.ArrayList<DrinksMenu>();
      return drinkMenu.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrinkMenu */
   public void setDrinkMenu(java.util.Collection<DrinksMenu> newDrinkMenu) {
      removeAllDrinkMenu();
      for (java.util.Iterator iter = newDrinkMenu.iterator(); iter.hasNext();)
         addDrinkMenu((DrinksMenu)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrinksMenu */
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
   
   /** @pdGenerated default remove
     * @param oldDrinksMenu */
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
   
   /** @pdGenerated default removeAll */
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
   /** @pdGenerated default getter */
   public java.util.Collection<DrinksOrder> getDrinkOrders() {
      if (drinkOrders == null)
         drinkOrders = new java.util.ArrayList<DrinksOrder>();
      return drinkOrders;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrinkOrders() {
      if (drinkOrders == null)
         drinkOrders = new java.util.ArrayList<DrinksOrder>();
      return drinkOrders.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrinkOrders */
   public void setDrinkOrders(java.util.Collection<DrinksOrder> newDrinkOrders) {
      removeAllDrinkOrders();
      for (java.util.Iterator iter = newDrinkOrders.iterator(); iter.hasNext();)
         addDrinkOrders((DrinksOrder)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrinksOrder */
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
   
   /** @pdGenerated default remove
     * @param oldDrinksOrder */
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
   
   /** @pdGenerated default removeAll */
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

}
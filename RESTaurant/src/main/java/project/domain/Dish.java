/***********************************************************************
 * Module:  Dish.java
 * Author:  Bojan
 * Purpose: Defines the Class Dish
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 7920bc95-0f72-4d02-8a92-1691e84dd635 */
public class Dish {
   /** @pdOid 678e4c33-c5c4-4b15-b49d-a33be1878860 */
   public long idDish;
   /** @pdOid 8567b64e-6a23-4850-9c1f-001a85f44234 */
   public java.lang.String label;
   /** @pdOid d6b96f53-df99-4551-b8bf-bf6015125c13 */
   public java.lang.String description;
   /** @pdOid bf28d272-8309-4ffa-95a8-88e3c2b15969 */
   public float price;
   
   /** @pdRoleInfo migr=no name=DishesOrder assc=dishesInAOrder coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<DishesOrder> dishOrders;
   /** @pdRoleInfo migr=no name=DishRating assc=dishesRating coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<DishRating> ratings;
   /** @pdRoleInfo migr=no name=Menu assc=dishes coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<Menu> menu;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<DishesOrder> getDishOrders() {
      if (dishOrders == null)
         dishOrders = new java.util.ArrayList<DishesOrder>();
      return dishOrders;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDishOrders() {
      if (dishOrders == null)
         dishOrders = new java.util.ArrayList<DishesOrder>();
      return dishOrders.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDishOrders */
   public void setDishOrders(java.util.Collection<DishesOrder> newDishOrders) {
      removeAllDishOrders();
      for (java.util.Iterator iter = newDishOrders.iterator(); iter.hasNext();)
         addDishOrders((DishesOrder)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDishesOrder */
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
   
   /** @pdGenerated default remove
     * @param oldDishesOrder */
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
   
   /** @pdGenerated default removeAll */
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
   /** @pdGenerated default getter */
   public java.util.Collection<DishRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<DishRating>();
      return ratings;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<DishRating>();
      return ratings.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRatings */
   public void setRatings(java.util.Collection<DishRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((DishRating)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDishRating */
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
   
   /** @pdGenerated default remove
     * @param oldDishRating */
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
   
   /** @pdGenerated default removeAll */
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
   /** @pdGenerated default getter */
   public java.util.Collection<Menu> getMenu() {
      if (menu == null)
         menu = new java.util.ArrayList<Menu>();
      return menu;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorMenu() {
      if (menu == null)
         menu = new java.util.ArrayList<Menu>();
      return menu.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newMenu */
   public void setMenu(java.util.Collection<Menu> newMenu) {
      removeAllMenu();
      for (java.util.Iterator iter = newMenu.iterator(); iter.hasNext();)
         addMenu((Menu)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newMenu */
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
   
   /** @pdGenerated default remove
     * @param oldMenu */
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
   
   /** @pdGenerated default removeAll */
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

}
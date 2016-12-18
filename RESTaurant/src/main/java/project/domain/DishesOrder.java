/***********************************************************************
 * Module:  DishesOrder.java
 * Author:  Bojan
 * Purpose: Defines the Class DishesOrder
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 8862fac3-3739-4864-97f1-b578955a982c */
public class DishesOrder {
   /** @pdOid e9fdfe80-b64a-4a56-a736-fc4a464a9b7a */
   public long idDishesOrder;
   /** @pdOid 83e7a8c0-67d9-4979-9784-b55a485bc928 */
   public java.lang.String status;
   
   /** @pdRoleInfo migr=no name=Chef assc=dishPreparation coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Chef> chef;
   /** @pdRoleInfo migr=no name=Dish assc=dishesInAOrder coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<Dish> dishes;
   /** @pdRoleInfo migr=no name=Customer assc=customerDish mult=0..1 side=A */
   public Customer customer;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Chef> getChef() {
      if (chef == null)
         chef = new java.util.ArrayList<Chef>();
      return chef;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorChef() {
      if (chef == null)
         chef = new java.util.ArrayList<Chef>();
      return chef.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newChef */
   public void setChef(java.util.Collection<Chef> newChef) {
      removeAllChef();
      for (java.util.Iterator iter = newChef.iterator(); iter.hasNext();)
         addChef((Chef)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newChef */
   public void addChef(Chef newChef) {
      if (newChef == null)
         return;
      if (this.chef == null)
         this.chef = new java.util.ArrayList<Chef>();
      if (!this.chef.contains(newChef))
      {
         this.chef.add(newChef);
        // newChef.addDishOrder(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldChef */
   public void removeChef(Chef oldChef) {
      if (oldChef == null)
         return;
      if (this.chef != null)
         if (this.chef.contains(oldChef))
         {
            this.chef.remove(oldChef);
            //oldChef.removeDishOrder(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllChef() {
      if (chef != null)
      {
         Chef oldChef;
         for (java.util.Iterator iter = getIteratorChef(); iter.hasNext();)
         {
            oldChef = (Chef)iter.next();
            iter.remove();
            //oldChef.removeDishOrder(this);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Dish> getDishes() {
      if (dishes == null)
         dishes = new java.util.ArrayList<Dish>();
      return dishes;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDishes() {
      if (dishes == null)
         dishes = new java.util.ArrayList<Dish>();
      return dishes.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDishes */
   public void setDishes(java.util.Collection<Dish> newDishes) {
      removeAllDishes();
      for (java.util.Iterator iter = newDishes.iterator(); iter.hasNext();)
         addDishes((Dish)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDish */
   public void addDishes(Dish newDish) {
      if (newDish == null)
         return;
      if (this.dishes == null)
         this.dishes = new java.util.ArrayList<Dish>();
      if (!this.dishes.contains(newDish))
      {
         this.dishes.add(newDish);
         newDish.addDishOrders(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldDish */
   public void removeDishes(Dish oldDish) {
      if (oldDish == null)
         return;
      if (this.dishes != null)
         if (this.dishes.contains(oldDish))
         {
            this.dishes.remove(oldDish);
            oldDish.removeDishOrders(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDishes() {
      if (dishes != null)
      {
         Dish oldDish;
         for (java.util.Iterator iter = getIteratorDishes(); iter.hasNext();)
         {
            oldDish = (Dish)iter.next();
            iter.remove();
            oldDish.removeDishOrders(this);
         }
      }
   }

}
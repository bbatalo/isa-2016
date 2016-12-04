/***********************************************************************
 * Module:  Menu.java
 * Author:  Bojan
 * Purpose: Defines the Class Menu
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 382a24db-925b-44ae-8ca8-b3e0f909ea98 */
public class Menu {
   /** @pdOid f8d152ba-a5e3-4e9a-80a8-441c0ca6a8b0 */
   public long idMenu;
   
   /** @pdRoleInfo migr=no name=Dish assc=dishes coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Dish> dishes;
   /** @pdRoleInfo migr=no name=Restaurant assc=menu mult=0..1 side=A */
   public Restaurant restaurant;
   
   
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
         newDish.addMenu(this);      
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
            oldDish.removeMenu(this);
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
            oldDish.removeMenu(this);
         }
      }
   }

}
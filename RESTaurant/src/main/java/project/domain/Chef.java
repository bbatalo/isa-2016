/***********************************************************************
 * Module:  Chef.java
 * Author:  Bojan
 * Purpose: Defines the Class Chef
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid e6c43d1b-cf89-4497-922b-d031227ce5a1 */
public class Chef extends Employee {
   /** @pdOid 4c4736d0-bb58-4812-af7f-fdde5eda9cf4 */
   public java.lang.String type;
   
   /** @pdRoleInfo migr=no name=ChefRating assc=chefRatings coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<ChefRating> ratings;
   /** @pdRoleInfo migr=no name=Shift assc=chefsInShifts coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<Shift> shifts;
   /** @pdRoleInfo migr=no name=DishesOrder assc=dishPreparation coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<DishesOrder> dishOrder;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<ChefRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<ChefRating>();
      return ratings;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<ChefRating>();
      return ratings.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRatings */
   public void setRatings(java.util.Collection<ChefRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((ChefRating)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newChefRating */
   public void addRatings(ChefRating newChefRating) {
      if (newChefRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<ChefRating>();
      if (!this.ratings.contains(newChefRating))
      {
         this.ratings.add(newChefRating);
         newChefRating.setChef(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldChefRating */
   public void removeRatings(ChefRating oldChefRating) {
      if (oldChefRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldChefRating))
         {
            this.ratings.remove(oldChefRating);
            oldChefRating.setChef((Chef)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRatings() {
      if (ratings != null)
      {
         ChefRating oldChefRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldChefRating = (ChefRating)iter.next();
            iter.remove();
            oldChefRating.setChef((Chef)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Shift> getShifts() {
      if (shifts == null)
         shifts = new java.util.ArrayList<Shift>();
      return shifts;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorShifts() {
      if (shifts == null)
         shifts = new java.util.ArrayList<Shift>();
      return shifts.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newShifts */
   public void setShifts(java.util.Collection<Shift> newShifts) {
      removeAllShifts();
      for (java.util.Iterator iter = newShifts.iterator(); iter.hasNext();)
         addShifts((Shift)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newShift */
   public void addShifts(Shift newShift) {
      if (newShift == null)
         return;
      if (this.shifts == null)
         this.shifts = new java.util.ArrayList<Shift>();
      if (!this.shifts.contains(newShift))
      {
         this.shifts.add(newShift);
         newShift.addChefs(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldShift */
   public void removeShifts(Shift oldShift) {
      if (oldShift == null)
         return;
      if (this.shifts != null)
         if (this.shifts.contains(oldShift))
         {
            this.shifts.remove(oldShift);
            oldShift.removeChefs(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllShifts() {
      if (shifts != null)
      {
         Shift oldShift;
         for (java.util.Iterator iter = getIteratorShifts(); iter.hasNext();)
         {
            oldShift = (Shift)iter.next();
            iter.remove();
            oldShift.removeChefs(this);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<DishesOrder> getDishOrder() {
      if (dishOrder == null)
         dishOrder = new java.util.ArrayList<DishesOrder>();
      return dishOrder;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDishOrder() {
      if (dishOrder == null)
         dishOrder = new java.util.ArrayList<DishesOrder>();
      return dishOrder.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDishOrder */
   public void setDishOrder(java.util.Collection<DishesOrder> newDishOrder) {
      removeAllDishOrder();
      for (java.util.Iterator iter = newDishOrder.iterator(); iter.hasNext();)
         addDishOrder((DishesOrder)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDishesOrder */
   public void addDishOrder(DishesOrder newDishesOrder) {
      if (newDishesOrder == null)
         return;
      if (this.dishOrder == null)
         this.dishOrder = new java.util.ArrayList<DishesOrder>();
      if (!this.dishOrder.contains(newDishesOrder))
      {
         this.dishOrder.add(newDishesOrder);
         newDishesOrder.addChef(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldDishesOrder */
   public void removeDishOrder(DishesOrder oldDishesOrder) {
      if (oldDishesOrder == null)
         return;
      if (this.dishOrder != null)
         if (this.dishOrder.contains(oldDishesOrder))
         {
            this.dishOrder.remove(oldDishesOrder);
            oldDishesOrder.removeChef(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDishOrder() {
      if (dishOrder != null)
      {
         DishesOrder oldDishesOrder;
         for (java.util.Iterator iter = getIteratorDishOrder(); iter.hasNext();)
         {
            oldDishesOrder = (DishesOrder)iter.next();
            iter.remove();
            oldDishesOrder.removeChef(this);
         }
      }
   }

}
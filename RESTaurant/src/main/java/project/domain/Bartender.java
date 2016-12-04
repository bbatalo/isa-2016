/***********************************************************************
 * Module:  Bartender.java
 * Author:  Bojan
 * Purpose: Defines the Class Bartender
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 03c00f81-a9d8-4dd1-b30a-6dbbc3fb964f */
public class Bartender extends Employee {
   /** @pdOid c4205cb1-ca5a-46ed-8802-e259e037eae1 */
   public boolean cocktails;
   
   /** @pdRoleInfo migr=no name=DrinksOrder assc=drinksPreparation coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<DrinksOrder> drinkOrder;
   /** @pdRoleInfo migr=no name=BartenderRating assc=bartenderRatings coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<BartenderRating> ratings;
   /** @pdRoleInfo migr=no name=Shift assc=bartendersInShifts coll=java.util.Collection impl=java.util.HashSet mult=0..* side=A */
   public java.util.Collection<Shift> shifts;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<DrinksOrder> getDrinkOrder() {
      if (drinkOrder == null)
         drinkOrder = new java.util.ArrayList<DrinksOrder>();
      return drinkOrder;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrinkOrder() {
      if (drinkOrder == null)
         drinkOrder = new java.util.ArrayList<DrinksOrder>();
      return drinkOrder.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrinkOrder */
   public void setDrinkOrder(java.util.Collection<DrinksOrder> newDrinkOrder) {
      removeAllDrinkOrder();
      for (java.util.Iterator iter = newDrinkOrder.iterator(); iter.hasNext();)
         addDrinkOrder((DrinksOrder)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrinksOrder */
   public void addDrinkOrder(DrinksOrder newDrinksOrder) {
      if (newDrinksOrder == null)
         return;
      if (this.drinkOrder == null)
         this.drinkOrder = new java.util.ArrayList<DrinksOrder>();
      if (!this.drinkOrder.contains(newDrinksOrder))
      {
         this.drinkOrder.add(newDrinksOrder);
         newDrinksOrder.addBartender(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldDrinksOrder */
   public void removeDrinkOrder(DrinksOrder oldDrinksOrder) {
      if (oldDrinksOrder == null)
         return;
      if (this.drinkOrder != null)
         if (this.drinkOrder.contains(oldDrinksOrder))
         {
            this.drinkOrder.remove(oldDrinksOrder);
            oldDrinksOrder.removeBartender(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDrinkOrder() {
      if (drinkOrder != null)
      {
         DrinksOrder oldDrinksOrder;
         for (java.util.Iterator iter = getIteratorDrinkOrder(); iter.hasNext();)
         {
            oldDrinksOrder = (DrinksOrder)iter.next();
            iter.remove();
            oldDrinksOrder.removeBartender(this);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<BartenderRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<BartenderRating>();
      return ratings;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<BartenderRating>();
      return ratings.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRatings */
   public void setRatings(java.util.Collection<BartenderRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((BartenderRating)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newBartenderRating */
   public void addRatings(BartenderRating newBartenderRating) {
      if (newBartenderRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<BartenderRating>();
      if (!this.ratings.contains(newBartenderRating))
      {
         this.ratings.add(newBartenderRating);
         newBartenderRating.setBartender(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldBartenderRating */
   public void removeRatings(BartenderRating oldBartenderRating) {
      if (oldBartenderRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldBartenderRating))
         {
            this.ratings.remove(oldBartenderRating);
            oldBartenderRating.setBartender((Bartender)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRatings() {
      if (ratings != null)
      {
         BartenderRating oldBartenderRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldBartenderRating = (BartenderRating)iter.next();
            iter.remove();
            oldBartenderRating.setBartender((Bartender)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Shift> getShifts() {
      if (shifts == null)
         shifts = new java.util.HashSet<Shift>();
      return shifts;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorShifts() {
      if (shifts == null)
         shifts = new java.util.HashSet<Shift>();
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
         this.shifts = new java.util.HashSet<Shift>();
      if (!this.shifts.contains(newShift))
      {
         this.shifts.add(newShift);
         newShift.addBartenders(this);      
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
            oldShift.removeBartenders(this);
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
            oldShift.removeBartenders(this);
         }
      }
   }

}
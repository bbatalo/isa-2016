/***********************************************************************
 * Module:  DrinksOrder.java
 * Author:  Bojan
 * Purpose: Defines the Class DrinksOrder
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid ecc9da90-7691-4122-80c3-605cb62aceb5 */
public class DrinksOrder {
   /** @pdOid 17ebb03d-94b4-40cf-86be-7ef0e67274d9 */
   public long idDrinksOrder;
   /** @pdOid eeaa6501-5ccc-4df3-823b-f1aebea7a5ac */
   public java.lang.String status;
   
   /** @pdRoleInfo migr=no name=Drink assc=drinksInAnOrder coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Drink> drinks;
   /** @pdRoleInfo migr=no name=Bartender assc=drinksPreparation coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<Bartender> bartender;
   /** @pdRoleInfo migr=no name=Customer assc=customerDrink mult=0..1 side=A */
   public Customer customer;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Drink> getDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<Drink>();
      return drinks;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<Drink>();
      return drinks.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrinks */
   public void setDrinks(java.util.Collection<Drink> newDrinks) {
      removeAllDrinks();
      for (java.util.Iterator iter = newDrinks.iterator(); iter.hasNext();)
         addDrinks((Drink)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrink */
   public void addDrinks(Drink newDrink) {
      if (newDrink == null)
         return;
      if (this.drinks == null)
         this.drinks = new java.util.ArrayList<Drink>();
      if (!this.drinks.contains(newDrink))
      {
         this.drinks.add(newDrink);
         newDrink.addDrinkOrders(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldDrink */
   public void removeDrinks(Drink oldDrink) {
      if (oldDrink == null)
         return;
      if (this.drinks != null)
         if (this.drinks.contains(oldDrink))
         {
            this.drinks.remove(oldDrink);
            oldDrink.removeDrinkOrders(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDrinks() {
      if (drinks != null)
      {
         Drink oldDrink;
         for (java.util.Iterator iter = getIteratorDrinks(); iter.hasNext();)
         {
            oldDrink = (Drink)iter.next();
            iter.remove();
            oldDrink.removeDrinkOrders(this);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Bartender> getBartender() {
      if (bartender == null)
         bartender = new java.util.ArrayList<Bartender>();
      return bartender;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorBartender() {
      if (bartender == null)
         bartender = new java.util.ArrayList<Bartender>();
      return bartender.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newBartender */
   public void setBartender(java.util.Collection<Bartender> newBartender) {
      removeAllBartender();
      for (java.util.Iterator iter = newBartender.iterator(); iter.hasNext();)
         addBartender((Bartender)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newBartender */
   public void addBartender(Bartender newBartender) {
      if (newBartender == null)
         return;
      if (this.bartender == null)
         this.bartender = new java.util.ArrayList<Bartender>();
      if (!this.bartender.contains(newBartender))
      {
         this.bartender.add(newBartender);
         //newBartender.addDrinkOrder(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldBartender */
   public void removeBartender(Bartender oldBartender) {
      if (oldBartender == null)
         return;
      if (this.bartender != null)
         if (this.bartender.contains(oldBartender))
         {
            this.bartender.remove(oldBartender);
            //oldBartender.removeDrinkOrder(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllBartender() {
      if (bartender != null)
      {
         Bartender oldBartender;
         for (java.util.Iterator iter = getIteratorBartender(); iter.hasNext();)
         {
            oldBartender = (Bartender)iter.next();
            iter.remove();
            //oldBartender.removeDrinkOrder(this);
         }
      }
   }

}
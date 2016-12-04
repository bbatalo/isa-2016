/***********************************************************************
 * Module:  Bid.java
 * Author:  Bojan
 * Purpose: Defines the Class Bid
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 726df00b-997b-4286-af75-3d20d44e1a9b */
public class Bid {
   /** @pdOid 5ef43ac1-4049-41e7-a1e1-c458649ac723 */
   public long idBid;
   /** @pdOid dcf096bf-15d7-4563-b578-e4a5f6f21003 */
   public java.util.Date beginning;
   /** @pdOid b4275181-892c-49bb-8dcf-d6e3a56da2a1 */
   public java.util.Date end;
   
   /** @pdRoleInfo migr=no name=Drink assc=requiredDrinks coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Drink> drinks;
   /** @pdRoleInfo migr=no name=Grocery assc=requiredGroceries coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Grocery> groceries;
   /** @pdRoleInfo migr=no name=RestorauntManager assc=bids mult=0..1 side=A */
   public RestorauntManager manager;
   /** @pdRoleInfo migr=no name=Offer assc=offersForBids coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<Offer> offers;
   
   
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
         this.drinks.add(newDrink);
   }
   
   /** @pdGenerated default remove
     * @param oldDrink */
   public void removeDrinks(Drink oldDrink) {
      if (oldDrink == null)
         return;
      if (this.drinks != null)
         if (this.drinks.contains(oldDrink))
            this.drinks.remove(oldDrink);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDrinks() {
      if (drinks != null)
         drinks.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Grocery> getGroceries() {
      if (groceries == null)
         groceries = new java.util.ArrayList<Grocery>();
      return groceries;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorGroceries() {
      if (groceries == null)
         groceries = new java.util.ArrayList<Grocery>();
      return groceries.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newGroceries */
   public void setGroceries(java.util.Collection<Grocery> newGroceries) {
      removeAllGroceries();
      for (java.util.Iterator iter = newGroceries.iterator(); iter.hasNext();)
         addGroceries((Grocery)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newGrocery */
   public void addGroceries(Grocery newGrocery) {
      if (newGrocery == null)
         return;
      if (this.groceries == null)
         this.groceries = new java.util.ArrayList<Grocery>();
      if (!this.groceries.contains(newGrocery))
         this.groceries.add(newGrocery);
   }
   
   /** @pdGenerated default remove
     * @param oldGrocery */
   public void removeGroceries(Grocery oldGrocery) {
      if (oldGrocery == null)
         return;
      if (this.groceries != null)
         if (this.groceries.contains(oldGrocery))
            this.groceries.remove(oldGrocery);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllGroceries() {
      if (groceries != null)
         groceries.clear();
   }
   /** @pdGenerated default parent getter */
   public RestorauntManager getManager() {
      return manager;
   }
   
   /** @pdGenerated default parent setter
     * @param newRestorauntManager */
   public void setManager(RestorauntManager newRestorauntManager) {
      if (this.manager == null || !this.manager.equals(newRestorauntManager))
      {
         if (this.manager != null)
         {
            RestorauntManager oldRestorauntManager = this.manager;
            this.manager = null;
            oldRestorauntManager.removeBids(this);
         }
         if (newRestorauntManager != null)
         {
            this.manager = newRestorauntManager;
            this.manager.addBids(this);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Offer> getOffers() {
      if (offers == null)
         offers = new java.util.ArrayList<Offer>();
      return offers;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorOffers() {
      if (offers == null)
         offers = new java.util.ArrayList<Offer>();
      return offers.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newOffers */
   public void setOffers(java.util.Collection<Offer> newOffers) {
      removeAllOffers();
      for (java.util.Iterator iter = newOffers.iterator(); iter.hasNext();)
         addOffers((Offer)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newOffer */
   public void addOffers(Offer newOffer) {
      if (newOffer == null)
         return;
      if (this.offers == null)
         this.offers = new java.util.ArrayList<Offer>();
      if (!this.offers.contains(newOffer))
      {
         this.offers.add(newOffer);
         newOffer.addBids(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldOffer */
   public void removeOffers(Offer oldOffer) {
      if (oldOffer == null)
         return;
      if (this.offers != null)
         if (this.offers.contains(oldOffer))
         {
            this.offers.remove(oldOffer);
            oldOffer.removeBids(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOffers() {
      if (offers != null)
      {
         Offer oldOffer;
         for (java.util.Iterator iter = getIteratorOffers(); iter.hasNext();)
         {
            oldOffer = (Offer)iter.next();
            iter.remove();
            oldOffer.removeBids(this);
         }
      }
   }

}
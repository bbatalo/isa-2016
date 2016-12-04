/***********************************************************************
 * Module:  Offer.java
 * Author:  Bojan
 * Purpose: Defines the Class Offer
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 161f585d-ab32-40d9-b65a-21f4d54ebe78 */
public class Offer {
   /** @pdOid 62d75a26-4e54-4eb8-bf60-23b6f5a4de90 */
   public long idOffer;
   /** @pdOid cd1cfd17-d9a8-4c56-a22f-b3067ab7c839 */
   public java.util.Date delivery;
   /** @pdOid b53c5300-4a49-4330-a3a3-7916225cb4c0 */
   public java.util.Date warranty;
   /** @pdOid dc7ee83c-873e-41f5-90e8-b89c6ddc7795 */
   public java.util.Date lastsUntil;
   
   /** @pdRoleInfo migr=no name=Bid assc=offersForBids coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Bid> bids;
   /** @pdRoleInfo migr=no name=DrinkOffer assc=drinkOffer coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<DrinkOffer> drinkOffers;
   /** @pdRoleInfo migr=no name=GroceryOffer assc=groceryOffer coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<GroceryOffer> groceryOffers;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Bid> getBids() {
      if (bids == null)
         bids = new java.util.ArrayList<Bid>();
      return bids;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorBids() {
      if (bids == null)
         bids = new java.util.ArrayList<Bid>();
      return bids.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newBids */
   public void setBids(java.util.Collection<Bid> newBids) {
      removeAllBids();
      for (java.util.Iterator iter = newBids.iterator(); iter.hasNext();)
         addBids((Bid)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newBid */
   public void addBids(Bid newBid) {
      if (newBid == null)
         return;
      if (this.bids == null)
         this.bids = new java.util.ArrayList<Bid>();
      if (!this.bids.contains(newBid))
      {
         this.bids.add(newBid);
         newBid.addOffers(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldBid */
   public void removeBids(Bid oldBid) {
      if (oldBid == null)
         return;
      if (this.bids != null)
         if (this.bids.contains(oldBid))
         {
            this.bids.remove(oldBid);
            oldBid.removeOffers(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllBids() {
      if (bids != null)
      {
         Bid oldBid;
         for (java.util.Iterator iter = getIteratorBids(); iter.hasNext();)
         {
            oldBid = (Bid)iter.next();
            iter.remove();
            oldBid.removeOffers(this);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<DrinkOffer> getDrinkOffers() {
      if (drinkOffers == null)
         drinkOffers = new java.util.ArrayList<DrinkOffer>();
      return drinkOffers;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrinkOffers() {
      if (drinkOffers == null)
         drinkOffers = new java.util.ArrayList<DrinkOffer>();
      return drinkOffers.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrinkOffers */
   public void setDrinkOffers(java.util.Collection<DrinkOffer> newDrinkOffers) {
      removeAllDrinkOffers();
      for (java.util.Iterator iter = newDrinkOffers.iterator(); iter.hasNext();)
         addDrinkOffers((DrinkOffer)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrinkOffer */
   public void addDrinkOffers(DrinkOffer newDrinkOffer) {
      if (newDrinkOffer == null)
         return;
      if (this.drinkOffers == null)
         this.drinkOffers = new java.util.ArrayList<DrinkOffer>();
      if (!this.drinkOffers.contains(newDrinkOffer))
      {
         this.drinkOffers.add(newDrinkOffer);
         newDrinkOffer.setOffer(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldDrinkOffer */
   public void removeDrinkOffers(DrinkOffer oldDrinkOffer) {
      if (oldDrinkOffer == null)
         return;
      if (this.drinkOffers != null)
         if (this.drinkOffers.contains(oldDrinkOffer))
         {
            this.drinkOffers.remove(oldDrinkOffer);
            oldDrinkOffer.setOffer((Offer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDrinkOffers() {
      if (drinkOffers != null)
      {
         DrinkOffer oldDrinkOffer;
         for (java.util.Iterator iter = getIteratorDrinkOffers(); iter.hasNext();)
         {
            oldDrinkOffer = (DrinkOffer)iter.next();
            iter.remove();
            oldDrinkOffer.setOffer((Offer)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<GroceryOffer> getGroceryOffers() {
      if (groceryOffers == null)
         groceryOffers = new java.util.ArrayList<GroceryOffer>();
      return groceryOffers;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorGroceryOffers() {
      if (groceryOffers == null)
         groceryOffers = new java.util.ArrayList<GroceryOffer>();
      return groceryOffers.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newGroceryOffers */
   public void setGroceryOffers(java.util.Collection<GroceryOffer> newGroceryOffers) {
      removeAllGroceryOffers();
      for (java.util.Iterator iter = newGroceryOffers.iterator(); iter.hasNext();)
         addGroceryOffers((GroceryOffer)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newGroceryOffer */
   public void addGroceryOffers(GroceryOffer newGroceryOffer) {
      if (newGroceryOffer == null)
         return;
      if (this.groceryOffers == null)
         this.groceryOffers = new java.util.ArrayList<GroceryOffer>();
      if (!this.groceryOffers.contains(newGroceryOffer))
      {
         this.groceryOffers.add(newGroceryOffer);
         newGroceryOffer.setOffer(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldGroceryOffer */
   public void removeGroceryOffers(GroceryOffer oldGroceryOffer) {
      if (oldGroceryOffer == null)
         return;
      if (this.groceryOffers != null)
         if (this.groceryOffers.contains(oldGroceryOffer))
         {
            this.groceryOffers.remove(oldGroceryOffer);
            oldGroceryOffer.setOffer((Offer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllGroceryOffers() {
      if (groceryOffers != null)
      {
         GroceryOffer oldGroceryOffer;
         for (java.util.Iterator iter = getIteratorGroceryOffers(); iter.hasNext();)
         {
            oldGroceryOffer = (GroceryOffer)iter.next();
            iter.remove();
            oldGroceryOffer.setOffer((Offer)null);
         }
      }
   }

}
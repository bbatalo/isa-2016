/***********************************************************************
 * Module:  RestorauntManager.java
 * Author:  Bojan
 * Purpose: Defines the Class RestorauntManager
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 912d45e1-a228-46e4-80f8-8541ab35801b */
public class RestorauntManager extends User {
   /** @pdOid ddd6760b-6728-42f9-943f-7ce63cd68275 */
   public java.lang.String name;
   /** @pdOid 06f214a0-d9ee-4cc0-8762-cc6779cf96f0 */
   public java.lang.String surname;
   
   /** @pdRoleInfo migr=no name=Restaurant assc=management mult=0..1 */
   public Restaurant restoraunt;
   /** @pdRoleInfo migr=no name=Bid assc=bids coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Bid> bids;
   /** @pdRoleInfo migr=no name=Supplier assc=suppliers coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Supplier> managers;
   
   
   /** @pdGenerated default parent getter */
   public Restaurant getRestoraunt() {
      return restoraunt;
   }
   
   /** @pdGenerated default parent setter
     * @param newRestaurant */
   public void setRestoraunt(Restaurant newRestaurant) {
      if (this.restoraunt == null || !this.restoraunt.equals(newRestaurant))
      {
         if (this.restoraunt != null)
         {
            Restaurant oldRestaurant = this.restoraunt;
            this.restoraunt = null;
            oldRestaurant.removeManagers(this);
         }
         if (newRestaurant != null)
         {
            this.restoraunt = newRestaurant;
            this.restoraunt.addManagers(this);
         }
      }
   }
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
         newBid.setManager(this);      
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
            oldBid.setManager((RestorauntManager)null);
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
            oldBid.setManager((RestorauntManager)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Supplier> getManagers() {
      if (managers == null)
         managers = new java.util.ArrayList<Supplier>();
      return managers;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorManagers() {
      if (managers == null)
         managers = new java.util.ArrayList<Supplier>();
      return managers.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newManagers */
   public void setManagers(java.util.Collection<Supplier> newManagers) {
      removeAllManagers();
      for (java.util.Iterator iter = newManagers.iterator(); iter.hasNext();)
         addManagers((Supplier)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newSupplier */
   public void addManagers(Supplier newSupplier) {
      if (newSupplier == null)
         return;
      if (this.managers == null)
         this.managers = new java.util.ArrayList<Supplier>();
      if (!this.managers.contains(newSupplier))
      {
         this.managers.add(newSupplier);
         newSupplier.addSuppliers(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldSupplier */
   public void removeManagers(Supplier oldSupplier) {
      if (oldSupplier == null)
         return;
      if (this.managers != null)
         if (this.managers.contains(oldSupplier))
         {
            this.managers.remove(oldSupplier);
            oldSupplier.removeSuppliers(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllManagers() {
      if (managers != null)
      {
         Supplier oldSupplier;
         for (java.util.Iterator iter = getIteratorManagers(); iter.hasNext();)
         {
            oldSupplier = (Supplier)iter.next();
            iter.remove();
            oldSupplier.removeSuppliers(this);
         }
      }
   }

}
/***********************************************************************
 * Module:  Supplier.java
 * Author:  Bojan
 * Purpose: Defines the Class Supplier
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 5e220776-2771-439b-b65a-163f58c4069a */
public class Supplier extends User {
   /** @pdOid 96acf68b-b510-4caa-8cde-cb1d3a9a7325 */
   public java.lang.String label;
   /** @pdOid 7d671230-2e02-4022-827c-81c457999caf */
   public java.lang.String description;
   /** @pdOid 87f2bdeb-1557-4ae9-b073-acbf67d9cafe */
   public boolean passChanged;
   
   /** @pdRoleInfo migr=no name=Offer assc=offers coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Offer> offers;
   /** @pdRoleInfo migr=no name=RestorauntManager assc=suppliers coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<RestorauntManager> suppliers;
   
   
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
         this.offers.add(newOffer);
   }
   
   /** @pdGenerated default remove
     * @param oldOffer */
   public void removeOffers(Offer oldOffer) {
      if (oldOffer == null)
         return;
      if (this.offers != null)
         if (this.offers.contains(oldOffer))
            this.offers.remove(oldOffer);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOffers() {
      if (offers != null)
         offers.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<RestorauntManager> getSuppliers() {
      if (suppliers == null)
         suppliers = new java.util.ArrayList<RestorauntManager>();
      return suppliers;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSuppliers() {
      if (suppliers == null)
         suppliers = new java.util.ArrayList<RestorauntManager>();
      return suppliers.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSuppliers */
   public void setSuppliers(java.util.Collection<RestorauntManager> newSuppliers) {
      removeAllSuppliers();
      for (java.util.Iterator iter = newSuppliers.iterator(); iter.hasNext();)
         addSuppliers((RestorauntManager)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRestorauntManager */
   public void addSuppliers(RestorauntManager newRestorauntManager) {
      if (newRestorauntManager == null)
         return;
      if (this.suppliers == null)
         this.suppliers = new java.util.ArrayList<RestorauntManager>();
      if (!this.suppliers.contains(newRestorauntManager))
      {
         this.suppliers.add(newRestorauntManager);
         newRestorauntManager.addManagers(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldRestorauntManager */
   public void removeSuppliers(RestorauntManager oldRestorauntManager) {
      if (oldRestorauntManager == null)
         return;
      if (this.suppliers != null)
         if (this.suppliers.contains(oldRestorauntManager))
         {
            this.suppliers.remove(oldRestorauntManager);
            oldRestorauntManager.removeManagers(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSuppliers() {
      if (suppliers != null)
      {
         RestorauntManager oldRestorauntManager;
         for (java.util.Iterator iter = getIteratorSuppliers(); iter.hasNext();)
         {
            oldRestorauntManager = (RestorauntManager)iter.next();
            iter.remove();
            oldRestorauntManager.removeManagers(this);
         }
      }
   }

}
/***********************************************************************
 * Module:  GroceryOffer.java
 * Author:  Bojan
 * Purpose: Defines the Class GroceryOffer
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 102b2278-4ea1-4513-8a9b-d80ed6a23687 */
public class GroceryOffer {
   /** @pdOid b53cde6b-ddff-4a58-b993-0262510746c5 */
   public long idGroceryOffer;
   /** @pdOid 35bf8298-3a7a-4129-98cb-2824b387a35f */
   public float price;
   
   /** @pdRoleInfo migr=no name=Offer assc=groceryOffer mult=0..1 side=A */
   public Offer offer;
   /** @pdRoleInfo migr=no name=Grocery assc=offeredGrocery mult=0..1 side=A */
   public Grocery grocery;
   
   
   /** @pdGenerated default parent getter */
   public Offer getOffer() {
      return offer;
   }
   /*
   public void setOffer(Offer newOffer) {
      if (this.offer == null || !this.offer.equals(newOffer))
      {
         if (this.offer != null)
         {
            Offer oldOffer = this.offer;
            this.offer = null;
            oldOffer.removeGroceryOffers(this);
         }
         if (newOffer != null)
         {
            this.offer = newOffer;
            this.offer.addGroceryOffers(this);
         }
      }
   }
	*/
}
/***********************************************************************
 * Module:  DrinkOffer.java
 * Author:  Bojan
 * Purpose: Defines the Class DrinkOffer
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid d4d7f798-1852-47f4-b0c2-e9f51abac097 */
public class DrinkOffer {
   /** @pdOid 098deb75-04ce-4ce3-9f0d-a875bc7cadf2 */
   public long idDrinkOffer;
   /** @pdOid 7e63d9db-cb38-456f-8f33-014751c1e050 */
   public float price;
   
   /** @pdRoleInfo migr=no name=Offer assc=drinkOffer mult=0..1 side=A */
   public Offer offer;
   /** @pdRoleInfo migr=no name=Drink assc=offeredDrink mult=0..1 side=A */
   public Drink drink;
   
   
   /** @pdGenerated default parent getter */
   public Offer getOffer() {
      return offer;
   }
   
   /** @pdGenerated default parent setter
     * @param newOffer */
   public void setOffer(Offer newOffer) {
      if (this.offer == null || !this.offer.equals(newOffer))
      {
         if (this.offer != null)
         {
            Offer oldOffer = this.offer;
            this.offer = null;
            oldOffer.removeDrinkOffers(this);
         }
         if (newOffer != null)
         {
            this.offer = newOffer;
            this.offer.addDrinkOffers(this);
         }
      }
   }

}
/***********************************************************************
 * Module:  BartenderRating.java
 * Author:  Bojan
 * Purpose: Defines the Class BartenderRating
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 4aa68bfa-3538-4b24-8d6b-aa87a5913552 */
public class BartenderRating {
   /** @pdOid 43267e4e-a13e-4ef0-8535-a71fa60f4493 */
   public long idBartenderRating;
   /** @pdOid e5ac9428-9218-4500-8f11-7658657b1a25 */
   public short rating;
   
   /** @pdRoleInfo migr=no name=ServiceRating assc=bartenderRating mult=0..1 side=A */
   public ServiceRating serviceRating;
   /** @pdRoleInfo migr=no name=Bartender assc=bartenderRatings mult=0..1 side=A */
   public Bartender bartender;
   
   
   /** @pdGenerated default parent getter */
   public Bartender getBartender() {
      return bartender;
   }
   
   /** @pdGenerated default parent setter
     * @param newBartender */
   public void setBartender(Bartender newBartender) {
      if (this.bartender == null || !this.bartender.equals(newBartender))
      {
         if (this.bartender != null)
         {
            Bartender oldBartender = this.bartender;
            this.bartender = null;
            oldBartender.removeRatings(this);
         }
         if (newBartender != null)
         {
            this.bartender = newBartender;
            this.bartender.addRatings(this);
         }
      }
   }

}
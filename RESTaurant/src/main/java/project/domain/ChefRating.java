/***********************************************************************
 * Module:  ChefRating.java
 * Author:  Bojan
 * Purpose: Defines the Class ChefRating
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 12aea99d-e037-434f-bcc3-a3fabdcb9fb4 */
public class ChefRating {
   /** @pdOid eea635d2-8307-4596-b324-6e5b71966418 */
   public long idChefRating;
   /** @pdOid ac55f277-13f1-45c5-803d-2a26de9be7b4 */
   public short rating;
   
   /** @pdRoleInfo migr=no name=ServiceRating assc=chefRating mult=0..1 side=A */
   public ServiceRating serviceRating;
   /** @pdRoleInfo migr=no name=Chef assc=chefRatings mult=0..1 side=A */
   public Chef chef;
   
   
   /** @pdGenerated default parent getter */
   public ServiceRating getServiceRating() {
      return serviceRating;
   }
   
   /** @pdGenerated default parent setter
     * @param newServiceRating */
   public void setServiceRating(ServiceRating newServiceRating) {
      if (this.serviceRating == null || !this.serviceRating.equals(newServiceRating))
      {
         if (this.serviceRating != null)
         {
            ServiceRating oldServiceRating = this.serviceRating;
            this.serviceRating = null;
            oldServiceRating.removeChefRatings(this);
         }
         if (newServiceRating != null)
         {
            this.serviceRating = newServiceRating;
            this.serviceRating.addChefRatings(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Chef getChef() {
      return chef;
   }
   
   /** @pdGenerated default parent setter
     * @param newChef */
   public void setChef(Chef newChef) {
      if (this.chef == null || !this.chef.equals(newChef))
      {
         if (this.chef != null)
         {
            Chef oldChef = this.chef;
            this.chef = null;
            //oldChef.removeRatings(this);
         }
         if (newChef != null)
         {
            this.chef = newChef;
            //this.chef.addRatings(this);
         }
      }
   }

}
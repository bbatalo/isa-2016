/***********************************************************************
 * Module:  WaiterRating.java
 * Author:  Bojan
 * Purpose: Defines the Class WaiterRating
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 4f00e99c-4aad-4c02-9bd9-b33e71457518 */
public class WaiterRating {
   /** @pdOid 26fb2e1f-f033-4113-97cc-1405bd530da2 */
   public long idWaiterRating;
   /** @pdOid 553b3e82-7a4c-4ad5-97d6-ae9c286484b2 */
   public short rating;
   
   /** @pdRoleInfo migr=no name=ServiceRating assc=waiterRating mult=0..1 side=A */
   public ServiceRating serviceRating;
   /** @pdRoleInfo migr=no name=WaiterSwitch assc=switchedWaitersRating mult=0..1 side=A */
   public WaiterSwitch waiterSwitch;
   /** @pdRoleInfo migr=no name=Waiter assc=waiterRatings mult=0..1 side=A */
   public Waiter waiter;
   
   
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
            oldServiceRating.removeWaiterRatings(this);
         }
         if (newServiceRating != null)
         {
            this.serviceRating = newServiceRating;
            this.serviceRating.addWaiterRatings(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Waiter getWaiter() {
      return waiter;
   }
   
   /** @pdGenerated default parent setter
     * @param newWaiter */
   public void setWaiter(Waiter newWaiter) {
      if (this.waiter == null || !this.waiter.equals(newWaiter))
      {
         if (this.waiter != null)
         {
            Waiter oldWaiter = this.waiter;
            this.waiter = null;
            //oldWaiter.removeRatings(this);
         }
         if (newWaiter != null)
         {
            this.waiter = newWaiter;
            //this.waiter.addRatings(this);
         }
      }
   }

}
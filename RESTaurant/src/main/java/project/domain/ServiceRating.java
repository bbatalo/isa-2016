/***********************************************************************
 * Module:  ServiceRating.java
 * Author:  Bojan
 * Purpose: Defines the Class ServiceRating
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 9fb8d926-d548-4cc7-88c2-292e572379b7 */
public class ServiceRating {
   /** @pdOid ebb53dd9-d008-48ce-9dd8-fd2d76732dda */
   public long idServiceRating;
   
   /** @pdRoleInfo migr=no name=WaiterRating assc=waiterRating coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<WaiterRating> waiterRatings;
   /** @pdRoleInfo migr=no name=BartenderRating assc=bartenderRating mult=0..1 */
   public BartenderRating bartenderRating;
   /** @pdRoleInfo migr=no name=ChefRating assc=chefRating coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<ChefRating> chefRatings;
   /** @pdRoleInfo migr=no name=Rating assc=serviceRating mult=0..1 side=A */
   public Rating rating;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<WaiterRating> getWaiterRatings() {
      if (waiterRatings == null)
         waiterRatings = new java.util.ArrayList<WaiterRating>();
      return waiterRatings;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorWaiterRatings() {
      if (waiterRatings == null)
         waiterRatings = new java.util.ArrayList<WaiterRating>();
      return waiterRatings.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newWaiterRatings */
   public void setWaiterRatings(java.util.Collection<WaiterRating> newWaiterRatings) {
      removeAllWaiterRatings();
      for (java.util.Iterator iter = newWaiterRatings.iterator(); iter.hasNext();)
         addWaiterRatings((WaiterRating)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newWaiterRating */
   public void addWaiterRatings(WaiterRating newWaiterRating) {
      if (newWaiterRating == null)
         return;
      if (this.waiterRatings == null)
         this.waiterRatings = new java.util.ArrayList<WaiterRating>();
      if (!this.waiterRatings.contains(newWaiterRating))
      {
         this.waiterRatings.add(newWaiterRating);
         newWaiterRating.setServiceRating(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldWaiterRating */
   public void removeWaiterRatings(WaiterRating oldWaiterRating) {
      if (oldWaiterRating == null)
         return;
      if (this.waiterRatings != null)
         if (this.waiterRatings.contains(oldWaiterRating))
         {
            this.waiterRatings.remove(oldWaiterRating);
            oldWaiterRating.setServiceRating((ServiceRating)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllWaiterRatings() {
      if (waiterRatings != null)
      {
         WaiterRating oldWaiterRating;
         for (java.util.Iterator iter = getIteratorWaiterRatings(); iter.hasNext();)
         {
            oldWaiterRating = (WaiterRating)iter.next();
            iter.remove();
            oldWaiterRating.setServiceRating((ServiceRating)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<ChefRating> getChefRatings() {
      if (chefRatings == null)
         chefRatings = new java.util.ArrayList<ChefRating>();
      return chefRatings;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorChefRatings() {
      if (chefRatings == null)
         chefRatings = new java.util.ArrayList<ChefRating>();
      return chefRatings.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newChefRatings */
   public void setChefRatings(java.util.Collection<ChefRating> newChefRatings) {
      removeAllChefRatings();
      for (java.util.Iterator iter = newChefRatings.iterator(); iter.hasNext();)
         addChefRatings((ChefRating)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newChefRating */
   public void addChefRatings(ChefRating newChefRating) {
      if (newChefRating == null)
         return;
      if (this.chefRatings == null)
         this.chefRatings = new java.util.ArrayList<ChefRating>();
      if (!this.chefRatings.contains(newChefRating))
      {
         this.chefRatings.add(newChefRating);
         newChefRating.setServiceRating(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldChefRating */
   public void removeChefRatings(ChefRating oldChefRating) {
      if (oldChefRating == null)
         return;
      if (this.chefRatings != null)
         if (this.chefRatings.contains(oldChefRating))
         {
            this.chefRatings.remove(oldChefRating);
            oldChefRating.setServiceRating((ServiceRating)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllChefRatings() {
      if (chefRatings != null)
      {
         ChefRating oldChefRating;
         for (java.util.Iterator iter = getIteratorChefRatings(); iter.hasNext();)
         {
            oldChefRating = (ChefRating)iter.next();
            iter.remove();
            oldChefRating.setServiceRating((ServiceRating)null);
         }
      }
   }

}
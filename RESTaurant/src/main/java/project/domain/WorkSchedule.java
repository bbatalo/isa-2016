/***********************************************************************
 * Module:  WorkSchedule.java
 * Author:  Bojan
 * Purpose: Defines the Class WorkSchedule
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid ab07d595-9600-4147-8b65-08471169cb3e */
public class WorkSchedule {
   /** @pdOid 920d09c7-0afb-4899-9bdc-89232bffa194 */
   public long idSchedule;
   /** @pdOid 93ce4c35-e7c6-4485-9a7d-5befde50b96b */
   public java.util.Date scBeginning;
   /** @pdOid 7f20f12e-0456-4401-bf2d-a36b4ceaee25 */
   public java.util.Date scEnd;
   
   /** @pdRoleInfo migr=no name=DailySchedule assc=dnevniRaspored coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<DailySchedule> dailySchedule;
   /** @pdRoleInfo migr=no name=Restaurant assc=schedule mult=0..1 side=A */
   public Restaurant restaurant;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<DailySchedule> getDailySchedule() {
      if (dailySchedule == null)
         dailySchedule = new java.util.ArrayList<DailySchedule>();
      return dailySchedule;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDailySchedule() {
      if (dailySchedule == null)
         dailySchedule = new java.util.ArrayList<DailySchedule>();
      return dailySchedule.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDailySchedule */
   public void setDailySchedule(java.util.Collection<DailySchedule> newDailySchedule) {
      removeAllDailySchedule();
      for (java.util.Iterator iter = newDailySchedule.iterator(); iter.hasNext();)
         addDailySchedule((DailySchedule)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDailySchedule */
   public void addDailySchedule(DailySchedule newDailySchedule) {
      if (newDailySchedule == null)
         return;
      if (this.dailySchedule == null)
         this.dailySchedule = new java.util.ArrayList<DailySchedule>();
      if (!this.dailySchedule.contains(newDailySchedule))
      {
         this.dailySchedule.add(newDailySchedule);
         newDailySchedule.setSchedule(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldDailySchedule */
   public void removeDailySchedule(DailySchedule oldDailySchedule) {
      if (oldDailySchedule == null)
         return;
      if (this.dailySchedule != null)
         if (this.dailySchedule.contains(oldDailySchedule))
         {
            this.dailySchedule.remove(oldDailySchedule);
            oldDailySchedule.setSchedule((WorkSchedule)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDailySchedule() {
      if (dailySchedule != null)
      {
         DailySchedule oldDailySchedule;
         for (java.util.Iterator iter = getIteratorDailySchedule(); iter.hasNext();)
         {
            oldDailySchedule = (DailySchedule)iter.next();
            iter.remove();
            oldDailySchedule.setSchedule((WorkSchedule)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Restaurant getRestaurant() {
      return restaurant;
   }
   
   /** @pdGenerated default parent setter
     * @param newRestaurant */
   public void setRestaurant(Restaurant newRestaurant) {
      if (this.restaurant == null || !this.restaurant.equals(newRestaurant))
      {
         if (this.restaurant != null)
         {
            Restaurant oldRestaurant = this.restaurant;
            this.restaurant = null;
            //oldRestaurant.removeSchedule(this);
         }
         if (newRestaurant != null)
         {
            this.restaurant = newRestaurant;
            //this.restaurant.addSchedule(this);
         }
      }
   }

}
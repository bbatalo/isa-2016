/***********************************************************************
 * Module:  Shift.java
 * Author:  Bojan
 * Purpose: Defines the Class Shift
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 655df44c-0c33-424d-b6cf-04937070f99b */
public class Shift {
   /** @pdOid a4d22278-1ae6-41cf-b9a5-6cc6f8fe0043 */
   public long idShift;
   /** @pdOid 89dcf4b7-89c7-4f7b-8032-e75a59d578f1 */
   public java.util.Date sBeginning;
   /** @pdOid 03237cd9-1db9-4298-9395-92def075ee62 */
   public java.util.Date sEnd;
   
   /** @pdRoleInfo migr=no name=Bartender assc=bartendersInShifts coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Bartender> bartenders;
   /** @pdRoleInfo migr=no name=Chef assc=chefsInShifts coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Chef> chefs;
   /** @pdRoleInfo migr=no name=DailySchedule assc=shifts mult=0..1 side=A */
   public DailySchedule dailySchedule;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Bartender> getBartenders() {
      if (bartenders == null)
         bartenders = new java.util.ArrayList<Bartender>();
      return bartenders;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorBartenders() {
      if (bartenders == null)
         bartenders = new java.util.ArrayList<Bartender>();
      return bartenders.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newBartenders */
   public void setBartenders(java.util.Collection<Bartender> newBartenders) {
      removeAllBartenders();
      for (java.util.Iterator iter = newBartenders.iterator(); iter.hasNext();)
         addBartenders((Bartender)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newBartender */
   public void addBartenders(Bartender newBartender) {
      if (newBartender == null)
         return;
      if (this.bartenders == null)
         this.bartenders = new java.util.ArrayList<Bartender>();
      if (!this.bartenders.contains(newBartender))
      {
         this.bartenders.add(newBartender);
         //newBartender.addShifts(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldBartender */
   public void removeBartenders(Bartender oldBartender) {
      if (oldBartender == null)
         return;
      if (this.bartenders != null)
         if (this.bartenders.contains(oldBartender))
         {
            this.bartenders.remove(oldBartender);
            //oldBartender.removeShifts(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllBartenders() {
      if (bartenders != null)
      {
         Bartender oldBartender;
         for (java.util.Iterator iter = getIteratorBartenders(); iter.hasNext();)
         {
            oldBartender = (Bartender)iter.next();
            iter.remove();
            //oldBartender.removeShifts(this);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Chef> getChefs() {
      if (chefs == null)
         chefs = new java.util.ArrayList<Chef>();
      return chefs;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorChefs() {
      if (chefs == null)
         chefs = new java.util.ArrayList<Chef>();
      return chefs.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newChefs */
   public void setChefs(java.util.Collection<Chef> newChefs) {
      removeAllChefs();
      for (java.util.Iterator iter = newChefs.iterator(); iter.hasNext();)
         addChefs((Chef)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newChef */
   public void addChefs(Chef newChef) {
      if (newChef == null)
         return;
      if (this.chefs == null)
         this.chefs = new java.util.ArrayList<Chef>();
      if (!this.chefs.contains(newChef))
      {
         this.chefs.add(newChef);
         //newChef.addShifts(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldChef */
   public void removeChefs(Chef oldChef) {
      if (oldChef == null)
         return;
      if (this.chefs != null)
         if (this.chefs.contains(oldChef))
         {
            this.chefs.remove(oldChef);
            //oldChef.removeShifts(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllChefs() {
      if (chefs != null)
      {
         Chef oldChef;
         for (java.util.Iterator iter = getIteratorChefs(); iter.hasNext();)
         {
            oldChef = (Chef)iter.next();
            iter.remove();
            //oldChef.removeShifts(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public DailySchedule getDailySchedule() {
      return dailySchedule;
   }
   
   /** @pdGenerated default parent setter
     * @param newDailySchedule */
   public void setDailySchedule(DailySchedule newDailySchedule) {
      if (this.dailySchedule == null || !this.dailySchedule.equals(newDailySchedule))
      {
         if (this.dailySchedule != null)
         {
            DailySchedule oldDailySchedule = this.dailySchedule;
            this.dailySchedule = null;
            oldDailySchedule.removeShifts(this);
         }
         if (newDailySchedule != null)
         {
            this.dailySchedule = newDailySchedule;
            this.dailySchedule.addShifts(this);
         }
      }
   }

}
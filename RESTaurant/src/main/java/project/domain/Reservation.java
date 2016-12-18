/***********************************************************************
 * Module:  Reservation.java
 * Author:  Bojan
 * Purpose: Defines the Class Reservation
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid affb386a-b846-4539-880f-1f7078b0d0dd */
public class Reservation {
   /** @pdOid 44009fe7-3ce9-4493-94f0-ad44a674708d */
   public long idReservation;
   /** @pdOid 7c45c7f4-27a7-49f5-8bc4-dbcc1431cbcb */
   public java.util.Date date;
   /** @pdOid d4eed14a-8db9-4bc9-b744-7e400251cf92 */
   public java.util.Date time;
   /** @pdOid 1c868826-b021-4d2b-a879-b6aaa2f27b10 */
   public short duration;
   
   /** @pdRoleInfo migr=no name=Table assc=reservedTables coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Table> tables;
   /** @pdRoleInfo migr=no name=Invite assc=invite coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Invite> invite;
   /** @pdRoleInfo migr=no name=Order assc=order mult=0..1 */
   public Order order;
   /** @pdRoleInfo migr=no name=Customer assc=reserving mult=0..1 side=A */
   public Customer customer;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Table> getTables() {
      if (tables == null)
         tables = new java.util.ArrayList<Table>();
      return tables;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorTables() {
      if (tables == null)
         tables = new java.util.ArrayList<Table>();
      return tables.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newTables */
   public void setTables(java.util.Collection<Table> newTables) {
      removeAllTables();
      for (java.util.Iterator iter = newTables.iterator(); iter.hasNext();)
         addTables((Table)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newTable */
   public void addTables(Table newTable) {
      if (newTable == null)
         return;
      if (this.tables == null)
         this.tables = new java.util.ArrayList<Table>();
      if (!this.tables.contains(newTable))
      {
         this.tables.add(newTable);
         newTable.setReservation(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldTable */
   public void removeTables(Table oldTable) {
      if (oldTable == null)
         return;
      if (this.tables != null)
         if (this.tables.contains(oldTable))
         {
            this.tables.remove(oldTable);
            oldTable.setReservation((Reservation)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllTables() {
      if (tables != null)
      {
         Table oldTable;
         for (java.util.Iterator iter = getIteratorTables(); iter.hasNext();)
         {
            oldTable = (Table)iter.next();
            iter.remove();
            oldTable.setReservation((Reservation)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Invite> getInvite() {
      if (invite == null)
         invite = new java.util.ArrayList<Invite>();
      return invite;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorInvite() {
      if (invite == null)
         invite = new java.util.ArrayList<Invite>();
      return invite.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newInvite */
   public void setInvite(java.util.Collection<Invite> newInvite) {
      removeAllInvite();
      for (java.util.Iterator iter = newInvite.iterator(); iter.hasNext();)
         addInvite((Invite)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newInvite */
   public void addInvite(Invite newInvite) {
      if (newInvite == null)
         return;
      if (this.invite == null)
         this.invite = new java.util.ArrayList<Invite>();
      if (!this.invite.contains(newInvite))
      {
         this.invite.add(newInvite);
         newInvite.setReservation(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldInvite */
   public void removeInvite(Invite oldInvite) {
      if (oldInvite == null)
         return;
      if (this.invite != null)
         if (this.invite.contains(oldInvite))
         {
            this.invite.remove(oldInvite);
            oldInvite.setReservation((Reservation)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllInvite() {
      if (invite != null)
      {
         Invite oldInvite;
         for (java.util.Iterator iter = getIteratorInvite(); iter.hasNext();)
         {
            oldInvite = (Invite)iter.next();
            iter.remove();
            oldInvite.setReservation((Reservation)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Customer getCustomer() {
      return customer;
   }
   
   /** @pdGenerated default parent setter
     * @param newCustomer */
   public void setCustomer(Customer newCustomer) {
      if (this.customer == null || !this.customer.equals(newCustomer))
      {
         if (this.customer != null)
         {
            Customer oldCustomer = this.customer;
            this.customer = null;
            //oldCustomer.removeReservations(this);
         }
         if (newCustomer != null)
         {
            this.customer = newCustomer;
            //this.customer.addReservations(this);
         }
      }
   }

}
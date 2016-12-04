/***********************************************************************
 * Module:  Customer.java
 * Author:  Bojan
 * Purpose: Defines the Class Customer
 ***********************************************************************/
package project.domain;

import java.util.*;

/** Gost restorana. Tabela svih gostiju.
 * 
 * @pdOid 7a8de1bd-adbc-4c69-952e-2931556e9941 */
public class Customer extends User {
   /** @pdOid 2cc755df-29c1-40a1-992d-2c3ad2add936 */
   public java.lang.String name;
   /** @pdOid 7fd0ff76-1ba7-4f13-b14a-f01a3184d28e */
   public java.lang.String surname;
   /** @pdOid ebc50c17-2270-40eb-9d25-99f820d791fd */
   public java.lang.String adress;
   /** @pdOid f5d634b2-1c29-49cc-a02b-5aab391ed239 */
   public java.util.Date dateBirth;
   
   /** @pdRoleInfo migr=no name=Reservation assc=reserving coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Reservation> reservations;
   /** @pdRoleInfo migr=no name=Invite assc=restaurantInvites coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Invite> invites;
   /** @pdRoleInfo migr=no name=VisitHistory assc=history mult=0..1 */
   public VisitHistory history;
   /** @pdRoleInfo migr=no name=Order assc=orders coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Order> orders;
   /** @pdRoleInfo migr=no name=Request assc=incoming coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Request> incomingRequests;
   /** @pdRoleInfo migr=no name=Request assc=outcoming coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Request> outcomingRequests;
   /** Svaki Gost može biti prijatelj sa više Gostiju. Veza je dvosmerna. */
   /** @pdRoleInfo migr=no name=Customer assc=friendship coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<Customer> friends;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Reservation> getReservations() {
      if (reservations == null)
         reservations = new java.util.ArrayList<Reservation>();
      return reservations;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorReservations() {
      if (reservations == null)
         reservations = new java.util.ArrayList<Reservation>();
      return reservations.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newReservations */
   public void setReservations(java.util.Collection<Reservation> newReservations) {
      removeAllReservations();
      for (java.util.Iterator iter = newReservations.iterator(); iter.hasNext();)
         addReservations((Reservation)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newReservation */
   public void addReservations(Reservation newReservation) {
      if (newReservation == null)
         return;
      if (this.reservations == null)
         this.reservations = new java.util.ArrayList<Reservation>();
      if (!this.reservations.contains(newReservation))
      {
         this.reservations.add(newReservation);
         newReservation.setCustomer(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldReservation */
   public void removeReservations(Reservation oldReservation) {
      if (oldReservation == null)
         return;
      if (this.reservations != null)
         if (this.reservations.contains(oldReservation))
         {
            this.reservations.remove(oldReservation);
            oldReservation.setCustomer((Customer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllReservations() {
      if (reservations != null)
      {
         Reservation oldReservation;
         for (java.util.Iterator iter = getIteratorReservations(); iter.hasNext();)
         {
            oldReservation = (Reservation)iter.next();
            iter.remove();
            oldReservation.setCustomer((Customer)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Invite> getInvites() {
      if (invites == null)
         invites = new java.util.ArrayList<Invite>();
      return invites;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorInvites() {
      if (invites == null)
         invites = new java.util.ArrayList<Invite>();
      return invites.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newInvites */
   public void setInvites(java.util.Collection<Invite> newInvites) {
      removeAllInvites();
      for (java.util.Iterator iter = newInvites.iterator(); iter.hasNext();)
         addInvites((Invite)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newInvite */
   public void addInvites(Invite newInvite) {
      if (newInvite == null)
         return;
      if (this.invites == null)
         this.invites = new java.util.ArrayList<Invite>();
      if (!this.invites.contains(newInvite))
         this.invites.add(newInvite);
   }
   
   /** @pdGenerated default remove
     * @param oldInvite */
   public void removeInvites(Invite oldInvite) {
      if (oldInvite == null)
         return;
      if (this.invites != null)
         if (this.invites.contains(oldInvite))
            this.invites.remove(oldInvite);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllInvites() {
      if (invites != null)
         invites.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Order> getOrders() {
      if (orders == null)
         orders = new java.util.ArrayList<Order>();
      return orders;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorOrders() {
      if (orders == null)
         orders = new java.util.ArrayList<Order>();
      return orders.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newOrders */
   public void setOrders(java.util.Collection<Order> newOrders) {
      removeAllOrders();
      for (java.util.Iterator iter = newOrders.iterator(); iter.hasNext();)
         addOrders((Order)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newOrder */
   public void addOrders(Order newOrder) {
      if (newOrder == null)
         return;
      if (this.orders == null)
         this.orders = new java.util.ArrayList<Order>();
      if (!this.orders.contains(newOrder))
      {
         this.orders.add(newOrder);
         newOrder.setCustomer(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldOrder */
   public void removeOrders(Order oldOrder) {
      if (oldOrder == null)
         return;
      if (this.orders != null)
         if (this.orders.contains(oldOrder))
         {
            this.orders.remove(oldOrder);
            oldOrder.setCustomer((Customer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOrders() {
      if (orders != null)
      {
         Order oldOrder;
         for (java.util.Iterator iter = getIteratorOrders(); iter.hasNext();)
         {
            oldOrder = (Order)iter.next();
            iter.remove();
            oldOrder.setCustomer((Customer)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Request> getIncomingRequests() {
      if (incomingRequests == null)
         incomingRequests = new java.util.ArrayList<Request>();
      return incomingRequests;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorIncomingRequests() {
      if (incomingRequests == null)
         incomingRequests = new java.util.ArrayList<Request>();
      return incomingRequests.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newIncomingRequests */
   public void setIncomingRequests(java.util.Collection<Request> newIncomingRequests) {
      removeAllIncomingRequests();
      for (java.util.Iterator iter = newIncomingRequests.iterator(); iter.hasNext();)
         addIncomingRequests((Request)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRequest */
   public void addIncomingRequests(Request newRequest) {
      if (newRequest == null)
         return;
      if (this.incomingRequests == null)
         this.incomingRequests = new java.util.ArrayList<Request>();
      if (!this.incomingRequests.contains(newRequest))
      {
         this.incomingRequests.add(newRequest);
         newRequest.setReceiver(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldRequest */
   public void removeIncomingRequests(Request oldRequest) {
      if (oldRequest == null)
         return;
      if (this.incomingRequests != null)
         if (this.incomingRequests.contains(oldRequest))
         {
            this.incomingRequests.remove(oldRequest);
            oldRequest.setReceiver((Customer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllIncomingRequests() {
      if (incomingRequests != null)
      {
         Request oldRequest;
         for (java.util.Iterator iter = getIteratorIncomingRequests(); iter.hasNext();)
         {
            oldRequest = (Request)iter.next();
            iter.remove();
            oldRequest.setReceiver((Customer)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Request> getOutcomingRequests() {
      if (outcomingRequests == null)
         outcomingRequests = new java.util.ArrayList<Request>();
      return outcomingRequests;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorOutcomingRequests() {
      if (outcomingRequests == null)
         outcomingRequests = new java.util.ArrayList<Request>();
      return outcomingRequests.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newOutcomingRequests */
   public void setOutcomingRequests(java.util.Collection<Request> newOutcomingRequests) {
      removeAllOutcomingRequests();
      for (java.util.Iterator iter = newOutcomingRequests.iterator(); iter.hasNext();)
         addOutcomingRequests((Request)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRequest */
   public void addOutcomingRequests(Request newRequest) {
      if (newRequest == null)
         return;
      if (this.outcomingRequests == null)
         this.outcomingRequests = new java.util.ArrayList<Request>();
      if (!this.outcomingRequests.contains(newRequest))
      {
         this.outcomingRequests.add(newRequest);
         newRequest.setSender(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldRequest */
   public void removeOutcomingRequests(Request oldRequest) {
      if (oldRequest == null)
         return;
      if (this.outcomingRequests != null)
         if (this.outcomingRequests.contains(oldRequest))
         {
            this.outcomingRequests.remove(oldRequest);
            oldRequest.setSender((Customer)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOutcomingRequests() {
      if (outcomingRequests != null)
      {
         Request oldRequest;
         for (java.util.Iterator iter = getIteratorOutcomingRequests(); iter.hasNext();)
         {
            oldRequest = (Request)iter.next();
            iter.remove();
            oldRequest.setSender((Customer)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Customer> getFriends() {
      if (friends == null)
         friends = new java.util.ArrayList<Customer>();
      return friends;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorFriends() {
      if (friends == null)
         friends = new java.util.ArrayList<Customer>();
      return friends.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newFriends */
   public void setFriends(java.util.Collection<Customer> newFriends) {
      removeAllFriends();
      for (java.util.Iterator iter = newFriends.iterator(); iter.hasNext();)
         addFriends((Customer)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newCustomer */
   public void addFriends(Customer newCustomer) {
      if (newCustomer == null)
         return;
      if (this.friends == null)
         this.friends = new java.util.ArrayList<Customer>();
      if (!this.friends.contains(newCustomer))
         this.friends.add(newCustomer);
   }
   
   /** @pdGenerated default remove
     * @param oldCustomer */
   public void removeFriends(Customer oldCustomer) {
      if (oldCustomer == null)
         return;
      if (this.friends != null)
         if (this.friends.contains(oldCustomer))
            this.friends.remove(oldCustomer);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllFriends() {
      if (friends != null)
         friends.clear();
   }

}
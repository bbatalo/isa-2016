package project.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends User {

	private static final long serialVersionUID = -2780627537315030281L;

	@Column(name = "cst_name", nullable = true)
	private String name;
	
	@Column(name = "cst_surname", nullable = true)
    private String surname;
	
	@Column(name = "cst_address", nullable = true)
    private String address;
	
	@Column(name = "cst_date_birth", nullable = true)
    private Date dateBirth;
	
	public Customer() {}

	/*
    public java.util.Collection<Reservation> reservations;

    public java.util.Collection<Invite> invites;

    public VisitHistory history;

    public java.util.Collection<Order> orders;

    public java.util.Collection<Request> incomingRequests;

    public java.util.Collection<Request> outcomingRequests;

    public java.util.Collection<Customer> friends;
    */

    public String getName() {
    	return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	/*
   public java.util.Collection<Reservation> getReservations() {
      if (reservations == null)
         reservations = new java.util.ArrayList<Reservation>();
      return reservations;
   }
   
   public java.util.Iterator getIteratorReservations() {
      if (reservations == null)
         reservations = new java.util.ArrayList<Reservation>();
      return reservations.iterator();
   }
   
   public void setReservations(java.util.Collection<Reservation> newReservations) {
      removeAllReservations();
      for (java.util.Iterator iter = newReservations.iterator(); iter.hasNext();)
         addReservations((Reservation)iter.next());
   }
   
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
   
   public java.util.Collection<Invite> getInvites() {
      if (invites == null)
         invites = new java.util.ArrayList<Invite>();
      return invites;
   }
   

   public java.util.Iterator getIteratorInvites() {
      if (invites == null)
         invites = new java.util.ArrayList<Invite>();
      return invites.iterator();
   }
   
   public void setInvites(java.util.Collection<Invite> newInvites) {
      removeAllInvites();
      for (java.util.Iterator iter = newInvites.iterator(); iter.hasNext();)
         addInvites((Invite)iter.next());
   }
   
   public void addInvites(Invite newInvite) {
      if (newInvite == null)
         return;
      if (this.invites == null)
         this.invites = new java.util.ArrayList<Invite>();
      if (!this.invites.contains(newInvite))
         this.invites.add(newInvite);
   }
   
   public void removeInvites(Invite oldInvite) {
      if (oldInvite == null)
         return;
      if (this.invites != null)
         if (this.invites.contains(oldInvite))
            this.invites.remove(oldInvite);
   }
   

   public void removeAllInvites() {
      if (invites != null)
         invites.clear();
   }

   public java.util.Collection<Order> getOrders() {
      if (orders == null)
         orders = new java.util.ArrayList<Order>();
      return orders;
   }
   

   public java.util.Iterator getIteratorOrders() {
      if (orders == null)
         orders = new java.util.ArrayList<Order>();
      return orders.iterator();
   }

   public void setOrders(java.util.Collection<Order> newOrders) {
      removeAllOrders();
      for (java.util.Iterator iter = newOrders.iterator(); iter.hasNext();)
         addOrders((Order)iter.next());
   }

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

   public java.util.Collection<Request> getIncomingRequests() {
      if (incomingRequests == null)
         incomingRequests = new java.util.ArrayList<Request>();
      return incomingRequests;
   }
   
   
   public java.util.Iterator getIteratorIncomingRequests() {
      if (incomingRequests == null)
         incomingRequests = new java.util.ArrayList<Request>();
      return incomingRequests.iterator();
   }
   
   public void setIncomingRequests(java.util.Collection<Request> newIncomingRequests) {
      removeAllIncomingRequests();
      for (java.util.Iterator iter = newIncomingRequests.iterator(); iter.hasNext();)
         addIncomingRequests((Request)iter.next());
   }
   
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
   
   public java.util.Collection<Request> getOutcomingRequests() {
      if (outcomingRequests == null)
         outcomingRequests = new java.util.ArrayList<Request>();
      return outcomingRequests;
   }
   
   public java.util.Iterator getIteratorOutcomingRequests() {
      if (outcomingRequests == null)
         outcomingRequests = new java.util.ArrayList<Request>();
      return outcomingRequests.iterator();
   }
   
   public void setOutcomingRequests(java.util.Collection<Request> newOutcomingRequests) {
      removeAllOutcomingRequests();
      for (java.util.Iterator iter = newOutcomingRequests.iterator(); iter.hasNext();)
         addOutcomingRequests((Request)iter.next());
   }
   
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
   
   public java.util.Collection<Customer> getFriends() {
      if (friends == null)
         friends = new java.util.ArrayList<Customer>();
      return friends;
   }
   
   public java.util.Iterator getIteratorFriends() {
      if (friends == null)
         friends = new java.util.ArrayList<Customer>();
      return friends.iterator();
   }
   
   public void setFriends(java.util.Collection<Customer> newFriends) {
      removeAllFriends();
      for (java.util.Iterator iter = newFriends.iterator(); iter.hasNext();)
         addFriends((Customer)iter.next());
   }
   
   public void addFriends(Customer newCustomer) {
      if (newCustomer == null)
         return;
      if (this.friends == null)
         this.friends = new java.util.ArrayList<Customer>();
      if (!this.friends.contains(newCustomer))
         this.friends.add(newCustomer);
   }
   
   public void removeFriends(Customer oldCustomer) {
      if (oldCustomer == null)
         return;
      if (this.friends != null)
         if (this.friends.contains(oldCustomer))
            this.friends.remove(oldCustomer);
   }
   
   public void removeAllFriends() {
      if (friends != null)
         friends.clear();
   }
   */
}
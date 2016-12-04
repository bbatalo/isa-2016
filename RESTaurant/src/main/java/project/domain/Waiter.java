/***********************************************************************
 * Module:  Waiter.java
 * Author:  Bojan
 * Purpose: Defines the Class Waiter
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid dcda712e-ee24-4bf6-a599-92b4e9193b9b */
public class Waiter extends Employee {
   /** @pdOid e57be3af-2938-470f-8687-67d88e7413a7 */
   public boolean trial;
   
   /** @pdRoleInfo migr=no name=Order assc=ordering coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Order> orders;
   /** @pdRoleInfo migr=no name=WaiterRating assc=waiterRatings coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<WaiterRating> ratings;
   public java.util.Collection waiterArea;
   
   
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
         this.orders.add(newOrder);
   }
   
   /** @pdGenerated default remove
     * @param oldOrder */
   public void removeOrders(Order oldOrder) {
      if (oldOrder == null)
         return;
      if (this.orders != null)
         if (this.orders.contains(oldOrder))
            this.orders.remove(oldOrder);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOrders() {
      if (orders != null)
         orders.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<WaiterRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<WaiterRating>();
      return ratings;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<WaiterRating>();
      return ratings.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRatings */
   public void setRatings(java.util.Collection<WaiterRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((WaiterRating)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newWaiterRating */
   public void addRatings(WaiterRating newWaiterRating) {
      if (newWaiterRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<WaiterRating>();
      if (!this.ratings.contains(newWaiterRating))
      {
         this.ratings.add(newWaiterRating);
         newWaiterRating.setWaiter(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldWaiterRating */
   public void removeRatings(WaiterRating oldWaiterRating) {
      if (oldWaiterRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldWaiterRating))
         {
            this.ratings.remove(oldWaiterRating);
            oldWaiterRating.setWaiter((Waiter)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRatings() {
      if (ratings != null)
      {
         WaiterRating oldWaiterRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldWaiterRating = (WaiterRating)iter.next();
            iter.remove();
            oldWaiterRating.setWaiter((Waiter)null);
         }
      }
   }

}
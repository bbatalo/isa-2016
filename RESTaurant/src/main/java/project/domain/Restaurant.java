/***********************************************************************
 * Module:  Restaurant.java
 * Author:  Bojan
 * Purpose: Defines the Class Restaurant
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 2ab6373d-fdfc-45eb-b0ca-362e3b8ca236 */
public class Restaurant {
   /** @pdOid 81a9f18d-6e85-401e-bf9a-6f8ea50b203c */
   public long idRestoraunt;
   /** @pdOid 25f90c78-599b-42a7-911f-5cf2cbfacb37 */
   public java.lang.String type;
   /** @pdOid a0852914-cb2e-48bf-8b51-3feabd09ca43 */
   public java.lang.String description;
   
   /** @pdRoleInfo migr=no name=SeatingArrangement assc=seating mult=0..1 */
   public SeatingArrangement seating;
   /** @pdRoleInfo migr=no name=Employee assc=radnici coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Employee> employees;
   /** @pdRoleInfo migr=no name=Menu assc=menu mult=0..1 */
   public Menu menu;
   /** @pdRoleInfo migr=no name=WorkSchedule assc=schedule coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<WorkSchedule> schedule;
   /** @pdRoleInfo migr=no name=Order assc=ordersRest coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Order> orders;
   /** @pdRoleInfo migr=no name=RestorauntManager assc=management coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<RestorauntManager> managers;
   /** @pdRoleInfo migr=no name=DrinksMenu assc=drinkMenu mult=0..1 side=A */
   public DrinksMenu drinks;
   /** @pdRoleInfo migr=no name=RestaurantRating assc=ratings coll=java.util.Collection impl=java.util.ArrayList mult=0..* side=A */
   public java.util.Collection<RestaurantRating> ratings;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Employee> getEmployees() {
      if (employees == null)
         employees = new java.util.ArrayList<Employee>();
      return employees;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorEmployees() {
      if (employees == null)
         employees = new java.util.ArrayList<Employee>();
      return employees.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newEmployees */
   public void setEmployees(java.util.Collection<Employee> newEmployees) {
      removeAllEmployees();
      for (java.util.Iterator iter = newEmployees.iterator(); iter.hasNext();)
         addEmployees((Employee)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newEmployee */
   public void addEmployees(Employee newEmployee) {
      if (newEmployee == null)
         return;
      if (this.employees == null)
         this.employees = new java.util.ArrayList<Employee>();
      if (!this.employees.contains(newEmployee))
      {
         this.employees.add(newEmployee);
         //newEmployee.setRestaurant(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldEmployee */
   public void removeEmployees(Employee oldEmployee) {
      if (oldEmployee == null)
         return;
      if (this.employees != null)
         if (this.employees.contains(oldEmployee))
         {
            this.employees.remove(oldEmployee);
            //oldEmployee.setRestaurant((Restaurant)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllEmployees() {
      if (employees != null)
      {
         Employee oldEmployee;
         for (java.util.Iterator iter = getIteratorEmployees(); iter.hasNext();)
         {
            oldEmployee = (Employee)iter.next();
            iter.remove();
            //oldEmployee.setRestaurant((Restaurant)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<WorkSchedule> getSchedule() {
      if (schedule == null)
         schedule = new java.util.ArrayList<WorkSchedule>();
      return schedule;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSchedule() {
      if (schedule == null)
         schedule = new java.util.ArrayList<WorkSchedule>();
      return schedule.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSchedule */
   public void setSchedule(java.util.Collection<WorkSchedule> newSchedule) {
      removeAllSchedule();
      for (java.util.Iterator iter = newSchedule.iterator(); iter.hasNext();)
         addSchedule((WorkSchedule)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newWorkSchedule */
   public void addSchedule(WorkSchedule newWorkSchedule) {
      if (newWorkSchedule == null)
         return;
      if (this.schedule == null)
         this.schedule = new java.util.ArrayList<WorkSchedule>();
      if (!this.schedule.contains(newWorkSchedule))
      {
         this.schedule.add(newWorkSchedule);
         newWorkSchedule.setRestaurant(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldWorkSchedule */
   public void removeSchedule(WorkSchedule oldWorkSchedule) {
      if (oldWorkSchedule == null)
         return;
      if (this.schedule != null)
         if (this.schedule.contains(oldWorkSchedule))
         {
            this.schedule.remove(oldWorkSchedule);
            oldWorkSchedule.setRestaurant((Restaurant)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSchedule() {
      if (schedule != null)
      {
         WorkSchedule oldWorkSchedule;
         for (java.util.Iterator iter = getIteratorSchedule(); iter.hasNext();)
         {
            oldWorkSchedule = (WorkSchedule)iter.next();
            iter.remove();
            oldWorkSchedule.setRestaurant((Restaurant)null);
         }
      }
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
         newOrder.setRestaurant(this);      
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
            oldOrder.setRestaurant((Restaurant)null);
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
            oldOrder.setRestaurant((Restaurant)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<RestorauntManager> getManagers() {
      if (managers == null)
         managers = new java.util.ArrayList<RestorauntManager>();
      return managers;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorManagers() {
      if (managers == null)
         managers = new java.util.ArrayList<RestorauntManager>();
      return managers.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newManagers */
   public void setManagers(java.util.Collection<RestorauntManager> newManagers) {
      removeAllManagers();
      for (java.util.Iterator iter = newManagers.iterator(); iter.hasNext();)
         addManagers((RestorauntManager)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRestorauntManager */
   public void addManagers(RestorauntManager newRestorauntManager) {
      if (newRestorauntManager == null)
         return;
      if (this.managers == null)
         this.managers = new java.util.ArrayList<RestorauntManager>();
      if (!this.managers.contains(newRestorauntManager))
      {
         this.managers.add(newRestorauntManager);
         //newRestorauntManager.setRestoraunt(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldRestorauntManager */
   public void removeManagers(RestorauntManager oldRestorauntManager) {
      if (oldRestorauntManager == null)
         return;
      if (this.managers != null)
         if (this.managers.contains(oldRestorauntManager))
         {
            this.managers.remove(oldRestorauntManager);
            //oldRestorauntManager.setRestoraunt((Restaurant)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllManagers() {
      if (managers != null)
      {
         RestorauntManager oldRestorauntManager;
         for (java.util.Iterator iter = getIteratorManagers(); iter.hasNext();)
         {
            oldRestorauntManager = (RestorauntManager)iter.next();
            iter.remove();
            //oldRestorauntManager.setRestoraunt((Restaurant)null);
         }
      }
   }
   /** @pdGenerated default getter */
   public java.util.Collection<RestaurantRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<RestaurantRating>();
      return ratings;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<RestaurantRating>();
      return ratings.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRatings */
   public void setRatings(java.util.Collection<RestaurantRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((RestaurantRating)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRestaurantRating */
   public void addRatings(RestaurantRating newRestaurantRating) {
      if (newRestaurantRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<RestaurantRating>();
      if (!this.ratings.contains(newRestaurantRating))
      {
         this.ratings.add(newRestaurantRating);
         newRestaurantRating.setRestaurant(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldRestaurantRating */
   public void removeRatings(RestaurantRating oldRestaurantRating) {
      if (oldRestaurantRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldRestaurantRating))
         {
            this.ratings.remove(oldRestaurantRating);
            oldRestaurantRating.setRestaurant((Restaurant)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRatings() {
      if (ratings != null)
      {
         RestaurantRating oldRestaurantRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldRestaurantRating = (RestaurantRating)iter.next();
            iter.remove();
            oldRestaurantRating.setRestaurant((Restaurant)null);
         }
      }
   }

}
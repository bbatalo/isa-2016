package project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Chef extends Employee {

	private static final long serialVersionUID = -8112031432823488140L;
	
	@Column(name = "chf_type", nullable = false)
    private String type;

	protected Chef() {}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
   /*
   public java.util.Collection<ChefRating> ratings;
   
   public java.util.Collection<Shift> shifts;
   
   public java.util.Collection<DishesOrder> dishOrder;
   */
   
   /*
   public java.util.Collection<ChefRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<ChefRating>();
      return ratings;
   }
   
   
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<ChefRating>();
      return ratings.iterator();
   }
   
   public void setRatings(java.util.Collection<ChefRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((ChefRating)iter.next());
   }
   

   public void addRatings(ChefRating newChefRating) {
      if (newChefRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<ChefRating>();
      if (!this.ratings.contains(newChefRating))
      {
         this.ratings.add(newChefRating);
         newChefRating.setChef(this);      
      }
   }

   public void removeRatings(ChefRating oldChefRating) {
      if (oldChefRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldChefRating))
         {
            this.ratings.remove(oldChefRating);
            oldChefRating.setChef((Chef)null);
         }
   }
   

   public void removeAllRatings() {
      if (ratings != null)
      {
         ChefRating oldChefRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldChefRating = (ChefRating)iter.next();
            iter.remove();
            oldChefRating.setChef((Chef)null);
         }
      }
   }

   public java.util.Collection<Shift> getShifts() {
      if (shifts == null)
         shifts = new java.util.ArrayList<Shift>();
      return shifts;
   }
   
   public java.util.Iterator getIteratorShifts() {
      if (shifts == null)
         shifts = new java.util.ArrayList<Shift>();
      return shifts.iterator();
   }
   
   public void setShifts(java.util.Collection<Shift> newShifts) {
      removeAllShifts();
      for (java.util.Iterator iter = newShifts.iterator(); iter.hasNext();)
         addShifts((Shift)iter.next());
   }
   
   public void addShifts(Shift newShift) {
      if (newShift == null)
         return;
      if (this.shifts == null)
         this.shifts = new java.util.ArrayList<Shift>();
      if (!this.shifts.contains(newShift))
      {
         this.shifts.add(newShift);
         newShift.addChefs(this);      
      }
   }

   public void removeShifts(Shift oldShift) {
      if (oldShift == null)
         return;
      if (this.shifts != null)
         if (this.shifts.contains(oldShift))
         {
            this.shifts.remove(oldShift);
            oldShift.removeChefs(this);
         }
   }

   public void removeAllShifts() {
      if (shifts != null)
      {
         Shift oldShift;
         for (java.util.Iterator iter = getIteratorShifts(); iter.hasNext();)
         {
            oldShift = (Shift)iter.next();
            iter.remove();
            oldShift.removeChefs(this);
         }
      }
   }

   public java.util.Collection<DishesOrder> getDishOrder() {
      if (dishOrder == null)
         dishOrder = new java.util.ArrayList<DishesOrder>();
      return dishOrder;
   }
   
   public java.util.Iterator getIteratorDishOrder() {
      if (dishOrder == null)
         dishOrder = new java.util.ArrayList<DishesOrder>();
      return dishOrder.iterator();
   }
   
   public void setDishOrder(java.util.Collection<DishesOrder> newDishOrder) {
      removeAllDishOrder();
      for (java.util.Iterator iter = newDishOrder.iterator(); iter.hasNext();)
         addDishOrder((DishesOrder)iter.next());
   }
   
   public void addDishOrder(DishesOrder newDishesOrder) {
      if (newDishesOrder == null)
         return;
      if (this.dishOrder == null)
         this.dishOrder = new java.util.ArrayList<DishesOrder>();
      if (!this.dishOrder.contains(newDishesOrder))
      {
         this.dishOrder.add(newDishesOrder);
         newDishesOrder.addChef(this);      
      }
   }
   

   public void removeDishOrder(DishesOrder oldDishesOrder) {
      if (oldDishesOrder == null)
         return;
      if (this.dishOrder != null)
         if (this.dishOrder.contains(oldDishesOrder))
         {
            this.dishOrder.remove(oldDishesOrder);
            oldDishesOrder.removeChef(this);
         }
   }
   
   public void removeAllDishOrder() {
      if (dishOrder != null)
      {
         DishesOrder oldDishesOrder;
         for (java.util.Iterator iter = getIteratorDishOrder(); iter.hasNext();)
         {
            oldDishesOrder = (DishesOrder)iter.next();
            iter.remove();
            oldDishesOrder.removeChef(this);
         }
      }
   }
	*/
}
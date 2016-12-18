package project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Bartender extends Employee {

	private static final long serialVersionUID = -4567146652809235714L;
	
	@Column(name = "bar_cocktails", nullable = false)
    private boolean cocktails = false;

	protected Bartender() {}
	
	public boolean isCocktails() {
		return cocktails;
	}

	public void setCocktails(boolean cocktails) {
		this.cocktails = cocktails;
	}
    
	
   /*
   public java.util.Collection<DrinksOrder> drinkOrder;

   public java.util.Collection<BartenderRating> ratings;

   public java.util.Collection<Shift> shifts;
   
   

   public java.util.Collection<DrinksOrder> getDrinkOrder() {
      if (drinkOrder == null)
         drinkOrder = new java.util.ArrayList<DrinksOrder>();
      return drinkOrder;
   }

   public java.util.Iterator getIteratorDrinkOrder() {
      if (drinkOrder == null)
         drinkOrder = new java.util.ArrayList<DrinksOrder>();
      return drinkOrder.iterator();
   }
   
   public void setDrinkOrder(java.util.Collection<DrinksOrder> newDrinkOrder) {
      removeAllDrinkOrder();
      for (java.util.Iterator iter = newDrinkOrder.iterator(); iter.hasNext();)
         addDrinkOrder((DrinksOrder)iter.next());
   }

   public void addDrinkOrder(DrinksOrder newDrinksOrder) {
      if (newDrinksOrder == null)
         return;
      if (this.drinkOrder == null)
         this.drinkOrder = new java.util.ArrayList<DrinksOrder>();
      if (!this.drinkOrder.contains(newDrinksOrder))
      {
         this.drinkOrder.add(newDrinksOrder);
         newDrinksOrder.addBartender(this);      
      }
   }

   public void removeDrinkOrder(DrinksOrder oldDrinksOrder) {
      if (oldDrinksOrder == null)
         return;
      if (this.drinkOrder != null)
         if (this.drinkOrder.contains(oldDrinksOrder))
         {
            this.drinkOrder.remove(oldDrinksOrder);
            oldDrinksOrder.removeBartender(this);
         }
   }
   
   public void removeAllDrinkOrder() {
      if (drinkOrder != null)
      {
         DrinksOrder oldDrinksOrder;
         for (java.util.Iterator iter = getIteratorDrinkOrder(); iter.hasNext();)
         {
            oldDrinksOrder = (DrinksOrder)iter.next();
            iter.remove();
            oldDrinksOrder.removeBartender(this);
         }
      }
   }

   public java.util.Collection<BartenderRating> getRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<BartenderRating>();
      return ratings;
   }
   
   public java.util.Iterator getIteratorRatings() {
      if (ratings == null)
         ratings = new java.util.ArrayList<BartenderRating>();
      return ratings.iterator();
   }
   
   public void setRatings(java.util.Collection<BartenderRating> newRatings) {
      removeAllRatings();
      for (java.util.Iterator iter = newRatings.iterator(); iter.hasNext();)
         addRatings((BartenderRating)iter.next());
   }

   public void addRatings(BartenderRating newBartenderRating) {
      if (newBartenderRating == null)
         return;
      if (this.ratings == null)
         this.ratings = new java.util.ArrayList<BartenderRating>();
      if (!this.ratings.contains(newBartenderRating))
      {
         this.ratings.add(newBartenderRating);
         newBartenderRating.setBartender(this);      
      }
   }
   
   public void removeRatings(BartenderRating oldBartenderRating) {
      if (oldBartenderRating == null)
         return;
      if (this.ratings != null)
         if (this.ratings.contains(oldBartenderRating))
         {
            this.ratings.remove(oldBartenderRating);
            oldBartenderRating.setBartender((Bartender)null);
         }
   }
   
   public void removeAllRatings() {
      if (ratings != null)
      {
         BartenderRating oldBartenderRating;
         for (java.util.Iterator iter = getIteratorRatings(); iter.hasNext();)
         {
            oldBartenderRating = (BartenderRating)iter.next();
            iter.remove();
            oldBartenderRating.setBartender((Bartender)null);
         }
      }
   }
   
   public java.util.Collection<Shift> getShifts() {
      if (shifts == null)
         shifts = new java.util.HashSet<Shift>();
      return shifts;
   }
   
   public java.util.Iterator getIteratorShifts() {
      if (shifts == null)
         shifts = new java.util.HashSet<Shift>();
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
         this.shifts = new java.util.HashSet<Shift>();
      if (!this.shifts.contains(newShift))
      {
         this.shifts.add(newShift);
         newShift.addBartenders(this);      
      }
   }

   public void removeShifts(Shift oldShift) {
      if (oldShift == null)
         return;
      if (this.shifts != null)
         if (this.shifts.contains(oldShift))
         {
            this.shifts.remove(oldShift);
            oldShift.removeBartenders(this);
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
            oldShift.removeBartenders(this);
         }
      }
   }
*/
}
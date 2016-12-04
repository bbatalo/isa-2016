/***********************************************************************
 * Module:  DrinksMenu.java
 * Author:  Bojan
 * Purpose: Defines the Class DrinksMenu
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid c6c07d07-3fc4-42f9-bcc0-86334830a3d0 */
public class DrinksMenu {
   /** @pdOid 76eb3174-4015-4ed3-b116-c040d1f05dd4 */
   public long idDrinkMenu;
   
   /** @pdRoleInfo migr=no name=Restaurant assc=drinkMenu mult=0..1 */
   public Restaurant restaurant;
   /** @pdRoleInfo migr=no name=Drink assc=pica coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Drink> drinks;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Drink> getDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<Drink>();
      return drinks;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorDrinks() {
      if (drinks == null)
         drinks = new java.util.ArrayList<Drink>();
      return drinks.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDrinks */
   public void setDrinks(java.util.Collection<Drink> newDrinks) {
      removeAllDrinks();
      for (java.util.Iterator iter = newDrinks.iterator(); iter.hasNext();)
         addDrinks((Drink)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newDrink */
   public void addDrinks(Drink newDrink) {
      if (newDrink == null)
         return;
      if (this.drinks == null)
         this.drinks = new java.util.ArrayList<Drink>();
      if (!this.drinks.contains(newDrink))
      {
         this.drinks.add(newDrink);
         newDrink.addDrinkMenu(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldDrink */
   public void removeDrinks(Drink oldDrink) {
      if (oldDrink == null)
         return;
      if (this.drinks != null)
         if (this.drinks.contains(oldDrink))
         {
            this.drinks.remove(oldDrink);
            oldDrink.removeDrinkMenu(this);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDrinks() {
      if (drinks != null)
      {
         Drink oldDrink;
         for (java.util.Iterator iter = getIteratorDrinks(); iter.hasNext();)
         {
            oldDrink = (Drink)iter.next();
            iter.remove();
            oldDrink.removeDrinkMenu(this);
         }
      }
   }

}
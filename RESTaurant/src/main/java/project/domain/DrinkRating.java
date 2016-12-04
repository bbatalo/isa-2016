/***********************************************************************
 * Module:  DrinkRating.java
 * Author:  Bojan
 * Purpose: Defines the Class DrinkRating
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid e57e561e-1267-4517-b6d9-ffd26b0cf55d */
public class DrinkRating {
   /** @pdOid 46ed0eb2-cb37-4acf-a14f-75ee9d2266da */
   public long idDrinkRating;
   /** @pdOid 90a761ef-3d18-45a0-82ab-1de777b4464b */
   public short rating;
   
   /** @pdRoleInfo migr=no name=Rating assc=drinkRatings mult=0..1 side=A */
   public Rating mainRating;
   /** @pdRoleInfo migr=no name=Drink assc=drinkRating mult=0..1 side=A */
   public Drink drink;
   
   
   /** @pdGenerated default parent getter */
   public Drink getDrink() {
      return drink;
   }
   
   /** @pdGenerated default parent setter
     * @param newDrink */
   public void setDrink(Drink newDrink) {
      if (this.drink == null || !this.drink.equals(newDrink))
      {
         if (this.drink != null)
         {
            Drink oldDrink = this.drink;
            this.drink = null;
            oldDrink.removeRatings(this);
         }
         if (newDrink != null)
         {
            this.drink = newDrink;
            this.drink.addRatings(this);
         }
      }
   }

}
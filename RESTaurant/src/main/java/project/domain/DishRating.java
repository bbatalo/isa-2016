/***********************************************************************
 * Module:  DishRating.java
 * Author:  Bojan
 * Purpose: Defines the Class DishRating
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid fec3a856-2514-4bc0-b8bb-571f9995d07d */
public class DishRating {
   /** @pdOid 1f11a110-1b97-4b0a-9203-4027e5fb736c */
   public long idDishRating;
   /** @pdOid 89d7898f-7217-400a-b2c1-9ebcb5b7cd4e */
   public short rating;
   
   /** @pdRoleInfo migr=no name=Rating assc=dishRating mult=0..1 side=A */
   public Rating mainRating;
   /** @pdRoleInfo migr=no name=Dish assc=dishesRating mult=0..1 side=A */
   public Dish dish;
   
   
   /** @pdGenerated default parent getter */
   public Dish getDish() {
      return dish;
   }
   /*
   public void setDish(Dish newDish) {
      if (this.dish == null || !this.dish.equals(newDish))
      {
         if (this.dish != null)
         {
            Dish oldDish = this.dish;
            this.dish = null;
            oldDish.removeRatings(this);
         }
         if (newDish != null)
         {
            this.dish = newDish;
            this.dish.addRatings(this);
         }
      }
   }
	*/
}
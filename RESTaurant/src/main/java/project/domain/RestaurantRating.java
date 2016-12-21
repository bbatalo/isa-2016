/***********************************************************************
 * Module:  RestaurantRating.java
 * Author:  Bojan
 * Purpose: Defines the Class RestaurantRating
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid cffb1386-dcac-40fc-9c49-8d4e3cd2fc50 */
public class RestaurantRating {
   /** @pdOid 0f79ce8d-a70c-4786-94f5-a4ef8feeef7e */
   public long idRestRating;
   /** @pdOid 8e6dbf6a-67fd-404b-ae1e-0e9f2351b450 */
   public short rating;
   
   /** @pdRoleInfo migr=no name=Restaurant assc=ratings mult=0..1 */
   public Restaurant restaurant;
   /** @pdRoleInfo migr=no name=Rating assc=ocenaRestorana mult=0..1 side=A */
   public Rating mainRating;
   
   
   /** @pdGenerated default parent getter */
   public Restaurant getRestaurant() {
      return restaurant;
   }
   
   /** @pdGenerated default parent setter
     * @param newRestaurant */
   public void setRestaurant(Restaurant newRestaurant) {
      if (this.restaurant == null || !this.restaurant.equals(newRestaurant))
      {
         if (this.restaurant != null)
         {
            Restaurant oldRestaurant = this.restaurant;
            this.restaurant = null;
            //oldRestaurant.removeRatings(this);
         }
         if (newRestaurant != null)
         {
            this.restaurant = newRestaurant;
            //this.restaurant.addRatings(this);
         }
      }
   }

}
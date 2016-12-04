/***********************************************************************
 * Module:  Rating.java
 * Author:  Bojan
 * Purpose: Defines the Class Rating
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 9bac9a91-05bd-4cfe-8b60-f24308b1a635 */
public class Rating {
   /** @pdOid a7dcee06-3d5b-49d9-8086-065354a8f0ab */
   public long idRating;
   
   /** @pdRoleInfo migr=no name=DishRating assc=dishRating mult=0..1 */
   public DishRating dishRating;
   /** @pdRoleInfo migr=no name=RestaurantRating assc=ocenaRestorana mult=0..1 */
   public RestaurantRating restaurantRating;
   /** @pdRoleInfo migr=no name=ServiceRating assc=serviceRating mult=0..1 */
   public ServiceRating serviceRating;
   /** @pdRoleInfo migr=no name=DrinkRating assc=drinkRatings mult=0..1 */
   public DrinkRating drinkRating;
   /** @pdRoleInfo migr=no name=Visit assc=rating mult=0..1 side=A */
   public Visit visit;

}
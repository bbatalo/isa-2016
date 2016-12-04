/***********************************************************************
 * Module:  Employee.java
 * Author:  Bojan
 * Purpose: Defines the Class Employee
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid bbf995ec-150d-488a-8a46-e18c3af07da3 */
public class Employee extends User {
   /** @pdOid 10e7e8e9-bb32-4288-a775-dc2be782fe96 */
   public java.lang.String name;
   /** @pdOid 604a971e-f58c-4db8-b337-98826ee2e9c8 */
   public java.lang.String surname;
   /** @pdOid c3e2a211-03ee-43c5-ba18-51118a2b46b9 */
   public short role;
   /** @pdOid 001ea76d-4223-4397-afe8-dcf4b5dda3f0 */
   public boolean passChanged;
   /** @pdOid d6da1500-2003-43a8-ac57-d9effa40fffb */
   public java.util.Date dateBirth;
   /** @pdOid 3f2ae124-57b1-4f47-a632-21b0e486872b */
   public int sizeCloth;
   /** @pdOid 8c90a228-7855-49b1-986e-057fd8f232aa */
   public int sizeShoes;
   
   /** @pdRoleInfo migr=no name=Restaurant assc=radnici mult=0..1 side=A */
   public Restaurant restaurant;
   
   
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
            oldRestaurant.removeEmployees(this);
         }
         if (newRestaurant != null)
         {
            this.restaurant = newRestaurant;
            this.restaurant.addEmployees(this);
         }
      }
   }

}
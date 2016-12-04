/***********************************************************************
 * Module:  Table.java
 * Author:  Bojan
 * Purpose: Defines the Class Table
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 85c00d53-d38b-458f-97cc-a831c22b8088 */
public class Table {
   /** @pdOid d0c3eb2b-1335-4901-93a4-8e71a5d6c475 */
   public long idTable;
   /** @pdOid 85668cc7-632b-4c7a-8aa7-d447f0b5096e */
   public int number;
   /** @pdOid 5b2875c3-87d3-4686-a7ef-dfaa60a6c04b */
   public java.lang.String status;
   
   /** @pdRoleInfo migr=no name=Reservation assc=reservedTables mult=0..1 side=A */
   public Reservation reservation;
   
   
   /** @pdGenerated default parent getter */
   public Reservation getReservation() {
      return reservation;
   }
   
   /** @pdGenerated default parent setter
     * @param newReservation */
   public void setReservation(Reservation newReservation) {
      if (this.reservation == null || !this.reservation.equals(newReservation))
      {
         if (this.reservation != null)
         {
            Reservation oldReservation = this.reservation;
            this.reservation = null;
            oldReservation.removeTables(this);
         }
         if (newReservation != null)
         {
            this.reservation = newReservation;
            this.reservation.addTables(this);
         }
      }
   }

}
/***********************************************************************
 * Module:  Request.java
 * Author:  Bojan
 * Purpose: Defines the Class Request
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 2e37513c-0fe4-456a-ac1b-d6559703b56a */
public class Request {
   /** @pdOid 05415aad-38e9-4954-bb0f-18e122961a37 */
   public long idRequest;
   /** @pdOid 18dc195b-096b-47e3-bdc3-f9d9d2b57f2c */
   public java.lang.String status;
   
   /** @pdRoleInfo migr=no name=Customer assc=incoming mult=1..1 side=A */
   public Customer receiver;
   /** @pdRoleInfo migr=no name=Customer assc=outcoming mult=1..1 side=A */
   public Customer sender;
   
   
   /** @pdGenerated default parent getter */
   public Customer getReceiver() {
      return receiver;
   }
   
   /** @pdGenerated default parent setter
     * @param newCustomer */
   public void setReceiver(Customer newCustomer) {
      if (this.receiver == null || !this.receiver.equals(newCustomer))
      {
         if (this.receiver != null)
         {
            Customer oldCustomer = this.receiver;
            this.receiver = null;
            oldCustomer.removeIncomingRequests(this);
         }
         if (newCustomer != null)
         {
            this.receiver = newCustomer;
            this.receiver.addIncomingRequests(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Customer getSender() {
      return sender;
   }
   
   /** @pdGenerated default parent setter
     * @param newCustomer */
   public void setSender(Customer newCustomer) {
      if (this.sender == null || !this.sender.equals(newCustomer))
      {
         if (this.sender != null)
         {
            Customer oldCustomer = this.sender;
            this.sender = null;
            oldCustomer.removeOutcomingRequests(this);
         }
         if (newCustomer != null)
         {
            this.sender = newCustomer;
            this.sender.addOutcomingRequests(this);
         }
      }
   }

}
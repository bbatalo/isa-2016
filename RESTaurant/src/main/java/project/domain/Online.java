/***********************************************************************
 * Module:  Online.java
 * Author:  Bojan
 * Purpose: Defines the Class Online
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 79a48d20-83a1-4741-9e8d-6ef5c3f9b7b4 */
public class Online {
   /** @pdOid ee7b0c89-dc43-4774-9c67-a75c5c283671 */
   public long idOnline;
   
   /** @pdRoleInfo migr=no name=User assc=onlineUsers coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<User> users;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<User> getUsers() {
      if (users == null)
         users = new java.util.ArrayList<User>();
      return users;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorUsers() {
      if (users == null)
         users = new java.util.ArrayList<User>();
      return users.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newUsers */
   public void setUsers(java.util.Collection<User> newUsers) {
      removeAllUsers();
      for (java.util.Iterator iter = newUsers.iterator(); iter.hasNext();)
         addUsers((User)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newUser */
   public void addUsers(User newUser) {
      if (newUser == null)
         return;
      if (this.users == null)
         this.users = new java.util.ArrayList<User>();
      if (!this.users.contains(newUser))
         this.users.add(newUser);
   }
   
   /** @pdGenerated default remove
     * @param oldUser */
   public void removeUsers(User oldUser) {
      if (oldUser == null)
         return;
      if (this.users != null)
         if (this.users.contains(oldUser))
            this.users.remove(oldUser);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllUsers() {
      if (users != null)
         users.clear();
   }

}
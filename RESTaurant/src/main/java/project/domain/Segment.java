/***********************************************************************
 * Module:  Segment.java
 * Author:  Bojan
 * Purpose: Defines the Class Segment
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid b474dffa-88f6-4902-ae3a-099d6e080045 */
public class Segment {
   /** @pdOid 4f6555f2-2406-40ad-a3c6-6b80ab1dad5f */
   public long idSegment;
   /** @pdOid a5193c17-810e-43cc-a6f4-2b0c34f53614 */
   public java.lang.String label;
   /** @pdOid 3800c1cb-1954-4388-8b16-576900fe3546 */
   public java.lang.String description;
   
   /** @pdRoleInfo migr=no name=Table assc=tablesInSegment coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Table> tables;
   /** @pdRoleInfo migr=no name=SeatingArrangement assc=segments mult=0..1 side=A */
   public SeatingArrangement seating;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Table> getTables() {
      if (tables == null)
         tables = new java.util.ArrayList<Table>();
      return tables;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorTables() {
      if (tables == null)
         tables = new java.util.ArrayList<Table>();
      return tables.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newTables */
   public void setTables(java.util.Collection<Table> newTables) {
      removeAllTables();
      for (java.util.Iterator iter = newTables.iterator(); iter.hasNext();)
         addTables((Table)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newTable */
   public void addTables(Table newTable) {
      if (newTable == null)
         return;
      if (this.tables == null)
         this.tables = new java.util.ArrayList<Table>();
      if (!this.tables.contains(newTable))
         this.tables.add(newTable);
   }
   
   /** @pdGenerated default remove
     * @param oldTable */
   public void removeTables(Table oldTable) {
      if (oldTable == null)
         return;
      if (this.tables != null)
         if (this.tables.contains(oldTable))
            this.tables.remove(oldTable);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllTables() {
      if (tables != null)
         tables.clear();
   }
   /** @pdGenerated default parent getter */
   public SeatingArrangement getSeating() {
      return seating;
   }
   
   /** @pdGenerated default parent setter
     * @param newSeatingArrangement */
   public void setSeating(SeatingArrangement newSeatingArrangement) {
      if (this.seating == null || !this.seating.equals(newSeatingArrangement))
      {
         if (this.seating != null)
         {
            SeatingArrangement oldSeatingArrangement = this.seating;
            this.seating = null;
            oldSeatingArrangement.removeSegments(this);
         }
         if (newSeatingArrangement != null)
         {
            this.seating = newSeatingArrangement;
            this.seating.addSegments(this);
         }
      }
   }

}
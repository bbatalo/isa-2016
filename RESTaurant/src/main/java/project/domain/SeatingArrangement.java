/***********************************************************************
 * Module:  SeatingArrangement.java
 * Author:  Bojan
 * Purpose: Defines the Class SeatingArrangement
 ***********************************************************************/
package project.domain;

import java.util.*;

/** @pdOid 508d0db8-39ba-4f03-a595-674b4d90293b */
public class SeatingArrangement {
   /** @pdOid bb95d41c-c743-48f7-9020-a40d4a3a0b5e */
   public long idSeating;
   
   /** @pdRoleInfo migr=no name=Segment assc=segments coll=java.util.Collection impl=java.util.ArrayList mult=0..* */
   public java.util.Collection<Segment> segments;
   /** @pdRoleInfo migr=no name=Restaurant assc=seating mult=0..1 side=A */
   public Restaurant restaurant;
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Segment> getSegments() {
      if (segments == null)
         segments = new java.util.ArrayList<Segment>();
      return segments;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSegments() {
      if (segments == null)
         segments = new java.util.ArrayList<Segment>();
      return segments.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSegments */
   public void setSegments(java.util.Collection<Segment> newSegments) {
      removeAllSegments();
      for (java.util.Iterator iter = newSegments.iterator(); iter.hasNext();)
         addSegments((Segment)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newSegment */
   public void addSegments(Segment newSegment) {
      if (newSegment == null)
         return;
      if (this.segments == null)
         this.segments = new java.util.ArrayList<Segment>();
      if (!this.segments.contains(newSegment))
      {
         this.segments.add(newSegment);
         newSegment.setSeating(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldSegment */
   public void removeSegments(Segment oldSegment) {
      if (oldSegment == null)
         return;
      if (this.segments != null)
         if (this.segments.contains(oldSegment))
         {
            this.segments.remove(oldSegment);
            oldSegment.setSeating((SeatingArrangement)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSegments() {
      if (segments != null)
      {
         Segment oldSegment;
         for (java.util.Iterator iter = getIteratorSegments(); iter.hasNext();)
         {
            oldSegment = (Segment)iter.next();
            iter.remove();
            oldSegment.setSeating((SeatingArrangement)null);
         }
      }
   }

}
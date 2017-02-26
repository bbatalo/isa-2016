/***********************************************************************
 * Module:  Table.java
 * Author:  Bojan
 * Purpose: Defines the Class Table
 ***********************************************************************/
package project.domain;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RestTable {
	@Id
	@GeneratedValue
	@Column(name="table_id", nullable = false)
	public long idTable;
      
	@Column(name="table_code", nullable = false)
	public String tableCode;
	
	@Column(name="table_status", nullable = false)
	public String status;
	
	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	@Column(name="table_row", nullable = false)
	public int tableRow;
	
	@Column(name="table_col", nullable = false)
	public int tableCol;
	
	public int getTableRow() {
		return tableRow;
	}

	public void setTableRow(int tableRow) {
		this.tableRow = tableRow;
	}

	public int getTableCol() {
		return tableCol;
	}

	public void setTableCol(int tableCol) {
		this.tableCol = tableCol;
	}

	@ManyToOne
	@JoinColumn(name="segment_id")
	public Segment segment;
    
	public RestTable() {}
   //public Reservation reservation;

	public long getIdTable() {
		return idTable;
	}

	public void setIdTable(long idTable) {
		this.idTable = idTable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}
   
   /*
   public Reservation getReservation() {
      return reservation;
   }
   
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
   */
}
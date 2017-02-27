package project.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RestOrder {

	@Id
	@GeneratedValue
	@Column(name = "ord_id", nullable = false)
    private long id;

	//da budem iskren, ni ne secam se zasto je ovo ovde, vidi da li sluzi icemu
	@Column(name = "ord_time", nullable = true)
    private Date time;

	@Column(name = "ord_status", nullable = false)
    private String status;

	@OneToMany(mappedBy="order")
	@JsonIgnore
    private List<DishOrder> dishOrders;

	@OneToMany(mappedBy="order")
	@JsonIgnore
    private List<DrinkOrder> drinkOrders;
    
    @ManyToOne(optional = false)
	@JoinColumn(name="ord_reservation",  referencedColumnName="res_id")
    private Reservation reservation;

    //ovo sam izbacio jer je mozda suvisno, ostvaruje se posredna veza preko rezervacije
    /*
    @ManyToOne(optional=false)
	@JoinColumn(name="ord_restaurant",  referencedColumnName="usr_id")
    private Restaurant restaurant;
	*/
    
    @ManyToOne(optional = false)
	@JoinColumn(name="ord_customer",  referencedColumnName="usr_id")
    private Customer customer;
   
    public RestOrder() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DishOrder> getDishOrders() {
		return dishOrders;
	}

	public void setDishOrders(List<DishOrder> dishOrders) {
		this.dishOrders = dishOrders;
	}

	public List<DrinkOrder> getDrinkOrders() {
		return drinkOrders;
	}

	public void setDrinkOrders(List<DrinkOrder> drinkOrders) {
		this.drinkOrders = drinkOrders;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    
   
}
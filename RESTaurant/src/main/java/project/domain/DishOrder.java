package project.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DishOrder {

	@Id
	@GeneratedValue
	@Column(name = "odr_id", nullable = false)
    private Long id;

	@Column(name = "odr_status", nullable = false)
    private String status;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="ChefsOnDuty",
	joinColumns=@JoinColumn(name="odr_id"),
	inverseJoinColumns=@JoinColumn(name="usr_id"))
    private Set<Chef> chef;

	@ManyToOne(optional = false)
	@JoinColumn(name="odr_dish",  referencedColumnName="dish_id")
    private Dish dish;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="odr_order",  referencedColumnName="ord_id")
	private RestOrder order;
	
	public DishOrder() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Chef> getChef() {
		return chef;
	}

	public void setChef(Set<Chef> chef) {
		this.chef = chef;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	public void setRatings(int rating){
		
	}

	public RestOrder getOrder() {
		return order;
	}

	public void setOrder(RestOrder order) {
		this.order = order;
	}
	
	
   
}
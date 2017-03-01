package project.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Chef extends Employee {

	
	@Column(name = "chf_type", nullable = false)
    private DishType type;

	protected Chef() {}
	
	public DishType getType() {
		return type;
	}

	public void setType(DishType type) {
		this.type = type;
	}

	
   /*
   public java.util.Collection<ChefRating> ratings;
   
   public java.util.Collection<Shift> shifts;
   
   */
	
	
	@ManyToMany(mappedBy="chef")
	@JsonIgnore
    private List<DishOrder> dishOrders;

	public List<DishOrder> getDishOrders() {
		return dishOrders;
	}

	public void setDishOrders(List<DishOrder> dishOrders) {
		this.dishOrders = dishOrders;
	}
	
	public void addRating(int rating){
		
	}
   
    
}
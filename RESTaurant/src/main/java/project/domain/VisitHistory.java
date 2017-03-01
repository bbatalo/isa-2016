package project.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VisitHistory {
	
	@Id
	@GeneratedValue
	@Column(name="history_id", nullable = false)
    private Long id;
   
	@OneToMany(mappedBy = "history")
	@JsonIgnore
    private List<Visit> visits;
   
	@OneToOne
	@JoinColumn(name = "usr_id")
	@JsonIgnore
    private Customer customer;
   
	public VisitHistory() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
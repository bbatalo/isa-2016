package project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Visit {

	@Id
	@GeneratedValue
	@Column(name="visit_id", nullable = false)
    private Long id;

    //public Rating rating;

	@ManyToOne
	@JoinColumn(name="history_id")
    private VisitHistory history;

	@OneToOne
	@JoinColumn(name="ord_id")
    private RestOrder order;

    //public Invite invite;
   
    public Visit() {}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VisitHistory getHistory() {
		return history;
	}

	public void setHistory(VisitHistory history) {
		this.history = history;
	}

	public RestOrder getOrder() {
		return order;
	}
	
	public void setOrder(RestOrder order) {
		this.order = order;
	}
   
   
   
}
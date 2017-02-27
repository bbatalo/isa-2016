package project.domain.dto;

public class OfferAcceptedDTO {
	public Long receiverID;
	
	public String managerName;
	
	public String ManagerSurname;
	
	public String restaurantName;
	
	public OfferAcceptedDTO() {}

	public Long getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(Long receiverID) {
		this.receiverID = receiverID;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerSurname() {
		return ManagerSurname;
	}

	public void setManagerSurname(String managerSurname) {
		ManagerSurname = managerSurname;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
}

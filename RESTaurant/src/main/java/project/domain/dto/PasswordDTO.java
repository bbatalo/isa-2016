package project.domain.dto;

import project.domain.Customer;

public class PasswordDTO {

	private Long userID;
	
	private String currentPass;
	
	private String newPass;
	
	public PasswordDTO() {}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getCurrentPass() {
		return currentPass;
	}

	public void setCurrentPass(String currentPass) {
		this.currentPass = currentPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	public Customer generateCustomer() {
		Customer ret = new Customer();
		ret.setUserID(userID);
		ret.setPassword(newPass);
		return ret;
	}
}

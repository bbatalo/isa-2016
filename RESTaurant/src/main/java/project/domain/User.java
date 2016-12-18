package project.domain;

import java.io.Serializable;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity
@Inheritance(strategy = JOINED)
public class User implements Serializable {

	private static final long serialVersionUID = -8458636721325624891L;

	@Id
	@GeneratedValue
	@Column(name = "usr_id", nullable = false)
	private Long userID;
	
	@Column(name = "usr_email", nullable = false)
	private String email;
	
	@Column(name = "usr_password", nullable = false)
	private String password;
	
	@Column(name = "usr_type", nullable = false)
	private UserType userType;
	
	protected User() {}
	
	public Long getIdUser() {
		return userID;
	}

	public void setIdUser(Long id_user) {
		this.userID = id_user;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [idUser=" + userID + ", email=" + email + ", password=" + password + ", userType=" + userType
				+ "]";
	}

	
	
	
}

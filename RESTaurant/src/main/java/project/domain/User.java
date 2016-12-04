package project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long idUser;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;

	protected User() {}
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long id_user) {
		this.idUser = id_user;
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

	@Override
	public String toString() {
		return "User [getId_user()=" + getIdUser() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword()
				+ "]";
	}
	
	
}

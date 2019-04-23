package fi.haagahelia.OnlineStore.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * An object for SignupForm.
 * 
 * @author aburkova
 */
public class SignupForm {
	@NotEmpty
	@Size(min = 5, max = 30)
	private String username = "";

	@NotEmpty
	@Size(min = 7, max = 30)
	private String password = "";

	@NotEmpty
	@Size(min = 7, max = 30)
	private String passwordCheck = "";

	@NotEmpty
	private String role = "USER";

	/**
	 * Gives new user name.
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets new user name.
	 * 
	 * @param username user name
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gives new user password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets new user password.
	 * 
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gives new password check validation.
	 * 
	 * @return passwordCheck
	 */
	public String getPasswordCheck() {
		return passwordCheck;
	}

	/**
	 * Sets new password check validation.
	 * 
	 * @param passwordCheck password check
	 */
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	/**
	 * Gives user role (USER).
	 * 
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets user role (USER).
	 * 
	 * @param role role
	 */
	public void setRole(String role) {
		this.role = role;
	}
}

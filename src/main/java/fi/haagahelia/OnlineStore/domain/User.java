package fi.haagahelia.OnlineStore.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * An object for User.
 * 
 * @author aburkova
 */
@Document(collection = "user_db")
public class User {
	@Id
	private String id;
	private String username;
	private String passwordHash;
	private String role;

	/**
	 * Sets empty User object.
	 */
	public User() {
	}

	/**
	 * Sets parameters for User object.
	 * 
	 * @param username
	 * @param passwordHash
	 * @param role
	 */
	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	/**
	 * Gives user id.
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets user id.
	 * 
	 * @param id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gives user name.
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets user name.
	 * 
	 * @param username user name
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gives password hash.
	 * 
	 * @return passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * Sets password hash.
	 * 
	 * @param passwordHash password hash
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * Gives user role.
	 * 
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets user role.
	 * 
	 * @param role role
	 */
	public void setRole(String role) {
		this.role = role;
	}
}

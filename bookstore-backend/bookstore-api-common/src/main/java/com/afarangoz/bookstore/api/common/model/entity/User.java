package com.afarangoz.bookstore.api.common.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.afarangoz.bookstore.api.common.model.dto.UserDTO;

/**
 * The Class User.
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8395237794000304963L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The username. */
	@Column(unique = true, length = 20)
	private String username;

	/** The password. */
	@Column(length = 60)
	private String password;

	/** The is enable. */
	private Boolean isEnable;

	/** The full name. */
	private String fullName;

	/** The email. */
	@Column(unique = true, length = 100)
	private String email;

	/** The roles. */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "user_id", "role_id" }) })
	private List<Role> roles;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		super();
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param userDTO the user DTO
	 */
	public User(UserDTO userDTO) {
		super();
		this.id = userDTO.getId();
		this.email = userDTO.getEmail();
		this.fullName = userDTO.getFullName();
		this.username = userDTO.getUsername();
	}

	/**
	 * Pre persist.
	 */
	@PrePersist
	public void prePersist() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String bcryptPassword = passwordEncoder.encode(this.getPassword());
		this.setPassword(bcryptPassword);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the checks if is enable.
	 *
	 * @return the checks if is enable
	 */
	public Boolean getIsEnable() {
		return isEnable;
	}

	/**
	 * Sets the checks if is enable.
	 *
	 * @param isEnable the new checks if is enable
	 */
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}

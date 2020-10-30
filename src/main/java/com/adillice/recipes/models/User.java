package com.adillice.recipes.models;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	//add one to many for recipes
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Recipe> recipes;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="First name is required.")
	@Size(min=2, message="First name must be at least 2 characters long.")
	private String firstName;
	
	@NotEmpty(message="Last name is required.")
	@Size(min=2, message="Last name must be at least 2 characters long.")
	private String lastName;
	
	@NotEmpty(message="User name is required.")
	@Size(min=2, message="User name must be at least 2 characters long.")
	private String username;
	
	@NotEmpty(message="Email is required.")
	@Email(message="The email is invalid.")
	private String email;
	
	@NotEmpty(message="Password is required.")
	@Size(min=8, message="Password must be at least 8 charcters long.")
	private String password;
	
	@Transient
	@NotEmpty(message="Confirming the password is required.")
	private String confirm;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	//getters/setters
	public List<Recipe> getRecipes() {
		return recipes;
	}	
	
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	@PrePersist
	protected void onCreate(){
	     this.createdAt=new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
	     this.updatedAt=new Date();
	}

}

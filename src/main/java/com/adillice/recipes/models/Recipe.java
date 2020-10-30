package com.adillice.recipes.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="Recipes")
public class Recipe {
	
	//add many to many for recipes
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(
	     name="recipes_categories",
	     joinColumns=@JoinColumn(name="recipe_id"), 
	     inverseJoinColumns=@JoinColumn(name="category_id")
	)
	private List<Category> categorys;
	
	
	//many to one relationship
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="The title is required.")
	@Size(min=2, message="The title needs to be at least 2 characters.")
	private String title;
	
	private String image;
	
	@NotEmpty(message="The category is required.")
	@Size(min=3, message="The category needs to be at least 3 characters.")
	@Transient
	private String categories;
	
	@Transient
	private MultipartFile file;
	
	@NotEmpty(message="The time is required.")
	@Size(min=2, message="The time needs to be at least 2 characters.")	
	private String time;
	
	@NotNull(message="The yield is required.")
	@Min(value=1, message="The yield should not be less than 1.")
	private Integer yield;
	
	@NotEmpty(message="The summary is required.")
	@Size(min=10, max=250, message="The summary needs to be at least 10 characters and no more than 250 characters.")
	private String summary;
	
	@Column(columnDefinition="TEXT")
	@NotEmpty(message="The ingredients are required.")
	@Size(min=2, message="Ingredients need to be at least 10 characters.")
	private String ingredients;
	
	@Column(columnDefinition="TEXT")
	@NotEmpty(message="The directions are required.")
	@Size(min=2, message="Directions need to be at least 10 characters.")	
	private String directions;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	//getter/setters
	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getYield() {
		return yield;
	}

	public void setYield(Integer yield) {
		this.yield = yield;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDate() {
		
		//use simple date format to format our time
		SimpleDateFormat timeFrame = new SimpleDateFormat("MMM dd, yyyy");
		
		//setup string to format our created time
		String readableTime = (String) timeFrame.format(this.createdAt);
		
		//return 
		return readableTime;
		
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

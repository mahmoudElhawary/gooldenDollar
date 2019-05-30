package com.egypt.daily.life.shopping.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User  implements Serializable{

	private static final long serialVersionUID = 2387334187371552191L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id ;
	private String firstName ;
	private String lastName ;
	@Column(unique= true)
	private String email ;
	private String gender ;
	private String password ;
	private boolean enabled ;
	private String role  ;
	private Date createdDate ;
	private Date updatedDate ;
	
	@OneToOne
    @JoinColumn(name = "cartId")
    @JsonIgnore
    private Cart cart;
    
    @OneToMany(mappedBy = "user")
    private List<ProductComment> productComments;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ShippingAddress> shippingAddresses;
    
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public List<ProductComment> getProductComments() {
		return productComments;
	}
	public void setProductComments(List<ProductComment> productComments) {
		this.productComments = productComments;
	}
	public List<ShippingAddress> getShippingAddresses() {
		return shippingAddresses;
	}
	public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}

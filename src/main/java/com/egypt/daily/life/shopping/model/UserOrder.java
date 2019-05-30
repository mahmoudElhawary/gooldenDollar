package com.egypt.daily.life.shopping.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserOrder implements Serializable{

    private static final long serialVersionUID = 584092333516618686L;

    @Id
    @GeneratedValue
    private Long userOrderId;

    private double orderTotalPrice;
    
    @Column(columnDefinition="DATETIME")
    private Date orderDate;
    
    @OneToOne
    @JoinColumn(name = "addressId")
    @JsonIgnore
    private UserOrderShippingAddress userOrderShippingAddress;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserOrderItem> userOrderItems;

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

	public Long getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(Long userOrderId) {
		this.userOrderId = userOrderId;
	}

	public UserOrderShippingAddress getUserOrderShippingAddress() {
		return userOrderShippingAddress;
	}

	public void setUserOrderShippingAddress(UserOrderShippingAddress userOrderShippingAddress) {
		this.userOrderShippingAddress = userOrderShippingAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserOrderItem> getUserOrderItems() {
		return userOrderItems;
	}

	public void setUserOrderItems(List<UserOrderItem> userOrderItems) {
		this.userOrderItems = userOrderItems;
	}

    
}

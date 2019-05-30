package com.egypt.daily.life.shopping.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserOrderItem implements Serializable{

    private static final long serialVersionUID = 8892553931694642183L;

    @Id
    @GeneratedValue
    private Long userOrderItemId;

    private Long productId;
    private int productQuantity;
    private double productPrice;
    private String productName;

    @ManyToOne
    @JoinColumn(name = "userOrderId")
    private UserOrder userOrder;
    

    public Long getUserOrderItemId() {
		return userOrderItemId;
	}

	public void setUserOrderItemId(Long userOrderItemId) {
		this.userOrderItemId = userOrderItemId;
	}

	public UserOrder getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}

	public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

	package com.egypt.daily.life.shopping.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Product implements Serializable, Comparable<Product>{

    private static final long serialVersionUID = -6989243970039135205L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId; // MySQL only accept Long or integer ID, can't be String type
    @Column(unique=true , name="productName")
    @OrderBy("productName asc")
    private String productName;
    @Column(unique=true , name="coupon")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coupon;
    
    private String size;
    
    private String defaultSize ;
    
    private String color;
    
    private String quantity; 
    
    @Lob
    @Basic(fetch=FetchType.EAGER)
    private byte[]  productPhoto ;
    
    private String productPhotoName; 
    
    private Long rating;  
    
    private Long view;  
    
    private Long sellCount ;
    
    private Boolean isSlider ;
    
    private String productSummary;
        
    private String productDescription;
    
    private Date productDate;
    
    @Column(name = "productViews", nullable = false, columnDefinition = "bigint(20) default 0")
    private long productViews = 0;

    @Min(value = 0, message = "Product price must no be less then zero.")
    private double productPrice;
    
    private String productCondition;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartItem> cartItemList;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ProductComment> productCommentList;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category productCategory;

    /******************* Comparator ************************/
    @Override
	public int compareTo(Product product){
		return new Double(this.getProductPrice()).compareTo(product.getProductPrice());
	}
    
    public static class Comparators {
    	public static Comparator<Product> PRICE = new Comparator<Product>(){
    		@Override
    		public int compare(Product p1, Product p2){
    			return new Double(p1.getProductPrice()).compareTo(p2.getProductPrice());
    		}
    	};
    	public static Comparator<Product> VIEWS = new Comparator<Product>(){
    		@Override
    		public int compare(Product p1, Product p2){
    			return new Long(p1.getProductViews()).compareTo(p2.getProductViews());
    		}
    	};
    	public static Comparator<Product> DATE = new Comparator<Product>(){
    		@Override
    		public int compare(Product p1, Product p2){
    			return (p1.getProductDate().after(p2.getProductDate())) ? 0:1;
    		}
    	};
    }
    /******************* Getter/Setter ************************/
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getDefaultSize() {
		return defaultSize;
	}

	public void setDefaultSize(String defaultSize) {
		this.defaultSize = defaultSize;
	}

	public String getProductSummary() {
		return productSummary;
	}

	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public long getProductViews() {
		return productViews;
	}

	public void setProductViews(long productViews) {
		this.productViews = productViews;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductCondition() {
		return productCondition;
	}

	public void setProductCondition(String productCondition) {
		this.productCondition = productCondition;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public List<ProductComment> getProductCommentList() {
		return productCommentList;
	}

	public void setProductCommentList(List<ProductComment> productCommentList) {
		this.productCommentList = productCommentList;
	}

	public Category getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}

	public Long getCoupon() {
		return coupon;
	}

	public void setCoupon(Long coupon) {
		this.coupon = coupon;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Long getRating() {
		return rating;
	}
	
	public void setRating(Long rating) {
		this.rating = rating;
	}

	public byte[] getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}

	public String getProductPhotoName() {
		return productPhotoName;
	}

	public void setProductPhotoName(String productPhotoName) {
		this.productPhotoName = productPhotoName;
	}
	
}

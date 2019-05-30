package com.egypt.daily.life.shopping.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductComment implements Serializable {

    private static final long serialVersionUID = 2586167050875661578L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(columnDefinition = "TEXT")
    private String commentCotents;

    private int commentStarts;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(columnDefinition="DATETIME")
    private Date commentDate;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getCommentCotents() {
		return commentCotents;
	}

	public void setCommentCotents(String commentCotents) {
		this.commentCotents = commentCotents;
	}

	public int getCommentStarts() {
		return commentStarts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCommentStarts(int commentStarts) {
		this.commentStarts = commentStarts;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

   
}

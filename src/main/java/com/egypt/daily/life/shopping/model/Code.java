package com.egypt.daily.life.shopping.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Code {
	
	@Id
    @GeneratedValue
    private Long codeId;
	
	private String codeStr;
	private int codeType; // 0:active, 1: reset PW
	
	@Column(columnDefinition="DATETIME")
	private Date codeDate;
	
	@ManyToOne
    private User user;

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public String getCodeStr() {
		return codeStr;
	}

	public void setCodeStr(String codeStr) {
		this.codeStr = codeStr;
	}

	public int getCodeType() {
		return codeType;
	}

	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}

	public Date getCodeDate() {
		return codeDate;
	}

	public void setCodeDate(Date codeDate) {
		this.codeDate = codeDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	
}

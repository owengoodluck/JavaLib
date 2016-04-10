package com.owen.wms.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UPC")
public class UPC {
	@Id
	private String code;
	
	private String status;//NEW, USED
	
	public UPC(){
		super(); 
	}
	
	public UPC(String code, String status) {
		super();
		this.code = code;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

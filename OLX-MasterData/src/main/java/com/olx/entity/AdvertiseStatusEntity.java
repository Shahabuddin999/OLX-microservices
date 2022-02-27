package com.olx.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="advertisestatus")
public class AdvertiseStatusEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AdvertiseStatusEntity(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public AdvertiseStatusEntity() {
		super();
	}
	@Override
	public String toString() {
		return "AdvertiseStatus [id=" + id + ", status=" + status + "]";
	}
}

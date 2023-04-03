package com.wipro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="addraug30")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int aid;
	
	int hno;
	String street, city, state, country;
	
	public int getHno() {
		return hno;
	}
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public void setHno(int hno) {
		this.hno = hno;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(int hno, String street, String city, String state, String country) {
		super();
		this.hno = hno;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
}

package com.wipro;

public class Address {
int hno;
String city, state, country;

public Address(int hno, String city, String state, String country) {
	System.out.println("Employee arg constructor is called");
	this.hno = hno;
	this.city = city;
	this.state = state;
	this.country = country;
}

public int getHno() {
	return hno;
}
public void setHno(int hno) {
	this.hno = hno;
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

@Override
public String toString() {
	return "Address [hno=" + hno + ", city=" + city + ", state=" + state + ", country=" + country + "]";
}
}

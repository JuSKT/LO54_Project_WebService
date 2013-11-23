package com.lo54project.webservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/** Class which manage session locations */
@XmlRootElement
@Entity
@Table(name = "location")
public class Location {
	// Properties
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false, length = 11)
	private int id;

	@Column(name = "city", nullable = false, length = 45)
	private String city;

	/** Default constructor */
	public Location() {
		super();
	}

	/**
	 * Specific constructor
	 * 
	 * @param id
	 * @param city
	 */
	public Location(int id, String city) {
		super();
		this.id = id;
		this.city = city;
	}

	public Location(String city) {
		super();
		this.city = city;
	}

	/**
	 * Return location's id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set location's id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return location's name
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set location's name
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/** Redefining the method toString */
	@Override
	public String toString() {
		return "Location [id=" + id + ", city=" + city + "]";
	}

}

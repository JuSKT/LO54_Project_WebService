package com.lo54project.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

/** Class which manage session locations */
@XmlRootElement
public class Location 
{
	// Properties
	private int id;
	private String city;
	
	/** Default constructor */
	public Location() 
	{
		super();
	}
	
	/** Specific constructor
	 * 
	 * @param id
	 * @param city
	 */
	public Location(int id, String city) 
	{
		super();
		this.id = id;
		this.city = city;
	}

	/** Return location's id
	 * 
	 * @return id
	 */
	public int getId() 
	{
		return id;
	}

	/** Set location's id
	 * 
	 * @param id
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	/** Return location's name
	 * 
	 * @return city
	 */
	public String getCity() 
	{
		return city;
	}

	/** Set location's name
	 * 
	 * @param city
	 */
	public void setCity(String city) 
	{
		this.city = city;
	}

	/** Redefining the method toString */
	@Override
	public String toString() 
	{
		return "Location [id=" + id + ", city=" + city + "]";
	}

}

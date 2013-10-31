package com.lo54project.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

/** Class which manage clients */
@XmlRootElement
public class Client 
{
	// Properties
	private int id;
	private String lastname;
	private String firstname;
	private String address;
	private String phone;
	private String email;
	private CourseSession crss;
	
	/** Default constructor */
	public Client() 
	{
		super();
	}
	
	/** Specific constructor 
	 * 
	 * @param id
	 * @param lastname
	 * @param firstname
	 * @param address
	 * @param phone
	 * @param email
	 */
	public Client(int id, String lastname, String firstname, String address, String phone, String email) 
	{
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	/** Return client's id
	 * 
	 * @return id
	 */
	public int getId() 
	{
		return id;
	}

	/** Set client's id
	 * 
	 * @param id
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	/** Return client's last name
	 * 
	 * @return lastname
	 */
	public String getLastname() 
	{
		return lastname;
	}

	/** Set client's last name
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}

	/** Return client's first name
	 * 
	 * @return firstname
	 */
	public String getFirstname() 
	{
		return firstname;
	}

	/** Set client's first name
	 * 
	 * @param firstname
	 */
	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	/** Return client's address
	 * 
	 * @return address
	 */
	public String getAddress() 
	{
		return address;
	}

	/** Set client's address
	 * 
	 * @param address
	 */
	public void setAddress(String address) 
	{
		this.address = address;
	}

	/** Return client's phone number
	 * 
	 * @return phone
	 */
	public String getPhone() 
	{
		return phone;
	}
	
	/** Set client's phone number
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	/** Return client's email
	 * 
	 * @return email
	 */
	public String getEmail() 
	{
		return email;
	}

	/** Set client's email
	 * 
	 * @param email
	 */
	public void setEmail(String email) 
	{
		this.email = email;
	}

	/** Return client's course session
	 * 
	 * @return course_session
	 */
	public CourseSession getCrss() 
	{
		return crss;
	}

	/** Set client's course session
	 * 
	 * @param crss
	 */
	public void setCrss(CourseSession crss) 
	{
		this.crss = crss;
	}

	/** Redefining the method toString */
	@Override
	public String toString() 
	{
		return "Client [id=" + id + ", lastname=" + lastname + ", firstname="
				+ firstname + ", address=" + address + ", phone=" + phone
				+ ", email=" + email + "]";
	}
}
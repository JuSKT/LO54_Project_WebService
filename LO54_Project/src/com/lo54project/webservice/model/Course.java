package com.lo54project.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

/** Class which manage courses */
@XmlRootElement
public class Course 
{
	// Properties
	private String code;
	private String title;
	
	/** Default constructor */
	public Course() 
	{
		super();
	}
	
	/** Specific constructor
	 * 
	 * @param code
	 * @param title
	 */
	public Course(String code, String title) 
	{
		super();
		this.code = code;
		this.title = title;
	}
	
	/** Return course's code
	 * 
	 * @return code
	 */
	public String getCode() 
	{
		return code;
	}

	/** Set course's code
	 * 
	 * @param code
	 */
	public void setCode(String code) 
	{
		this.code = code;
	}
	
	/** Return course's title
	 * 
	 * @return title
	 */
	public String getTitle() 
	{
		return title;
	}

	/** Set course's title
	 * 
	 * @param title
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}

	/** Redefining the method toString */
	@Override
	public String toString() 
	{
		return "Course [code=" + code + ", title=" + title + "]";
	}
}
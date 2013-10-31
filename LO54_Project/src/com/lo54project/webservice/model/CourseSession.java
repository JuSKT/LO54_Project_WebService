package com.lo54project.webservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/** Class which manage session courses */
@XmlRootElement
public class CourseSession 
{
	// Properties
	private int id;
	private Date start;
	private Date end;
	private Course crs;
	private Location loc;
	
	/** Default constructor */
	public CourseSession() 
	{
		super();
	}
	
	/** Specific constructor
	 * 
	 * @param id
	 * @param start
	 * @param end
	 */
	public CourseSession(int id, Date start, Date end) 
	{
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	}

	/** Return course session's id
	 * 
	 * @return id
	 */
	public int getId() 
	{
		return id;
	}

	/** Set course session's id
	 * 
	 * @param id
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	/** Return course session's start time
	 * 
	 * @return start
	 */
	public Date getStart() 
	{
		return start;
	}

	/** Set course session's start time
	 * 
	 * @param start
	 */
	public void setStart(Date start) 
	{
		this.start = start;
	}

	/** Return course session's end time
	 * 
	 * @return end
	 */
	public Date getEnd() 
	{
		return end;
	}

	/** Set course session's end time
	 * 
	 * @param end
	 */
	public void setEnd(Date end) 
	{
		this.end = end;
	}

	/** Return course session's course
	 * 
	 * @return crs
	 */
	public Course getCrs() 
	{
		return crs;
	}

	/** Set course session's course
	 * 
	 * @param crs
	 */
	public void setCrs(Course crs) 
	{
		this.crs = crs;
	}

	/** Return course session's location
	 * 
	 * @return loc
	 */
	public Location getLoc() 
	{
		return loc;
	}

	/** Set course session's location
	 * 
	 * @param loc
	 */
	public void setLoc(Location loc) 
	{
		this.loc = loc;
	}

	/** Redefining the method toString */
	@Override
	public String toString() 
	{
		return "CourseSession [id=" + id + ", start=" + start + ", end=" + end
				+ "]";
	}
}
package com.lo54project.webservice.model;

import java.util.Date;

public class CourseSession {
	
	private int id;
	private Date start;
	private Date end;
	
	private Course crs;
	private Location loc;
	
	public CourseSession() {
		super();
	}
	
	public CourseSession(int id, Date start, Date end) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Course getCrs() {
		return crs;
	}

	public void setCrs(Course crs) {
		this.crs = crs;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "CourseSession [id=" + id + ", start=" + start + ", end=" + end
				+ "]";
	}

}

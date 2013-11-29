package com.lo54project.webservice.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonRootName;

/** Class which manage session courses */
@XmlRootElement
@Entity
@Table(name = "course_session")
public class CourseSession {
	// Properties
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false, length = 11)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start", nullable = false)
	private Date start;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end", nullable = false)
	private Date end;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "course_code", nullable = false)
	
//	@ManyToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name="course_code", 
//                insertable=false, updatable=false, 
//                nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_code", referencedColumnName = "code", nullable = false)
	private Course crs;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
	private Location loc;

	/** Default constructor */
	public CourseSession() {
		super();
	}

	public CourseSession(int id, Date start, Date end, Course crs, Location loc) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.crs = crs;
		this.loc = loc;
	}

	/**
	 * Specific constructor
	 * 
	 * @param id
	 * @param start
	 * @param end
	 */
	public CourseSession(int id, Date start, Date end) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	}

	public CourseSession(Date start, Date end) {
		super();
		this.start = start;
		this.end = end;
	}

	/**
	 * Return course session's id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set course session's id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return course session's start time
	 * 
	 * @return start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * Set course session's start time
	 * 
	 * @param start
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * Return course session's end time
	 * 
	 * @return end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * Set course session's end time
	 * 
	 * @param end
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * Return course session's course
	 * 
	 * @return crs
	 */
	public Course getCrs() {
		return crs;
	}

	/**
	 * Set course session's course
	 * 
	 * @param crs
	 */
	public void setCrs(Course crs) {
		this.crs = crs;
	}

	/**
	 * Return course session's location
	 * 
	 * @return loc
	 */
	public Location getLoc() {
		return loc;
	}

	/**
	 * Set course session's location
	 * 
	 * @param loc
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "CourseSession [id=" + id + ", start=" + start + ", end=" + end
				+ ", crs=" + crs + ", loc=" + loc + "]";
	}
}

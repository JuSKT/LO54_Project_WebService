package com.lo54project.webservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/** Class which manage courses */
@XmlRootElement
@Entity
@Table(name = "course")
public class Course {
	// Properties
	@Id
	@Column(name = "code", unique = true, nullable = false, length = 45)
	private String code;

	@Column(name = "title", nullable = false, length = 45)
	private String title;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="crs")
	private List<CourseSession> courseSessions;

	/** Default constructor */
	public Course() {
		super();
	}

	/**
	 * Specific constructor
	 * 
	 * @param code
	 * @param title
	 */
	public Course(String code, String title) {
		super();
		this.code = code;
		this.title = title;
		this.courseSessions = new ArrayList<>();
	}

	/**
	 * Return course's code
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Set course's code
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Return course's title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set course's title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * Get courseSessions
	 * 
	 * @return courseSessions
	 */
	public List<CourseSession> getCourseSessions() {
		return courseSessions;
	}

	/**
	 * Set list of courseSessions
	 * 
	 * @param courseSessions
	 */
	public void setCourseSessions(List<CourseSession> courseSessions) {
		this.courseSessions = courseSessions;
	}

	/** Redefining the method toString */
	@Override
	public String toString() {
		return "Course [code=" + code + ", title=" + title + "]";
	}
}
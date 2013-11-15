package com.lo54project.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

	/** Redefining the method toString */
	@Override
	public String toString() {
		return "Course [code=" + code + ", title=" + title + "]";
	}
}
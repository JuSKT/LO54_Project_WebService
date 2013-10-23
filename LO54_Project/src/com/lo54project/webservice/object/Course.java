package com.lo54project.webservice.object;

public class Course {
	
	private String code;
	private String title;
	
	public Course() {
		super();
	}
	
	public Course(String code, String title) {
		super();
		this.code = code;
		this.title = title;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Course [code=" + code + ", title=" + title + "]";
	}
	
}

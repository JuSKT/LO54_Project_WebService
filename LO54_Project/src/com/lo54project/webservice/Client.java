package com.lo54project.webservice;

public class Client {
	
	private int id;
	private String lastname;
	private String firstname;
	private String address;
	private String phone;
	private String email;
	
	private CourseSession crss;
	
	public Client() {
		super();
	}
	
	public Client(int id, String lastname, String firstname, String address,
			String phone, String email) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", lastname=" + lastname + ", firstname="
				+ firstname + ", address=" + address + ", phone=" + phone
				+ ", email=" + email + "]";
	}
	
}

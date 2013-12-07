package com.lo54project.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/** Class which manage clients */
@XmlRootElement
@Entity
@Table(name = "client")
public class Client {
        // Properties
        @Id
        @GeneratedValue
        @Column(name = "id", unique = true, nullable = false, length = 11)
        private int id;

        @Column(name = "lastname", nullable = false, length = 45)
        private String lastname;

        @Column(name = "firstname", nullable = false, length = 45)
        private String firstname;

        @Column(name = "address", nullable = false, length = 45)
        private String address;

        @Column(name = "phone", nullable = false, length = 45)
        private String phone;

        @Column(name = "email", nullable = false, length = 45)
        private String email;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "session_id", referencedColumnName = "id", nullable = false)
        private CourseSession crss;

        /** Default constructor */
        public Client() {
                super();
        }

        /**
         * Specific constructor
         * 
         * @param id
         * @param lastname
         * @param firstname
         * @param address
         * @param phone
         * @param email
         */
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

        /**
         * Specific constructor
         * 
         * @param lastname
         * @param firstname
         * @param address
         * @param phone
         * @param email
         */
        public Client(String lastname, String firstname, String address,
                        String phone, String email) {
                super();
                this.lastname = lastname;
                this.firstname = firstname;
                this.address = address;
                this.phone = phone;
                this.email = email;
        }

        /**
         * Return client's id
         * 
         * @return id
         */
        public int getId() {
                return id;
        }

        /**
         * Set client's id
         * 
         * @param id
         */
        public void setId(int id) {
                this.id = id;
        }

        /**
         * Return client's last name
         * 
         * @return lastname
         */
        public String getLastname() {
                return lastname;
        }

        /**
         * Set client's last name
         * 
         * @param lastname
         */
        public void setLastname(String lastname) {
                this.lastname = lastname;
        }

        /**
         * Return client's first name
         * 
         * @return firstname
         */
        public String getFirstname() {
                return firstname;
        }

        /**
         * Set client's first name
         * 
         * @param firstname
         */
        public void setFirstname(String firstname) {
                this.firstname = firstname;
        }

        /**
         * Return client's address
         * 
         * @return address
         */
        public String getAddress() {
                return address;
        }

        /**
         * Set client's address
         * 
         * @param address
         */
        public void setAddress(String address) {
                this.address = address;
        }

        /**
         * Return client's phone number
         * 
         * @return phone
         */
        public String getPhone() {
                return phone;
        }

        /**
         * Set client's phone number
         * 
         * @param phone
         */
        public void setPhone(String phone) {
                this.phone = phone;
        }

        /**
         * Return client's email
         * 
         * @return email
         */
        public String getEmail() {
                return email;
        }

        /**
         * Set client's email
         * 
         * @param email
         */
        public void setEmail(String email) {
                this.email = email;
        }

        /**
         * Return client's course session
         * 
         * @return course_session
         */
        public CourseSession getCrss() {
                return crss;
        }

        /**
         * Set client's course session
         * 
         * @param crss
         */
        public void setCrss(CourseSession crss) {
                this.crss = crss;
        }

        /** Redefining the method toString */
		@Override
		public String toString() {
			return "Client [id=" + id + ", lastname=" + lastname
					+ ", firstname=" + firstname + ", address=" + address
					+ ", phone=" + phone + ", email=" + email + ", crss="
					+ crss + "]";
		}

}
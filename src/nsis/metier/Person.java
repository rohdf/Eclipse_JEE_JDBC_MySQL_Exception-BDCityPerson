package nsis.metier;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	long   id_person;
	long   id_city;
	String firstname;
	String lastname;
	String emails;
	String phone;
	
	public Person(long id_city, String firstname, String lastname, String emails, String phone) {
		super();
		this.id_city = id_city;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emails = emails;
		this.phone = phone;
	}
	
	

	public Person(long id_person, long id_city, String firstname, String lastname, String emails, String phone) {
		super();
		this.id_person = id_person;
		this.id_city = id_city;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emails = emails;
		this.phone = phone;
	}



	public Person() {
	}



	public Person(long id_city, String firstname, String lastname) {
		super();
		this.id_city = id_city;
		this.firstname = firstname;
		this.lastname = lastname;
	}



	@Override
	public String toString() {
		return "Person [id_person=" + id_person + ", id_city=" + id_city + ", firstname=" + firstname + ", lastname="
				+ lastname + ", emails=" + emails + ", phone=" + phone + "]";
	}



	public long getId_person() {
		return id_person;
	}

	public void setId_person(long id_person) {
		this.id_person = id_person;
	}

	public long getId_city() {
		return id_city;
	}

	public void setId_city(long id_city) {
		this.id_city = id_city;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}

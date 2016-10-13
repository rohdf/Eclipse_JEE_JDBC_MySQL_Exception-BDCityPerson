package nsis.metier;

import java.io.Serializable;

public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	
	long   id_city;
	String name;
	String mayor;
	int    inhabitants;
	int    postalcode;
	
	public City(long id_city, String name, String mayor, int inhabitants, int postalcode) {
		this.id_city = id_city;
		this.name = name;
		this.mayor = mayor;
		this.inhabitants = inhabitants;
		this.postalcode = postalcode;
	}
	
	public City(String name, String mayor, int inhabitants, int postalcode) {
		this.name = name;
		this.mayor = mayor;
		this.inhabitants = inhabitants;
		this.postalcode = postalcode;
	}

	public City(String name) {
		this.name = name;
	}
	
	public City() {
		
	}
	
	
	
	

	@Override
	public String toString() {
		return "City [id_city=" + id_city + ", name=" + name + ", mayor=" + mayor + ", inhabitants=" + inhabitants
				+ ", postalcode=" + postalcode + "]";
	}

	public long getId_city() {
		return id_city;
	}

	public void setId_city(long id_city) {
		this.id_city = id_city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMayor() {
		return mayor;
	}

	public void setMayor(String mayor) {
		this.mayor = mayor;
	}

	public int getInhabitants() {
		return inhabitants;
	}

	public void setInhabitants(int inhabitants) {
		this.inhabitants = inhabitants;
	}

	public int getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	
	
	
}

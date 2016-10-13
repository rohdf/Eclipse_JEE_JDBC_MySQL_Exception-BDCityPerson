package nsis.app;

import java.util.ArrayList;
import java.util.List;

import nsis.dao.CityDAO;
import nsis.dao.PersonDAO;
import nsis.dao.exception.CityDAOException;
import nsis.dao.exception.PersonDAOException;
import nsis.metier.City;
import nsis.metier.Person;
import nsis.util.JDBCManager;

public class JDBCMySqlApp {

	public static void main(String[] args) {
		//Ouvrir une connexion à la DB
		JDBCManager.ouvrirConnexion();
		
		try {
		//Création des DAO
		CityDAO   cityDao   = new CityDAO();
		PersonDAO personDao = new PersonDAO();
		
		
		//Création de 10 villes
		
		for(int i=0; i<10; i++){
			cityDao.insererVille( new City("ville"+(i+1)) );	
		}
		
		//Récupération des villes afin d'avoir l'id pour l'affecter à personne
		List<City> villes = new ArrayList<>();
		for(int i=0; i<10; i++){
			//C'est pas bien fait mais je sais que je n'ai qu'une instance de cette ville 
			villes.add(cityDao.recupererVilles( "ville"+(i+1) ).get(0));
		}
		
		int i = 1;
		for(City v: villes){
			int j=1;
			personDao.ajouterPerson( new Person(v.getId_city(), "ville"+( i )+"Prenom"+(j), "ville"+( i )+"Nom"+(j)) );
			j++;
			personDao.ajouterPerson( new Person(v.getId_city(), "ville"+( i )+"Prenom"+(j), "ville"+( i )+"Nom"+(j)) );
			i++;
		}
		

		//Récupération des personnes 
			List<Person> persons = new ArrayList<>();
			
			for (int i1 = 1; i1<=10; i1++){
				
					for(Person ps: personDao.recupererPersons( "ville"+(i1) )){
						System.out.println(ps);
					}

			}
			
		//Afficher les personnes dans leur ville respective
		afficherPersonDansVille(cityDao, personDao);
			
		} catch (PersonDAOException | CityDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Fermer une connexion à la DB
		JDBCManager.fermerConnexion();

	}
	
	public static void afficherPersonDansVille(CityDAO cdao, PersonDAO pdao) throws CityDAOException, PersonDAOException{
		int i=1;
		for(City v: cdao.recupererToutesLesVilles()){
			System.out.println("> "+v);
			for(Person p: pdao.recupererPersons("ville"+(i++))){
				System.out.println(">>>>> "+p);
			}
		}
	}

}

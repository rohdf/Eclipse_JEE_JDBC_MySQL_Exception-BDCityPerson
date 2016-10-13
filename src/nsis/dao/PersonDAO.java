package nsis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import nsis.dao.exception.PersonDAOException;
import nsis.metier.Person;
import nsis.util.JDBCManager;
import nsis.metier.Person;

public class PersonDAO {
	Connection connexion;
	
	
	public PersonDAO(){
		this.connexion = JDBCManager.getConnexion();
	}
	
	public void ajouterPerson(Person person) throws PersonDAOException{
		
try {
		
	   String ordre;
	   // Instanciation d'un objet Statement
	   Statement statement = connexion.createStatement();
	   // Construction de l'ordre SQL
	   ordre = "INSERT INTO person (id_person,id_city,firstname,lastname,emails,phone) VALUES("+
			person.getId_person()	+","+
	   		person.getId_city()		+","+
	   "'"+person.getFirstname()	+"',"+
	   "'"+person.getLastname()		+"',"+
	   "'"+person.getEmails()		+"',"+
	   "'"+person.getPhone()		+"')";
	   // Exécution de l'ordre SQL
	   statement.executeUpdate(ordre);
	   statement.close();
	  } catch (SQLException expt) {
		throw new PersonDAOException(expt);
	  }catch (NullPointerException expt) {
		throw new PersonDAOException(expt);
	  }
	}
	
	
	public void miseAJourPerson(Person person) throws PersonDAOException{
		try {
			   String ordre;
			   // Instanciation d'un objet Statement
			   Statement statement = connexion.createStatement();
			   // Construction de l'ordre SQL
			   ordre = "UPDATE person SET "
			   		+ "id_city   = "+person.getId_city()+","
			   		+ "firstname = '"+person.getFirstname()+"',"
			   		+ "lastname  = '"+person.getLastname()+"',"
			   		+ "emails    = '"+person.getEmails()+"',"
			   		+ "phone     = '"+person.getPhone()+"' "
			   		+ "WHERE id_person = "+person.getId_person();
			   				
			   // Exécution de l'ordre SQL
			   statement.executeUpdate(ordre);
			   statement.close();
			  } catch (SQLException expt) {
					throw new PersonDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new PersonDAOException(expt);
			  }
	}
	
	
	public void supprimerPerson(Person person) throws PersonDAOException{
		
		  try {
			   // Construction de l'ordre SQL précompilé
			   String ordre = "DELETE FROM person WHERE id_person=? AND id_city=? AND firstname=? AND lastname=? AND emails=? AND phone=?";
			   // Instanciation d'un objet PreparedStatement
			   PreparedStatement stmtPrep;
			   stmtPrep = (PreparedStatement) connexion.prepareStatement(ordre);
			   // Exécution de l'ordre SQL
			   
			    stmtPrep.setLong  (1, person.getId_person());
			    stmtPrep.setLong(2, person.getId_city());
			    stmtPrep.setString(3, person.getFirstname());
			    stmtPrep.setString(4, person.getLastname());
			    stmtPrep.setString(5, person.getEmails());
			    stmtPrep.setString(6, person.getPhone());
			    
			    
			    stmtPrep.executeUpdate();
			   
			   stmtPrep.close();
			  } catch (SQLException expt) {
					throw new PersonDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new PersonDAOException(expt);
			  }
	}
	
	public Person recupererPersonParId(long id) throws PersonDAOException{
		Person person = new Person();
		try {
			   String ordre;
			   
			   Statement statement = connexion.createStatement();
			   // Construction de l'ordre SQL
			   ordre = "SELECT * FROM person WHERE id_person="+ id ;
			   
			   System.out.println(ordre);
			   
			   // Exécution de l'ordre SQL
			   ResultSet resultSet = statement.executeQuery(ordre);
			   
			   if(resultSet.next()){
				   person.setId_person(resultSet.getLong("id_person"));
				   person.setId_city(resultSet.getLong("id_city"));
				   person.setFirstname(resultSet.getString("firstname"));
				   person.setLastname(resultSet.getString("lastname"));
				   person.setEmails(resultSet.getString("emails"));
				   person.setPhone(resultSet.getString("phone"));
			   }
			   resultSet.close();
			   statement.close();
			  } catch (SQLException expt) {
					throw new PersonDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new PersonDAOException(expt);
			  }
				return person;
			 
		
	}
	
	public List<Person> recupererPersons(String nomDePerson) throws PersonDAOException{
		List<Person> persons = new ArrayList<>();
		if(nomDePerson == null)
			throw new PersonDAOException(new NullPointerException());
		try {
			   String ordre;
			   
			   Statement statement = connexion.createStatement();
			   // Construction de l'ordre SQL
			   ordre = "SELECT * FROM person WHERE lastname LIKE '"+ nomDePerson +"%'";
			   
			   System.out.println(ordre);
			   
			   // Exécution de l'ordre SQL
			   ResultSet resultSet = statement.executeQuery(ordre);
			   
			   while(resultSet.next()){
				   persons.add(new Person(
						   resultSet.getLong("id_person"), 
						   resultSet.getLong("id_city"), 
						   resultSet.getString("firstname"), 
						   resultSet.getString("lastname"), 
						   resultSet.getString("emails"), 
						   resultSet.getString("phone")));
			   }
			   
			   resultSet.close();
			   statement.close();
			  } catch (SQLException expt) {
					throw new PersonDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new PersonDAOException(expt);
			  }
		return persons;
	}
	
	public List<Person> recupererToutesLesPersons() throws PersonDAOException{
		List<Person> persons = new ArrayList<>();
		try {
			   String ordre;
			   
			   Statement statement = connexion.createStatement();
			   // Construction de l'ordre SQL
			   ordre = "SELECT * FROM person" ;
			   
			   System.out.println(ordre);
			   
			   // Exécution de l'ordre SQL
			   ResultSet resultSet = statement.executeQuery(ordre);
			   
			   while(resultSet.next()){
				   persons.add(new Person(
						   resultSet.getLong("id_person"), 
						   resultSet.getLong("id_city"), 
						   resultSet.getString("firstname"), 
						   resultSet.getString("lastname"), 
						   resultSet.getString("emails"), 
						   resultSet.getString("phone")));
			   }
			   
			   resultSet.close();
			   statement.close();
			  } catch (SQLException expt) {
					throw new PersonDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new PersonDAOException(expt);
			  }
		return persons;
	}
}

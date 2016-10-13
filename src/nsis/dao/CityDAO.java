package nsis.dao;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import nsis.metier.Person;
import nsis.util.JDBCManager;
import nsis.dao.exception.CityDAOException;
import nsis.dao.exception.PersonDAOException;
import nsis.metier.City;

public class CityDAO implements DAO{
	Connection connexion;
	
	
	public CityDAO(){
		this.connexion = JDBCManager.getConnexion();
	}
	
	public void insererVille(City ville) throws CityDAOException{


try {
	   String ordre;
	   // Instanciation d'un objet Statement
	   Statement statement = connexion.createStatement();
	   // Construction de l'ordre SQL
	   ordre = "INSERT INTO city (id_city,name,mayor,inhabitants,postalcode) VALUES("+
	   ville.getId_city()+",'"+
	   ville.getName()+"','"+
	   ville.getMayor()+"',"+
	   ville.getInhabitants()+","+
	   ville.getPostalcode()+")";
	   // Exécution de l'ordre SQL
	   statement.executeUpdate(ordre);
	   statement.close();
	  } catch (SQLException expt) {
				throw new CityDAOException(expt);
	  }catch (NullPointerException expt) {
		throw new CityDAOException(expt);
	  }

	}
	
	public void miseAJourCity(City ville) throws CityDAOException{
		try {
			   String ordre;
			   // Instanciation d'un objet Statement
			   Statement statement = connexion.createStatement();
			   // Construction de l'ordre SQL
			   ordre = "UPDATE city SET "
			   		+ "name = '"+ville.getName()+"',"
			   		+ "mayor  = '"+ville.getMayor()+"',"
			   		+ "inhabitants    = "+ville.getInhabitants()+","
			   		+ "postalcode     = "+ville.getPostalcode()+" "
			   		+ "WHERE id_city = "+ville.getId_city();
			   				
			   // Exécution de l'ordre SQL
			   statement.executeUpdate(ordre);
			   statement.close();
			  } catch (SQLException expt) {
						throw new CityDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new CityDAOException(expt);
			  }
	}
	
	public void supprimerVille(City ville) throws CityDAOException{
		
		  try {
			   // Construction de l'ordre SQL précompilé
			   String ordre = "DELETE FROM city WHERE id_city=? AND name=? AND mayor=? AND inhabitants=? AND postalcode=?";
			   // Instanciation d'un objet PreparedStatement
			   PreparedStatement stmtPrep;
			   stmtPrep = (PreparedStatement) connexion.prepareStatement(ordre);
			   // Exécution de l'ordre SQL
			   
			    stmtPrep.setLong  (1, ville.getId_city());
			    stmtPrep.setString(2, ville.getName());
			    stmtPrep.setString(3, ville.getMayor());
			    stmtPrep.setInt   (4, ville.getInhabitants());
			    stmtPrep.setInt   (5, ville.getPostalcode());
			    
			    
			    stmtPrep.executeUpdate();
			   
			   stmtPrep.close();
			  } catch (SQLException expt) {
				  throw new CityDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new CityDAOException(expt);
			  }
	}
	
	public City recupererVilleParId(long id) throws CityDAOException{
		City ville = new City();
		try {
			   String ordre;
			   
			   Statement statement = connexion.createStatement();
			   // Construction de l'ordre SQL
			   ordre = "SELECT * FROM city WHERE id_city="+ id ;
			   
			   System.out.println(ordre);
			   
			   // Exécution de l'ordre SQL
			   ResultSet resultSet = statement.executeQuery(ordre);
			   
			   if(resultSet.next()){
				   ville.setId_city(resultSet.getLong("id_city")); 
				   ville.setName(resultSet.getString("name"));
				   ville.setMayor(resultSet.getString("mayor"));
				   ville.setInhabitants(resultSet.getInt("inhabitants"));
				   ville.setPostalcode(resultSet.getInt("postalcode"));
			   }
			   resultSet.close();
			   statement.close();
			  } catch (SQLException expt) {
					throw new CityDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new CityDAOException(expt);
			  }
				return ville;
			 
		
	}
	
	public List<City> recupererVilles(String nomDeVille) throws CityDAOException{
		List<City> villes = new ArrayList<>();
		if(nomDeVille == null)
			throw new CityDAOException(new NullPointerException());
		
		try {
			   String ordre;
			   
			   Statement statement = connexion.createStatement();
			   // Construction de l'ordre SQL
			   ordre = "SELECT * FROM city WHERE name LIKE '"+ nomDeVille +"%'";
			   
			   System.out.println(ordre);
			   
			   // Exécution de l'ordre SQL
			   ResultSet resultSet = statement.executeQuery(ordre);
			   
			   while(resultSet.next()){
				   villes.add(new City(
						   resultSet.getLong("id_city"),
						   resultSet.getString("name"),
						   resultSet.getString("mayor"),
						   resultSet.getInt("inhabitants"), 
						   resultSet.getInt("postalcode"))
						   );
			   }
			   
			   resultSet.close();
			   statement.close();
			  } catch (SQLException expt) {
				throw new CityDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new CityDAOException(expt);
			  }
		return villes;
	}
	
	public List<City> recupererToutesLesVilles() throws CityDAOException{
		List<City> villes = new ArrayList<>();
		try {
			   String ordre;
			   
			   Statement statement = connexion.createStatement();
			   // Construction de l'ordre SQL
			   ordre = "SELECT * FROM city" ;
			   
			   System.out.println(ordre);
			   
			   // Exécution de l'ordre SQL
			   ResultSet resultSet = statement.executeQuery(ordre);
			   
			   while(resultSet.next()){
				   villes.add(new City(
						   resultSet.getLong("id_city"),
						   resultSet.getString("name"),
						   resultSet.getString("mayor"),
						   resultSet.getInt("inhabitants"), 
						   resultSet.getInt("postalcode"))
						   );
			   }
			   
			   resultSet.close();
			   statement.close();
			  } catch (SQLException expt) {
					throw new CityDAOException(expt);
			  }catch (NullPointerException expt) {
					throw new CityDAOException(expt);
			  }
		return villes;
	}
	
	

}

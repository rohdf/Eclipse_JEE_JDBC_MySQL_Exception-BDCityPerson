package nsis.test.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import nsis.util.JDBCManager;

public abstract class TestMerePersonEtCity extends TestCase {

	public TestMerePersonEtCity(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		JDBCManager.ouvrirConnexion();
		razSystem();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		JDBCManager.fermerConnexion();;
	}

	public void razSystem(){
		try {
			
			   // Instanciation d'un objet Statement
			   Statement statement = JDBCManager.getConnexion().createStatement();
			   
			   //Création lite d'ordre
			   List<String> ordres = new ArrayList<>();
			   
			   //Supprimer Tables
			   ordres.add("DROP TABLE IF EXISTS person CASCADE");
			   ordres.add("DROP TABLE IF EXISTS city CASCADE");
			  
			   //Création des Tables city et person
			   ordres.add("CREATE TABLE city ("
			   		+ "id_city INT NOT NULL AUTO_INCREMENT,"
			   		+ "name VARCHAR (50),mayor VARCHAR(100),"
			   		+ "inhabitants INT,postalcode INT(6),"
			   		+ "PRIMARY KEY(id_city),INDEX city_name (name)"
			   		+ ") engine=innodb "
			   		);
			   
			   ordres.add("CREATE TABLE person ("
			   		+ "id_person INT NOT NULL AUTO_INCREMENT,"
			   		+ "id_city INT,firstname VARCHAR (50),"
			   		+ "lastname VARCHAR (50),"
			   		+ "emails VARCHAR(100),"
			   		+ "phone VARCHAR(20),"
			   		+ "PRIMARY KEY(id_person),"
			   		+ "FOREIGN KEY person_city (id_city) references city (id_city) ON DELETE SET null,"
			   		+ "INDEX person_lastname(lastname)"
			   		+ ") engine=innodb;"
			   		);

			   //Init des Tables
			   ordres.add("INSERT INTO city (name,mayor,inhabitants,postalcode) VALUES('Paris'    ,'Anne Hidalgo',12405000,75001)");
			   ordres.add("INSERT INTO city (name,mayor,inhabitants,postalcode) VALUES('Lyon'     ,'Gérard Collomb',2237000,69000)");
			   ordres.add("INSERT INTO city (name,mayor,inhabitants,postalcode) VALUES('Marseille','Jean-Claude Gaudin',1734000,130000)");
			   ordres.add("INSERT INTO city (name,mayor,inhabitants,postalcode) VALUES('Toulouse' ,'Jean-Luc Moudenc',1291000,31000)");
			   
			   ordres.add("INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(1,'Nicolas','HANG','nhang@nsis.com','000000000');");
			   ordres.add("INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(1,'François','HANG','fhang@nsis.com','1111111111');");
			   ordres.add("INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(2,'Frank','THIBAULT','fthibault@nsis.com','2222222222');");
			   ordres.add("INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(2,'Loic','GOLVIN','lgolvin@nsis.com','3333333333');");
			   ordres.add("INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(3,'Christophe','PITREY','@nsis.com','4444444444');");
			   ordres.add("INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(3,'Rohdri','FRIMAT','rfrimat@nsis.com','5555555555');");
			   ordres.add("INSERT INTO person (id_city,firstname,lastname,emails,phone) VALUES(4,'Laurent','MERCIER','lmercier@nsis.com','6666666666');");
			   
			   // Exécution de l'ordre SQL
			   for(String ordre: ordres){
				   statement.executeUpdate(ordre);
			   }

			   statement.close();
			  } catch (SQLException expt) {
				//throw new PersonDAOException(expt);
			  }catch (NullPointerException expt) {
				//throw new PersonDAOException(expt);
			  }
	}
}

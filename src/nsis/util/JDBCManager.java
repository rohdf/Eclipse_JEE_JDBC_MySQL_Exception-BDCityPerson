/**
 * Auteur: Rohdri FRIMAT
 * Date : 21/09/2016
 */
package nsis.util;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class JDBCManager {

 static public PrintStream so = System.out;
 static public Connection connexion;

 private JDBCManager(){}
 
 public static void ouvrirConnexion() {
  try {
   // chargement du driver
	  Class.forName("com.mysql.jdbc.Driver"); // A compléter suivant le type de driver JDBC
   // instanciation d'un objet Connection
   connexion =
      DriverManager.getConnection("jdbc:mysql://localhost/annu?" +
                                  "user=sa&password=sa");
   
   
   traiterSQLWarning(connexion.getWarnings());

   
  } catch (SQLException ex) {
   traiterSQLException(ex);
  } catch (ClassNotFoundException ex) {
   ex.printStackTrace();
  }
 }
 
 
 public static void fermerConnexion() {
	  try {
	   // fermeture de la connexion
	   connexion.close();
	  } catch (SQLException ex) {
	   traiterSQLException(ex);
	  }
	 }
 
 private static void traiterSQLException(SQLException expt) {
	  so.println("\n SQLException \n");
	  while (expt != null) {
	   so.println("SQLState: " + expt.getSQLState());
	   so.println("Message : " + expt.getMessage());
	   so.println("Vendeur : " + expt.getErrorCode());
	   expt = expt.getNextException();
	  }
	 }

private static void traiterSQLWarning(SQLWarning warn) throws SQLException {
	  if (warn != null) {
	   so.println("\n SQLWarning \n");
	   while (warn != null) {
	    so.println("SQLState: " + warn.getSQLState());
	    so.println("Message : " + warn.getMessage());
	    so.println("Vendeur : " + warn.getErrorCode());
	    warn = warn.getNextWarning();
	   }
	  }
	 }

public static Connection getConnexion() {
	return connexion;
}

public static void setConnexion(Connection connexion) {
	JDBCManager.connexion = connexion;
}




}
package nsis.dao.exception;

import java.sql.SQLException;

public class PersonDAOException extends DAOException{
	private static final long serialVersionUID = 1L;
	public PersonDAOException(SQLException expt) {
		  System.out.println("\n PersonDAOException SQLException \n");
		  while (expt != null) {
		   System.out.println("SQLState: " + expt.getSQLState());
		   System.out.println("Message : " + expt.getMessage());
		   System.out.println("Vendeur : " + expt.getErrorCode());
		   expt = expt.getNextException();
		  }
		 }
	
	public PersonDAOException(NullPointerException expt) {
		  System.out.println("\n PersonDAOException NullPointerException \n");
		  if (expt != null) {
		   System.out.println("PersonDAOException Message : " + expt.getMessage());

		  }
		 }

}

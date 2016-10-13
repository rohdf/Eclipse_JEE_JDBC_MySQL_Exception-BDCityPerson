package nsis.dao.exception;

import java.sql.SQLException;

public class CityDAOException extends DAOException{
	private static final long serialVersionUID = 1L;

	public CityDAOException(SQLException expt) {
		  System.out.println("\n CityDAOException SQLException \n");
		  while (expt != null) {
		   System.out.println("SQLState: " + expt.getSQLState());
		   System.out.println("Message : " + expt.getMessage());
		   System.out.println("Vendeur : " + expt.getErrorCode());
		   expt = expt.getNextException();
		  }
		 }
	
	public CityDAOException(NullPointerException expt) {
		  System.out.println("\n CityDAOException NullPointerException \n");
		  if (expt != null) {
		   System.out.println("Message : " + expt.getMessage());

		  }
		 }
	
}

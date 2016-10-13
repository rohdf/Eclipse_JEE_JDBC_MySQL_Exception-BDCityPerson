package nsis.test.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import nsis.dao.PersonDAO;
import nsis.dao.exception.PersonDAOException;
import nsis.metier.Person;

public class TestPersonDAO extends TestMerePersonEtCity {

	public TestPersonDAO(String name) {
		super(name);
	}



	public void testAddPersonNull(){
		PersonDAO pdao = new PersonDAO();
		try {
			pdao.ajouterPerson(null);
			TestCase.fail("une exception aurait du être levée !!");
		} catch (PersonDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSupprimerPersonNull(){
		PersonDAO pdao = new PersonDAO();
		
		try {
			pdao.supprimerPerson(null);
			TestCase.fail("une exception aurait du être levée !!");
		} catch (PersonDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSelectPersonNull(){
		PersonDAO pdao = new PersonDAO();
		try {
			pdao.recupererPersons(null);
			TestCase.fail("une exception aurait du être levée !!");
		} catch (PersonDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testPersonneDansBD(){
		PersonDAO pdao = new PersonDAO();
		List<String> prenoms = new ArrayList<String>();
		
		prenoms.add("Nicolas");
		prenoms.add("François");
		prenoms.add("Frank");
		prenoms.add("Loic");
		prenoms.add("Christophe");
		prenoms.add("Rohdri");
		prenoms.add("Laurent");
		
		try {
			int i = 0;
			for(Person p: pdao.recupererToutesLesPersons()){
				TestCase.assertEquals("N'est pas la person attendue !!!", prenoms.get(i++), p.getFirstname());
			}
		} catch (PersonDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

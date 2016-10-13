package nsis.test.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import nsis.dao.CityDAO;
import nsis.dao.exception.CityDAOException;
import nsis.dao.exception.PersonDAOException;
import nsis.metier.City;
import nsis.metier.Person;

public class TestCityDao extends TestMerePersonEtCity {

	public TestCityDao(String name) {
		super(name);
	}
	
	public void testAddCityNull(){
		CityDAO cdao = new CityDAO();
		try {
			cdao.insererVille(null);
			TestCase.fail("une exception aurait du être levée !!");
		} catch (CityDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSupprimerCityNull(){
		CityDAO cdao = new CityDAO();
		
		try {
			cdao.supprimerVille(null);
			TestCase.fail("une exception aurait du être levée !!");
		} catch (CityDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSelectCityNull(){
		CityDAO cdao = new CityDAO();
		try {
			cdao.recupererVilles(null);
			TestCase.fail("une exception aurait du être levée !!");
		} catch (CityDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testCityDansBD(){
		CityDAO pdao = new CityDAO();
		List<String> prenoms = new ArrayList<String>();
		
		prenoms.add("Paris");
		prenoms.add("Lyon");
		prenoms.add("Marseille");
		prenoms.add("Toulouse");
		
		try {
			int i = 0;
			for(City c: pdao.recupererToutesLesVilles()){
				TestCase.assertEquals("N'est pas la person attendue !!!", prenoms.get(i++), c.getName());
				
			}
		} catch (CityDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

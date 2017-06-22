package nl.hu.v1wac.firstapp.webservices;

import java.util.List;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.Medewerker;
import nl.hu.v1wac.firstapp.persistence.MedewerkerDAO;

//alle resource functies lopen via hier
public class MedewerkerService {
	MedewerkerDAO Medewerker = new MedewerkerDAO();
	
	public List<Medewerker> getAllMedewerkers() {
		return Medewerker.findAll();
	}
	
	public Medewerker getMedewerkerByID(int ID) {
		Medewerker result = Medewerker.findByID(ID);
		return result;
	}
	public Medewerker update(Medewerker medewerker){
		Medewerker result = Medewerker.update(medewerker);
		return result;
	}
	public void deleteMedewerker(int ID) {
		Medewerker m = Medewerker.findByID(ID);
		System.out.println(ID);
		if (m != null) {
			Medewerker.delete(m);
		} else throw new IllegalArgumentException("Medewerker does not exist! (m=null)");
	}
}

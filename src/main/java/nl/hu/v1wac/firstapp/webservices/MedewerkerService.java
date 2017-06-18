package nl.hu.v1wac.firstapp.webservices;

import java.util.List;

import nl.hu.v1wac.firstapp.model.Medewerker;
import nl.hu.v1wac.firstapp.persistence.MedewerkerDAO;

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
}

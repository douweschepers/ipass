package nl.hu.v1wac.firstapp.webservices;

public class MedewerkerServiceProvider {
	private static MedewerkerService medewerkerService= new MedewerkerService();

	public static MedewerkerService getMedewerkerService() {
		return medewerkerService;
	}
}

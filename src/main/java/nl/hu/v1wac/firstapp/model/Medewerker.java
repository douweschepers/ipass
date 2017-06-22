package nl.hu.v1wac.firstapp.model;

public class Medewerker {
	// zet de variable die ik gebruik
	private int medewerkerID;
	private int huisNummer;
	private String straatNaam;
	private String voornaam;
	private String Achternaam;
	private String rol;
	private int ProjectID;
	private String gebruikersNaam;
	private String wachtwoord;
	//de constructor
	public Medewerker(int medewerkerID, int huisNummer, String straatNaam, String voornaam, String achternaam,
			String rol, int projectID, String gebruikersNaam, String wachtwoord) {
		super();
		this.medewerkerID = medewerkerID;
		this.huisNummer = huisNummer;
		this.straatNaam = straatNaam;
		this.voornaam = voornaam;
		Achternaam = achternaam;
		this.rol = rol;
		ProjectID = projectID;
		this.gebruikersNaam = gebruikersNaam;
		this.wachtwoord = wachtwoord;
	}
	//de getters en setters
	public int getMedewerkerID() {
		return medewerkerID;
	}
	public void setMedewerkerID(int medewerkerID) {
		this.medewerkerID = medewerkerID;
	}
	public int getHuisNummer() {
		return huisNummer;
	}
	public void setHuisNummer(int huisNummer) {
		this.huisNummer = huisNummer;
	}
	public String getStraatNaam() {
		return straatNaam;
	}
	public void setStraatNaam(String straatNaam) {
		this.straatNaam = straatNaam;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getAchternaam() {
		return Achternaam;
	}
	public void setAchternaam(String achternaam) {
		Achternaam = achternaam;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public int getProjectID() {
		return ProjectID;
	}
	public void setProjectID(int projectID) {
		ProjectID = projectID;
	}
	public String getGebruikersNaam() {
		return gebruikersNaam;
	}
	public void setGebruikersNaam(String gebruikersNaam) {
		this.gebruikersNaam = gebruikersNaam;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
}

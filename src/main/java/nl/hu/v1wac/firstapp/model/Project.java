package nl.hu.v1wac.firstapp.model;

import java.util.Date;

public class Project {
	private int projectID;
	private int huisNummer;
	private String straatnaam;
	private String segment;
	private String type;
	private String solution;
	private int telefoonNummer;
	private int medewerkersID;
	private String begindatum;
	private String projectNaam;
	private String status;
	
	
	public Project(int projectID, int huisNummer, String straatnaam, String segment, String type, String solution,
			int telefoonNummer, int medewerkersID, String begindatum, String projectNaam, String status) {
		super();
		this.projectID = projectID;
		this.huisNummer = huisNummer;
		this.straatnaam = straatnaam;
		this.segment = segment;
		this.type = type;
		this.solution = solution;
		this.telefoonNummer = telefoonNummer;
		this.medewerkersID = medewerkersID;
		this.begindatum = begindatum;
		this.projectNaam = projectNaam;
		this.status = status;
	}
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public int getHuisNummer() {
		return huisNummer;
	}
	public void setHuisNummer(int huisNummer) {
		this.huisNummer = huisNummer;
	}
	public String getStraatnaam() {
		return straatnaam;
	}
	public void setStraatnaam(String straatnaam) {
		this.straatnaam = straatnaam;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public int getTelefoonNummer() {
		return telefoonNummer;
	}
	public void setTelefoonNummer(int telefoonNummer) {
		this.telefoonNummer = telefoonNummer;
	}
	public int getMedewerkersID() {
		return medewerkersID;
	}
	public void setMedewerkersID(int medewerkersID) {
		this.medewerkersID = medewerkersID;
	}
	public String getBegindatum() {
		return begindatum;
	}
	public void setBegindatum(String begindatum) {
		this.begindatum = begindatum;
	}
	public String getProjectNaam() {
		return projectNaam;
	}
	public void setProjectNaam(String projectNaam) {
		this.projectNaam = projectNaam;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

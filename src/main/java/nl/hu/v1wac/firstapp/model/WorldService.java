package nl.hu.v1wac.firstapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.v1wac.firstapp.persistence.CountryDAO;

public class WorldService {
	CountryDAO country = new CountryDAO();
		
	public List<Country> getAllCountries() {
		return country.findAll();
	}
	
	public List<Country> get10LargestPopulations() {		
		ArrayList<Country> countryList = new ArrayList <Country>();
		for(Country hoi: country.find10LargestPopulations()){
		countryList.add(hoi);
		}
		return countryList;
	}

	public List<Country> get10LargestSurfaces() {		
		
		ArrayList<Country> countryList = new ArrayList <Country>();
		for(Country hoi: country.find10LargestSurfaces()){
		countryList.add(hoi);
		}
		return countryList;
	}
	
	public Country getCountryByCode(String code) {
		Country result = country.findByCode(code);
		
		/*
		for (Country c : country.findAll()) {
			if (c.getCode().equals(code)) {
				result = c;
				break;
			}
		}*/
		
		return result;
	}
	public void deleteCountry(String code) {
		Country c = country.findByCode(code);
		System.out.println(code);
		if (c != null) {
			country.delete(c);
		} else throw new IllegalArgumentException("Code does not exist! (c=null)");
	}

}

package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Country;

public class CountryDAO extends BaseDAO{
	public List<Country> findAll(){
		List<Country> results = new ArrayList<Country>();
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("Select * from Country");
			while (dbResultSet.next()) {
		        String code = dbResultSet.getString("code");
		        String code2 = dbResultSet.getString("code2");
		        String name = dbResultSet.getString("name");
		        String capital = dbResultSet.getString("capital");
		        String continent = dbResultSet.getString("continent");
		        String region = dbResultSet.getString("region");
		        double surface = dbResultSet.getDouble("surfacearea");
		        int population = dbResultSet.getInt("population");
		        String gov = dbResultSet.getString("governmentform");
		        double lat = dbResultSet.getDouble("latitude");
		        double lon = dbResultSet.getDouble("longitude");
		        
		        Country newCountry = new Country(code, code2, name, capital, continent, region, surface, population, gov, lat, lon);
		        results.add(newCountry);
		      }
	
		}catch (SQLException sqle){
			sqle.printStackTrace();
		}
		return results;
	}
	
	public Country findByCode(String incode){
		Country newCountry = null;
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("Select * from country where code='" + incode+"'");
			while (dbResultSet.next()) {
			    String code = dbResultSet.getString("code");
		        String code2 = dbResultSet.getString("code2");
		        String name = dbResultSet.getString("name");
		        String capital = dbResultSet.getString("capital");
		        String continent = dbResultSet.getString("continent");
		        String region = dbResultSet.getString("region");
		        double surface = dbResultSet.getDouble("surfacearea");
		        int population = dbResultSet.getInt("population");
		        String gov = dbResultSet.getString("governmentform");
		        double lat = dbResultSet.getDouble("latitude");
		        double lon = dbResultSet.getDouble("longitude");
		        
		        newCountry = new Country(code, code2, name, capital, continent, region, surface, population, gov, lat, lon);
			}
		}catch(SQLException e){
			
		}
		return newCountry;
	}
	
	public List<Country> find10LargestPopulations(){
		List<Country> results = new ArrayList<Country>();
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("select * from country order by population Desc Limit 10;");
			while (dbResultSet.next()) {
				  String code = dbResultSet.getString("code");
			        String code2 = dbResultSet.getString("code2");
			        String name = dbResultSet.getString("name");
			        String capital = dbResultSet.getString("capital");
			        String continent = dbResultSet.getString("continent");
			        String region = dbResultSet.getString("region");
			        double surface = dbResultSet.getDouble("surfacearea");
			        int population = dbResultSet.getInt("population");
			        String gov = dbResultSet.getString("governmentform");
			        double lat = dbResultSet.getDouble("latitude");
			        double lon = dbResultSet.getDouble("longitude");
			
			        Country newCountry = new Country(code, code2, name, capital, continent, region, surface, population, gov, lat, lon);
			        results.add(newCountry);
			}
		}catch(SQLException sqle){
			
		}
		return results;
	}
	
	public List<Country> find10LargestSurfaces(){
		List<Country> results = new ArrayList<Country>();
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("select * from country order by surfacearea Desc Limit 10;");
			while (dbResultSet.next()) {
				  String code = dbResultSet.getString("code");
			        String code2 = dbResultSet.getString("code2");
			        String name = dbResultSet.getString("name");
			        String capital = dbResultSet.getString("capital");
			        String continent = dbResultSet.getString("continent");
			        String region = dbResultSet.getString("region");
			        double surface = dbResultSet.getDouble("surfacearea");
			        int population = dbResultSet.getInt("population");
			        String gov = dbResultSet.getString("governmentform");
			        double lat = dbResultSet.getDouble("latitude");
			        double lon = dbResultSet.getDouble("longitude");
			
			        Country newCountry = new Country(code, code2, name, capital, continent, region, surface, population, gov, lat, lon);
			        results.add(newCountry);
			}
		}catch(SQLException sqle){
			
		}
		return results;
	}
	
public Country update(Country country){
	try(Connection con = super.getConnection()){
		Statement stmt = con.createStatement();
		ResultSet dbResultSet = stmt.executeQuery("update country set" +
		country.getCode()+
		country.getIso3Code()+
		country.getName()+
		country.getCapital()+
		country.getContinent()+
		country.getRegion()+
		country.getSurface()+
		country.getPopulation()+
		country.getGovernment()+
		country.getLatitude()+
		country.getLongitude()+
		"where code='" + country.getCode()+ "'");
	}catch(SQLException sqle){
		
	}
	return country;
}
	
	public boolean delete(Country country){
		boolean result = false;
		boolean countryExists = findByCode(country.getCode()) != null;
		
		
		if(countryExists){
			String query= "delete from country where code = '"+country.getCode()+"'";
		
		try (Connection con = getConnection()) {
			
			Statement stmt = con.createStatement();
			if (stmt.executeUpdate(query) == 1) { // 1 row updated!
				result = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			result = false;
		}
		}
		return result;
	}
}

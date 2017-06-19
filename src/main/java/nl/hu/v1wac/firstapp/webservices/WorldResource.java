package nl.hu.v1wac.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.WorldService;

@Path("/country")
public class WorldResource {
	
	@GET
	@Produces("application/json")
	public String getCountry(){
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Country c :service.getAllCountries()){
			JsonObjectBuilder job =Json.createObjectBuilder();
			job.add("code", c.getCode())
		    .add("iso3", c.getIso3Code())
		    .add("name", c.getName())
		    .add("continent", c.getContinent())
		    .add("capital", c.getCapital())
		    .add("region", c.getRegion())
		    .add("surface", c.getSurface())
		    .add("population", c.getPopulation())
		    .add("government", c.getGovernment())
		    .add("lat", c.getLatitude())
		    .add("lng", c.getLongitude());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		//System.out.println(array);
		return array.toString();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getCountryByCode(@PathParam("id") String id){
		WorldService service = ServiceProvider.getWorldService();
		//JsonArrayBuilder jab = Json.createArrayBuilder();
		
		Country c = service.getCountryByCode(id);
		for(Country x: service.getAllCountries()){
			if (!c.equals(x.getCode())){
				throw new WebApplicationException("no such country!");
			}
		}
		JsonObjectBuilder job =Json.createObjectBuilder();
		job.add("code", c.getCode())
	    .add("iso3", c.getIso3Code())
	    .add("name", c.getName())
	    .add("continent", c.getContinent())
	    .add("capital", c.getCapital())
	    .add("region", c.getRegion())
	    .add("surface", c.getSurface())
	    .add("population", c.getPopulation())
	    .add("government", c.getGovernment())
	    .add("lat", c.getLatitude())
	    .add("lng", c.getLongitude());
			
		//jab.add(job);
		
		//JsonArray array = jab.build();
		//System.out.println(array);
		return job.build().toString();
	}
	
	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getCountrySurface(){
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Country c :service.get10LargestSurfaces()){
			JsonObjectBuilder job =Json.createObjectBuilder();
			job.add("code", c.getCode())
		    .add("iso3", c.getIso3Code())
		    .add("name", c.getName())
		    .add("continent", c.getContinent())
		    .add("capital", c.getCapital())
		    .add("region", c.getRegion())
		    .add("surface", c.getSurface())
		    .add("population", c.getPopulation())
		    .add("government", c.getGovernment())
		    .add("lat", c.getLatitude())
		    .add("lng", c.getLongitude());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String getCountryPopulation(){
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Country c :service.get10LargestPopulations()){
			JsonObjectBuilder job =Json.createObjectBuilder();
			job.add("code", c.getCode())
		    .add("iso3", c.getIso3Code())
		    .add("name", c.getName())
		    .add("continent", c.getContinent())
		    .add("capital", c.getCapital())
		    .add("region", c.getRegion())
		    .add("surface", c.getSurface())
		    .add("population", c.getPopulation())
		    .add("government", c.getGovernment())
		    .add("lat", c.getLatitude())
		    .add("lng", c.getLongitude());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	@DELETE
	@Path("{id}")
	public void deleteCountry(@PathParam("id") String id) {	
		WorldService service = ServiceProvider.getWorldService();
		service.deleteCountry(id);
	}
}

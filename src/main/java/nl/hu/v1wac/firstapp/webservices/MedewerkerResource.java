package nl.hu.v1wac.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Medewerker;
import nl.hu.v1wac.firstapp.model.WorldService;

@Path("/medewerker")
public class MedewerkerResource {
	
	private JsonObjectBuilder medewerkerToJson(Medewerker m){
		  JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("medewerkers_id", m.getMedewerkerID())
			.add("huisnr", m.getHuisNummer())
		    .add("straatnaam", m.getStraatNaam())
		    .add("voornaam", m.getVoornaam())
		    .add("achternaam", m.getAchternaam())
		    .add("rol", m.getRol())
		    .add("project_id", m.getProjectID())
		    .add("gebruikersnaam", m.getGebruikersNaam())
		    .add("wachtwoord", m.getWachtwoord());
		  return job;
		 }
	
	@GET
	@Produces("application/json")
	public String getMedewerker(){
		MedewerkerService service = MedewerkerServiceProvider.getMedewerkerService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for(Medewerker m :service.getAllMedewerkers()){
			JsonObjectBuilder job =Json.createObjectBuilder();
			job.add("medewerkers_id", m.getMedewerkerID())
		    .add("huisnr", m.getHuisNummer())
		    .add("straatnaam", m.getStraatNaam())
		    .add("voornaam", m.getVoornaam())
		    .add("achternaam", m.getAchternaam())
		    .add("rol", m.getRol())
		    .add("project_id", m.getProjectID())
		    .add("gebruikersnaam", m.getGebruikersNaam())
		    .add("wachtwoord", m.getWachtwoord());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getMedewerkerById(@PathParam("id") int id){
		MedewerkerService service = MedewerkerServiceProvider.getMedewerkerService();
		Medewerker m = service.getMedewerkerByID(id);
	
		JsonObjectBuilder job =Json.createObjectBuilder();
		job.add("medewerkers_id", m.getMedewerkerID())
	    .add("huisnr", m.getHuisNummer())
	    .add("straatnaam", m.getStraatNaam())
	    .add("voornaam", m.getVoornaam())
	    .add("achternaam", m.getAchternaam())
	    .add("rol", m.getRol())
	    .add("project_id", m.getProjectID())
	    .add("gebruikersnaam", m.getGebruikersNaam())
	    .add("wachtwoord", m.getWachtwoord());
						
		return job.build().toString();
	}
	@PUT
	@Path("{id}")
	@Produces("application/json")
	public Response updateMedewerker(@PathParam("id") int id,
		@FormParam("voornaam") String voornaam,
		@FormParam("achternaam") String achternaam,
		@FormParam("straatnaam") String straatnaam,
		@FormParam("huisnummer") int huisnummer) {
		
		MedewerkerService service = MedewerkerServiceProvider.getMedewerkerService();
		Medewerker m = service.getMedewerkerByID(id);	
		
		if(m != null) {
			m.setVoornaam(voornaam);
			m.setAchternaam(achternaam);
			m.setStraatNaam(straatnaam);
			m.setHuisNummer(huisnummer);	
			
			String a = medewerkerToJson(service.update(m)).build().toString();
			return Response.ok(a).build();
		}
		
		throw new WebApplicationException("Country not found!");
		
	}
	@DELETE
	@Path("{id}")
	public void deleteMedewerker(@PathParam("id") int id) {	
		MedewerkerService service = MedewerkerServiceProvider.getMedewerkerService();
		service.deleteMedewerker(id);
	}

}

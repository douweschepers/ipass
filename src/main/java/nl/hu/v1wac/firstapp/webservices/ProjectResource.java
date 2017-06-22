package nl.hu.v1wac.firstapp.webservices;


import java.text.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Medewerker;
import nl.hu.v1wac.firstapp.model.Project;

//het pad om naar deze classe te komen
@Path("/project")
public class ProjectResource {
	//één functie om een project naar json te zetten
	private JsonObjectBuilder projectToJson(Project p){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("project_id", p.getProjectID())
	    .add("huisnr", p.getHuisNummer())
	    .add("straatnaam", p.getStraatnaam())
	    .add("segment", p.getSegment())
	    .add("type", p.getType())
	    .add("solution", p.getSolution())
	    .add("telefoonnr", p.getTelefoonNummer())
	    .add("medewerkers_id", p.getMedewerkersID())
	    .add("begindatum",  p.getBegindatum())
	    .add("projectNaam", p.getProjectNaam())
	    .add("status", p.getStatus());
        return job;
    }	
	//de get methode om een project op te roepen
		@GET
		@Produces("application/json")
		public String getProject(){
			
			ProjectService service = ProjectServiceProvider.getProjectService();
			JsonArrayBuilder jab = Json.createArrayBuilder();
			for(Project p :service.getAllProjects()){
				
				JsonObjectBuilder job =Json.createObjectBuilder();
				job.add("project_id", p.getProjectID())
			    .add("huisnr", p.getHuisNummer())
			    .add("straatnaam", p.getStraatnaam())
			    .add("segment", p.getSegment())
			    .add("type", p.getType())
			    .add("solution", p.getSolution())
			    .add("telefoonnr", p.getTelefoonNummer())
			    .add("medewerkers_id", p.getMedewerkersID())
			    .add("begindatum",  p.getBegindatum())
			    .add("projectNaam", p.getProjectNaam())
			    .add("status", p.getStatus());
				
				jab.add(job);
			}
			JsonArray array = jab.build();
			return array.toString();
		}
		// de get methode om een project op te roepen via het projectID
		@GET
		@Path("{id}")
		@Produces("application/json")
		public String getProjectByCode(@PathParam("id") int id){
			
			ProjectService service = ProjectServiceProvider.getProjectService();
			Project p = service.getProjectByID(id);
			
		
			JsonObjectBuilder job =Json.createObjectBuilder();
			job.add("project_id", p.getProjectID())
		    .add("huisnr", p.getHuisNummer())
		    .add("straatnaam", p.getStraatnaam())
		    .add("segment", p.getSegment())
		    .add("type", p.getType())
		    .add("solution", p.getSolution())
		    .add("telefoonnr", p.getTelefoonNummer())
		    .add("medewerkers_id", p.getMedewerkersID())
		    .add("begindatum",  p.getBegindatum())
		    .add("projectNaam", p.getProjectNaam())
		    .add("status", p.getStatus());
							
			return job.build().toString();
		}
		// de methode om een project toe te voegen met eigen pad voor zekerheid
	    @POST
	    @Path("/newProject")
	    public Response addProject(@FormParam("project_id") int project_id,
	    		@FormParam("huisnr") int huisnr,
	    		@FormParam("medewerkers_id") int medewerkers_id,
	    		@FormParam("projectnaam") String projectnaam,
	    		@FormParam("segment") String segment,
	    		@FormParam("solution") String solution,
	    		@FormParam("status")String status,
	    		@FormParam("straatnaam")String straatnaam,
	    		@FormParam("telefoonnr") int telefoonnr,
	    		@FormParam("type") String type,
	    		@FormParam("begindatum")String begindatum
	    		) throws ParseException{
	    		    	
	    	
	    	
	    	Project newProject = new Project(project_id,huisnr,straatnaam,segment,type,solution,telefoonnr,medewerkers_id,begindatum,projectnaam,status);
 	        ProjectService service = ProjectServiceProvider.getProjectService();
 	        
 	       if(service.getProjectByID(project_id) == null){
	            Project returnProject= service.addProject(newProject);
	            String a = projectToJson(returnProject).build().toString();
	            return Response.ok(a).build();
 	       }else {
	            return Response.status(Response.Status.FOUND).build();
 	       }
	    }
	    //methode omproject te update 
	    @PUT
		@Path("{id}")
		@Produces("application/json")
		public Response updateProject(@PathParam("id") int id,
			@FormParam("med_id") int med_id) {
			
			ProjectService service = ProjectServiceProvider.getProjectService();
			Project p = service.getProjectByID(id);	
			
			if(p != null) {
				p.setMedewerkersID(med_id);
				String a = projectToJson(service.update(p)).build().toString();
				return Response.ok(a).build();
			}
			
			throw new WebApplicationException("Project not found!");
			
		}
	    //methode om status van project te update als op afgerond word gezet
	    @PUT
		@Path("/status/{id}")
		@Produces("application/json")
		public Response updateProjectStatus(@PathParam("id") int id)
					{
			
			ProjectService service = ProjectServiceProvider.getProjectService();
			Project p = service.getProjectByID(id);	
			
			if(p != null) {
				p.setStatus("afgerond");
				String a = projectToJson(service.updateStatus(p)).build().toString();
				return Response.ok(a).build();
			}
			
			throw new WebApplicationException("Country not found!");
			
		}
}


package nl.hu.v1wac.firstapp.webservices;


import java.text.ParseException;

import javax.json.Json;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Project;

@Path("/project")
public class ProjectResource {
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
		
	    @POST
	    @Path("/newProject")
	    public Response addCountry(@FormParam("project_id") int project_id,
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
}


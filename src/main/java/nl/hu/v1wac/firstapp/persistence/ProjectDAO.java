package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Medewerker;
import nl.hu.v1wac.firstapp.model.Project;

public class ProjectDAO extends BaseDAO{
	public Project save(Project project) throws ParseException{
	
		
		  String query = "INSERT INTO projects(project_id, huisnr, straatnaam, segment, type, solution, telefoonnr, medewerkers_id, begindatum, projectnaam, status) VALUES ("
	                + project.getProjectID()+ ", "
	                + project.getHuisNummer()+ ", '"
	                + project.getStraatnaam()+ "', '"
	                + project.getSegment() + "', '"
	                + project.getType()+ "', '"
	                + project.getSolution()+ "', "
	                + project.getTelefoonNummer() + ", "
	                + project.getMedewerkersID() + ", '"
	                + project.getBegindatum() + "', '"
	                + project.getProjectNaam() + "', '"
	                + project.getStatus()+ "');";
	         
	        try (Connection con = super.getConnection()){
	            Statement stmt = con.createStatement();
	            stmt.executeUpdate(query);
	             
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	         
	        return findByID(project.getProjectID());
	}
	

	public List<Project> findAll(){
		List<Project> results = new ArrayList<Project>();
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("select * from projects");
			while (dbResultSet.next()) {
		        int projectID = dbResultSet.getInt("project_id");
		        int huisNummer = dbResultSet.getInt("huisnr");
		        String straatnaam = dbResultSet.getString("straatnaam");
		        String segment = dbResultSet.getString("segment");
		        String type = dbResultSet.getString("type");
		        String solution = dbResultSet.getString("solution");
		        int telefoonNummer = dbResultSet.getInt("telefoonnr");
		        int medewerkersID = dbResultSet.getInt("medewerkers_id");
		        String begindatum = dbResultSet.getString("begindatum");
		        String projectNaam = dbResultSet.getString("projectnaam");
		        String status = dbResultSet.getString("status");
		        
		        Project newProject = new Project(projectID, huisNummer, straatnaam, segment, type, solution, telefoonNummer, medewerkersID, begindatum, projectNaam, status);
		        results.add(newProject);
		      }
	
		}catch (SQLException sqle){
			sqle.printStackTrace();
		}
		return results;
	}
	public Project findByID(int i){
		Project newProject = null;
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("select * from projects where project_id='" + i+"'");
			while (dbResultSet.next()) {
		        int projectID = dbResultSet.getInt("project_id");
		        int huisNummer = dbResultSet.getInt("huisnr");
		        String straatnaam = dbResultSet.getString("straatnaam");
		        String segment = dbResultSet.getString("segment");
		        String type = dbResultSet.getString("type");
		        String solution = dbResultSet.getString("solution");
		        int telefoonNummer = dbResultSet.getInt("telefoonnr");
		        int medewerkersID = dbResultSet.getInt("medewerkers_id");
		        String begindatum = dbResultSet.getString("begindatum");
		        String projectNaam = dbResultSet.getString("projectnaam");
		        String status = dbResultSet.getString("status");
		        
		        newProject = new Project(projectID, huisNummer, straatnaam, segment, type, solution, telefoonNummer, medewerkersID, begindatum, projectNaam, status);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return newProject;
		}
	public Project findByMedID(String ID){
		Project newProject = null;
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("select * from projects where medewerkers_id='" + ID+"'");
			while (dbResultSet.next()) {
		        int projectID = dbResultSet.getInt("project_id");
		        int huisNummer = dbResultSet.getInt("huisnr");
		        String straatnaam = dbResultSet.getString("straatnaam");
		        String segment = dbResultSet.getString("segment");
		        String type = dbResultSet.getString("type");
		        String solution = dbResultSet.getString("solution");
		        int telefoonNummer = dbResultSet.getInt("telefoonnr");
		        int medewerkersID = dbResultSet.getInt("medewerkers_id");
		        String begindatum = dbResultSet.getString("begindatum");
		        String projectNaam = dbResultSet.getString("projectnaam");
		        String status = dbResultSet.getString("status");
		        
		        newProject = new Project(projectID, huisNummer, straatnaam, segment, type, solution, telefoonNummer, medewerkersID, begindatum, projectNaam, status);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return newProject;
		}
	public Project update(Project project){
		String query = "update project set medewerkers_id="+project.getMedewerkersID()+";";
		try (Connection con = super.getConnection()){
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
        	System.out.println("DB connection: failed");
            e.printStackTrace();
        }
         return findByID(project.getProjectID());
    }
}


package nl.hu.v1wac.firstapp.webservices;

import java.text.ParseException;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Project;
import nl.hu.v1wac.firstapp.persistence.ProjectDAO;

public class ProjectService {
	ProjectDAO ProjectDAO = new ProjectDAO();
	
	public List<Project> getAllProjects() {
		return ProjectDAO.findAll();
	}
	
	public Project getProjectByID(int ID) {
		Project result = ProjectDAO.findByID(ID);
			
		return result;
	}
	public Project getProjectMedByID(String ID) {
		Project result = ProjectDAO.findByMedID(ID);
			
		return result;
	}
	public Project addProject(Project project) throws ParseException{
		return ProjectDAO.save(project);
	}
}

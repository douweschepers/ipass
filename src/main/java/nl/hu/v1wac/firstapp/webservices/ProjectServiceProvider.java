package nl.hu.v1wac.firstapp.webservices;

public class ProjectServiceProvider {
	private static ProjectService projectservice = new ProjectService();

	public static ProjectService getProjectService() {
		return projectservice;
	}
}

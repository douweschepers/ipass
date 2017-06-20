package nl.hu.v1wac.firstapp.webservices;

import java.security.Principal;
import javax.ws.rs.core.SecurityContext;


public class MySecurityContext implements SecurityContext{
	private String name;
	 private String rol;
	 private boolean isSecure;

	 public MySecurityContext(String name, String rol, boolean isSecure) {
	 this.name = name;
	 this.rol = rol;
	 }

	 public Principal getUserPrincipal() {
	 return new Principal() {
	 public String getName() { return name; }
	 };
	 }
	 public boolean isUserInRole(String rol) { return rol.equals(this.rol); }
	 public boolean isSecure() { return isSecure; }
	 public String getAuthenticationScheme() { return "Bearer"; }

}

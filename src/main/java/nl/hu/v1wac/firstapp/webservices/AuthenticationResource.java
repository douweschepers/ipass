package nl.hu.v1wac.firstapp.webservices;

import java.security.Key;
import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.v1wac.firstapp.persistence.MedewerkerDAO;

//het pad voor de url om deze classe te vinden
@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();
	
	//post methode om iets te update (het token)
	 @POST
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 public Response authenticateUser(@FormParam("username") String username,
			 						  @FormParam("password") String password) {
	 try {
	 // maak verbinding met DAO
	 MedewerkerDAO dao = new MedewerkerDAO();
	 String rol = dao.findRolForUsernameAndPassword(username, password);

	 if (rol == null) { throw new IllegalArgumentException("No user found!"); }

	 Calendar expiration = Calendar.getInstance();
	 expiration.add(Calendar.MINUTE, 30);
	 
	 System.out.println(rol);

	 String token = Jwts.builder()
	 .setSubject(username)
	 .claim("rol", rol)
	 .setExpiration(expiration.getTime())
	 .signWith(SignatureAlgorithm.HS512, key)
	 .compact();
	 // return de token
	 return Response.ok(token).build();
		 } catch (JwtException |IllegalArgumentException e) {
		 return Response.status(Response.Status.UNAUTHORIZED).build();
		 }
	 }
}

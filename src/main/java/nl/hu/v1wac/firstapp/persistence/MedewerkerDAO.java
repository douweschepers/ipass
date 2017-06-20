package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import nl.hu.v1wac.firstapp.model.Medewerker;

public class MedewerkerDAO extends BaseDAO{
	public List<Medewerker> findAll(){
		List<Medewerker> results = new ArrayList<Medewerker>();
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("select * from medewerkers");
			while (dbResultSet.next()) {
		        int medewerkers_id = dbResultSet.getInt("medewerkers_id");
		        int huisNummer = dbResultSet.getInt("huisnr");
		        String straatnaam = dbResultSet.getString("straatnaam");
		        String voornaam = dbResultSet.getString("voornaam");
		        String achternaam = dbResultSet.getString("achternaam");
		        String rol = dbResultSet.getString("rol");
		        int project_id = dbResultSet.getInt("project_id");
		        String gebruikersnaam = dbResultSet.getString("gebruikersnaam");
		        String wachtwoord = dbResultSet.getString("wachtwoord");
		        
		        Medewerker newMedewerker = new Medewerker(medewerkers_id, huisNummer, straatnaam, voornaam, achternaam, rol, project_id, gebruikersnaam, wachtwoord);
		        results.add(newMedewerker);
		      }
	
		}catch (SQLException sqle){
			sqle.printStackTrace();
		}
		return results;
	}
	public Medewerker findByID(int ID){
		Medewerker newMedewerker = null;
		try(Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("select * from medewerkers where medewerkers_id='" + ID+"'");
			while (dbResultSet.next()) {
				 int medewerkers_id = dbResultSet.getInt("medewerkers_id");
			        int huisNummer = dbResultSet.getInt("huisnr");
			        String straatnaam = dbResultSet.getString("straatnaam");
			        String voornaam = dbResultSet.getString("voornaam");
			        String achternaam = dbResultSet.getString("achternaam");
			        String rol = dbResultSet.getString("rol");
			        int project_id = dbResultSet.getInt("project_id");
			        String gebruikersnaam = dbResultSet.getString("gebruikersnaam");
			        String wachtwoord = dbResultSet.getString("wachtwoord");
			        
			        newMedewerker = new Medewerker(medewerkers_id, huisNummer, straatnaam, voornaam, achternaam, rol, project_id, gebruikersnaam, wachtwoord);
			        
			    }
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return newMedewerker;
		}
	
	public Medewerker update(Medewerker medewerker){
		String query = "update medewerkers set huisnr="+medewerker.getHuisNummer()+
                ", straatnaam='"+medewerker.getStraatNaam()+
                "', voornaam='"+medewerker.getVoornaam()+
                "', achternaam='"+medewerker.getAchternaam()+
                "', rol='"+medewerker.getRol()+
                "', project_id="+medewerker.getProjectID()+
                ", gebruikersnaam='"+medewerker.getGebruikersNaam()+
                "', wachtwoord='"+medewerker.getWachtwoord()+
                "' where medewerkers_id = " + medewerker.getMedewerkerID() + ";";
		try (Connection con = super.getConnection()){
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
        	System.out.println("DB connection: failed");
            e.printStackTrace();
        }
         
        return findByID(medewerker.getMedewerkerID());
    }
	
	public boolean delete(Medewerker medewerker){
		boolean result = false;
		boolean medewerkerExists = findByID(medewerker.getMedewerkerID()) != null;
		
		
		if(medewerkerExists){
			String queryUpdate = "update projects set medewerkers_id= null where medewerkers_id='" + medewerker.getMedewerkerID()+"'";   
			String query= "delete from medewerkers where medewerkers_id = '"+medewerker.getMedewerkerID()+"'";
		
		try (Connection con = getConnection()) {
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate(queryUpdate);
			System.out.println("werkt");
			stmt.executeUpdate(query);  // 1 row updated!
			result = true;
				
			}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			result = false;
		}
		}
		return result;
	}
	public String findRolForUsernameAndPassword(String username, String password) {
		 String rol = null;
		 String query = "SELECT rol FROM medewerkers WHERE username = ? AND password = ?";

		 try (Connection con = super.getConnection()) {

		 PreparedStatement pstmt = con.prepareStatement(query);
		 pstmt.setString(1, username);
		 pstmt.setString(2, password);

		 ResultSet rs = pstmt.executeQuery();
		 if (rs.next())
		 rol = rs.getString("rol");

		 } catch (SQLException sqle) {
		 sqle.printStackTrace();
		 }

		 return rol;
		 }

		
}




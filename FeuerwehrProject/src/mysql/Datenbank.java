package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import einsaetze.Einsatz;
import einsaetze.EinsatzTyp;
import fahrzeuge.Leiterwagen;
import fahrzeuge.TankLoeschfahrzeug;
import fahrzeuge.*;
import menschen.Feuerwehrmensch;
import util.FahrzeugStatus;
import util.FahrzeugTyp;
import util.MitarbeiterStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Datenbank Vebindugs und Dateninitialiserungs sowie Update Klasse
 * How To: MySQL Connection mit dem connect aufbauen, danach daraus entstandenes objekt für andere Methoden nutzen
 * @author Anisa Mecheraoui
 *
 */
public class Datenbank {

	//Nicht Anfassen, Anisa fragen, dann machen
    
	public static void main(String[] args) { //main methode nur temporär zum testen meiner sql methodik
			Connection con = connect();
			
			//Feuerwehrmensch fw = new Feuerwehrmensch(1, null, null, null);
			//fw = updateEinsatz(fw, con, 0);
			/*ArrayList<Feuerwehrmensch> fwList = initFeuerwehrmensch(con);
			System.out.println("Erlaubnis: "+fwList.get(2).getFahrerlaubnis());
			ArrayList<Leiterwagen> lwList = initLeiterwagen(con);
			ArrayList<TankLoeschfahrzeug> lfList = initTankLoeschfahrzeug(con);
			ArrayList<Mannschaftstransporter> mwList=initMannschaftstransporter(con);*/
			Einsatz einsatz = initEinsatz(con);
			System.out.println(einsatz.getMitarbeiter().get(0).getName());
			System.out.println(einsatz.getFahrzeuge().get(0).getFahrzeugName());
	}
	//Ab hier Abwechselnd Status und Einsatz updater der verschiedenen Datentypen
	/**
	 * Updated den Status vom Feuerwehrmenschen in der DB und im Programm
	 * @param fw Feuerwehrmensch welcher geupdated werden soll
	 * @param con MySQL Connection Objekt
	 * @param status neuer MitarbeiterStatus
	 * @return Feuerwehrmensch mit neuem Status
	 */
	public static Feuerwehrmensch updateStatus(Feuerwehrmensch fw, Connection con, MitarbeiterStatus status) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `emp` SET `state`='"+status.toString()+"' WHERE id="+fw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		fw.setMitarbeiterStatus(status);
		return fw;
	}
	/**
	 * Updated die Einsatznummer vom Feuerwehrmenschen in der DB und im Programm
	 * @param fw Feuerwehrmensch welcher geupdated werden soll
	 * @param con MySQL Connection Objekt
	 * @param einsatz Einsatz dem der Feuerwehrmensch zugeordnet werden soll
	 * @return Feuerwehrmensch mit neuer Einsatz ID
	 */
	public static Feuerwehrmensch updateEinsatz(Feuerwehrmensch fw, Connection con, Einsatz einsatz) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `emp` SET `missionID`='"+einsatz.getID()+"' WHERE id="+fw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		fw.setEinsatzID(einsatz.getID());
		return fw;
	}
	/**
	 * 
	 * @param lw Updated den Status vom Leiterwagen in der DB und im Programm
	 * @param con Leiterwagen welcher geupdated werden soll
	 * @param status MySQL Connection Objekt
	 * @return Leiterwagen mit neuem Status
	 */
	public static Leiterwagen updateStatus(Leiterwagen lw, Connection con, FahrzeugStatus status) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `turntableLadder` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+lw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		lw.setFahrzeugStatus(status);
		return lw;
	}
	/**
	 * Updated die Einsatznummer vom Leiterwagen in der DB und im Programm
	 * @param lw Leiterwagen welcher geupdated werden soll
	 * @param con MySQL Connection Objekt
	 * @param einsatz Einsatz dem der Feuerwehrmensch zugeordnet werden soll
	 * @return Leiterwagen mit neuer Einsatz ID
	 */
	public static Leiterwagen updateEinsatz(Leiterwagen lw, Connection con, Einsatz einsatz) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `turntableLadder` SET `missionID`='"+einsatz.getID()+"' WHERE id="+lw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		lw.setEinsatzID(einsatz.getID());
		return lw;
	}
	/**
	 * 
	 * @param lw Updated den Status vom Mannschaftstransporter in der DB und im Programm
	 * @param con Mannschaftstransporter welcher geupdated werden soll
	 * @param status MySQL Connection Objekt
	 * @return
	 */
	public static Mannschaftstransporter updateStatus(Mannschaftstransporter lw, Connection con, FahrzeugStatus status) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `crewTransport` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+lw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		lw.setFahrzeugStatus(status);
		return lw;
	}
	/**
	 * Updated die Einsatznummer vom Mannschaftstransporter in der DB und im Programm
	 * @param lw Mannschaftstransporter welcher geupdated werden soll
	 * @param con MySQL Connection Objekt
	 * @param einsatz Einsatz dem der Mannschaftstransporter zugeordnet werden soll
	 * @return Mannschaftstransporter mit neuer Einsatz ID
	 */
	public static Mannschaftstransporter updateEinsatz(Mannschaftstransporter lw, Connection con, Einsatz einsatz) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `crewTransport` SET `missionID`='"+einsatz.getID()+"' WHERE id="+lw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		lw.setEinsatzID(einsatz.getID());
		return lw;
	}
	/**
	 * 
	 * @param lw Updated den Status vom EinsatzLeitfahrzeug in der DB und im Programm
	 * @param con EinsatzLeitfahrzeug welcher geupdated werden soll
	 * @param status MySQL Connection Objekt
	 * @return
	 */
	public static EinsatzLeitfahrzeug updateStatus(EinsatzLeitfahrzeug lw, Connection con, FahrzeugStatus status) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `commandVehicle` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+lw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		lw.setFahrzeugStatus(status);
		return lw;
	}
	/**
	 * Updated die Einsatznummer vom EinsatzLeitfahrzeug in der DB und im Programm
	 * @param lw EinsatzLeitfahrzeug welcher geupdated werden soll
	 * @param con MySQL EinsatzLeitfahrzeug Objekt
	 * @param einsatz Einsatz dem der EinsatzLeitfahrzeug zugeordnet werden soll
	 * @return EinsatzLeitfahrzeug mit neuer Einsatz ID
	 */
	public static EinsatzLeitfahrzeug updateEinsatz(EinsatzLeitfahrzeug lw, Connection con, Einsatz einsatz) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `commandVehicle` SET `missionID`='"+einsatz.getID()+"' WHERE id="+lw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		lw.setEinsatzID(einsatz.getID());
		return lw;
	}
	
	
	//Ab hier Init Methoden
	/**
	 * Initialisiert Feuerwehrmenschen aus DB
	 * @param con MySQL Connection Object
	 * @return Arraylist der Initialisierten Feuerwehrmenschen
	 */
	public static ArrayList<Feuerwehrmensch> initFeuerwehrmensch(Connection con) {
		ArrayList<Feuerwehrmensch> fwList = new ArrayList<Feuerwehrmensch>();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM emp;");
			while(rs.next()){
				Feuerwehrmensch fw;
				System.out.println("ID: "+rs.getInt(1) + " Status: " +
				                   rs.getString(2) + " Name: " +
				                   rs.getString(3) + " Fahrerlaubnis: " +
				                   rs.getString(4) + " EinsatzID: "+
				                   rs.getString(5));
				fw = new Feuerwehrmensch(rs.getInt(1),MitarbeiterStatus.valueOf(rs.getString(2)), rs.getString(3), FahrzeugTyp.valueOf(rs.getString(4)), rs.getInt(5));
				fwList.add(fw);
				}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return fwList;
	}
	/**
	 * Initialisiert Leiterwagen aus DB
	 * @param con MySQL Connection Objekt
	 * @return Arraylist der Initialisierten Leiterwagen
	 */
	public static ArrayList<Leiterwagen> initLeiterwagen(Connection con) {
		ArrayList<Leiterwagen> fwList = new ArrayList<Leiterwagen>();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM turntableLadder;");
			while(rs.next()){
				Leiterwagen lw;
				System.out.println("ID: "+rs.getInt(1) + " Sitze: " +
				                   rs.getString(2) + " FahrzeugTyp: " +
				                   rs.getString(3) + " FahrzeugStatus: " +
				                   rs.getString(4) + " Kennzeichen: "+
				                   rs.getString(5) + " maxLeiter: " +
				                   rs.getString(6) + " EinsatzID: "+
				                   rs.getString(7));
				lw = new Leiterwagen(rs.getInt(1),rs.getInt(2),FahrzeugTyp.valueOf(rs.getString(3)), FahrzeugStatus.valueOf(rs.getString(4)), rs.getString(5), rs.getInt(6), rs.getInt(7));
				fwList.add(lw);
				}
			return fwList;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return fwList;
	}
	/**
	 * Initialisiert TankLoeschfahrzeug aus DB
	 * @param con MySQL Connection Objekt
	 * @return Arraylist der Initialisierten TankLoeschfahrzeug
	 */
	public static ArrayList<TankLoeschfahrzeug> initTankLoeschfahrzeug(Connection con) {
		ArrayList<TankLoeschfahrzeug> fwList = new ArrayList<TankLoeschfahrzeug>();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM fireEngine;");
			while(rs.next()){
				TankLoeschfahrzeug lw;
				System.out.println("ID: "+rs.getInt(1) + " Sitze: " +
				                   rs.getString(2) + " FahrzeugTyp: " +
				                   rs.getString(3) + " FahrzeugStatus: " +
				                   rs.getString(4) + " Kennzeichen: "+
				                   rs.getString(5) + " maxTank: " +
				                   rs.getString(6) + " EinsatzID: "+
				                   rs.getString(7));
				lw = new TankLoeschfahrzeug(rs.getInt(1),rs.getInt(2),FahrzeugTyp.valueOf(rs.getString(3)), FahrzeugStatus.valueOf(rs.getString(4)), rs.getString(5), rs.getInt(6), rs.getInt(7));
				fwList.add(lw);
				}
			return fwList;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return fwList;
	}
	/**
	 * Initialisiert Mannschaftstransporter aus DB
	 * @param con MySQL Connection Objekt
	 * @return Arraylist der Initialisierten Mannschaftstransporter
	 */
	public static ArrayList<Mannschaftstransporter> initMannschaftstransporter(Connection con) {
		ArrayList<Mannschaftstransporter> fwList = new ArrayList<Mannschaftstransporter>();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM crewTransport;");
			while(rs.next()){
				Mannschaftstransporter lw;
				System.out.println("ID: "+rs.getInt(1) + " Sitze: " +
				                   rs.getString(2) + " FahrzeugTyp: " +
				                   rs.getString(3) + " FahrzeugStatus: " +
				                   rs.getString(4) + " Kennzeichen: "+
				                   rs.getString(5) + " manufactureYear: " +
				                   rs.getString(6) + " EinsatzID: "+
				                   rs.getString(7));
				lw = new Mannschaftstransporter(rs.getInt(1),rs.getInt(2),FahrzeugTyp.valueOf(rs.getString(3)), FahrzeugStatus.valueOf(rs.getString(4)), rs.getString(5), rs.getInt(6), rs.getInt(7));
				fwList.add(lw);
				}
			return fwList;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return fwList;
	}
	/**
	 * Initialisiert EinsatzLeitfahrzeug aus DB
	 * @param con MySQL Connection Objekt
	 * @return Arraylist der Initialisierten EinsatzLeitfahrzeug
	 */
	public static ArrayList<EinsatzLeitfahrzeug> initEinsatzLeitfahrzeug(Connection con) {
		ArrayList<EinsatzLeitfahrzeug> fwList = new ArrayList<EinsatzLeitfahrzeug>();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM commandVehicle;");
			while(rs.next()){
				EinsatzLeitfahrzeug lw;
				System.out.println("ID: "+rs.getInt(1) + " Sitze: " +
				                   rs.getString(2) + " FahrzeugTyp: " +
				                   rs.getString(3) + " FahrzeugStatus: " +
				                   rs.getString(4) + " Kennzeichen: "+
				                   rs.getString(5) + " Dienstgrad: " +
				                   rs.getString(6) + " EinsatzID: "+
				                   rs.getString(7));
				lw = new EinsatzLeitfahrzeug(rs.getInt(1),rs.getInt(2),FahrzeugTyp.valueOf(rs.getString(3)), FahrzeugStatus.valueOf(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getInt(7));
				fwList.add(lw);
				}
			return fwList;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return fwList;
	}
	
	public static Einsatz initEinsatz(Connection con) {
		Einsatz einsatz;
		EinsatzTyp einsatzTyp = null;
		ArrayList<Fahrzeug> tempList = new ArrayList<Fahrzeug>();
		ArrayList<Fahrzeug> fList = new ArrayList<Fahrzeug>();
		ArrayList<Feuerwehrmensch> feuerList = new ArrayList<Feuerwehrmensch>();
		boolean istVorschlag = false;
		int id=0;
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM mission;");
			while(rs.next()){
				System.out.println("ID: "+rs.getInt(1) + " istVorschlag: " +
		                   rs.getString(2) + " minFeuerwehrmensch: " +
		                   rs.getString(3) + " minEinsatzleitwagen: " +
		                   rs.getString(4) + " minLoeschfahrzeug: "+
		                   rs.getString(5) + " minManschaftstransporter: " +
		                   rs.getString(6) + " minLeiterwagen: "+
		                   rs.getString(7));
				einsatzTyp=new EinsatzTyp(rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
				id=rs.getInt(1);
				if(rs.getInt(2)==0) {
					istVorschlag = false;
				}else {
					istVorschlag=true;
				}
				}
			//ArrayList Fahrzeuge erstellen
			ArrayList<EinsatzLeitfahrzeug> elList = initEinsatzLeitfahrzeug(con);
			ArrayList<Mannschaftstransporter> mList = initMannschaftstransporter(con);
			ArrayList<Leiterwagen> lList = initLeiterwagen(con);
			ArrayList<TankLoeschfahrzeug> tList=initTankLoeschfahrzeug(con);
			tempList.addAll(elList);
			tempList.addAll(mList);
			tempList.addAll(lList);
			tempList.addAll(tList);
			for(int i = 0; i<tempList.size(); i++) { // hier filtere ich nach den Fahrzeugen die am Einsatz mitmachen
				if(tempList.get(i).getEinsatzID()==id) {
					fList.add(tempList.get(i));
				}
			}
			//Feuerwehrmenschen init ohne neue Mthode da technik wie bei Fahrzeuge nicht klappt
			Statement stm2 = con.createStatement();
			ResultSet rs2 = stm2.executeQuery("SELECT * FROM emp WHERE missionID="+id+";");
			System.out.println("SELECT * FROM emp WHERE missionID="+id+";");
			while(rs2.next()){
				Feuerwehrmensch fw;
				System.out.println("ID: "+rs2.getInt(1) + " Status: " +
				                   rs2.getString(2) + " Name: " +
				                   rs2.getString(3) + " Fahrerlaubnis: " +
				                   rs2.getString(4) + " EinsatzID: "+
				                   rs2.getString(5));
				fw = new Feuerwehrmensch(rs2.getInt(1),MitarbeiterStatus.valueOf(rs2.getString(2)), rs2.getString(3), FahrzeugTyp.valueOf(rs2.getString(4)), rs2.getInt(5));
				feuerList.add(fw);
			}
			einsatz = new Einsatz(einsatzTyp, fList, feuerList, istVorschlag, id);
			return einsatz;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}

	/**
	 * Verbindet sich mit der Datenbank
	 * @return MySQL Connection Objekt
	 */
	public static Connection connect() {
		String url = "jdbc:mysql://remotemysql.com/DnPTNNcyD1";
	    String user = "DnPTNNcyD1";
	    String pass = "003Fxxy6zg";
		try {
		    Connection con = DriverManager.getConnection(url, user, pass);
		    System.out.println("Verbindung erfolgreich hergestellt");
		    return con;
		    } catch (SQLException e) {
		    System.out.println(e.getMessage());
		    }
		return null;
	}

}

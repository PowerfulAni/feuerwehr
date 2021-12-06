package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import einsaetze.Einsatz;
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
			//ArrayList<Feuerwehrmensch> fwList = initFeuerwehrmensch(con);
			//System.out.println("Name: "+fwList.get(2).getName());
			ArrayList<Leiterwagen> lwList = initLeiterwagen(con);
			ArrayList<TankLoeschfahrzeug> lfList = initTankLoeschfahrzeug(con);
			ArrayList<Mannschaftstransporter> mwList=initMannschaftstransporter(con);
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
				if(rs.getString(2)=="Einsatz"&&rs.getString(4)=="PKW") {
					fw = new Feuerwehrmensch(rs.getInt(1),MitarbeiterStatus.Einsatz, rs.getString(3), FahrzeugTyp.PKW, rs.getInt(5));
				}else if(rs.getString(2)=="Bereit"&&rs.getString(4)=="PKW") {
					fw = new Feuerwehrmensch(rs.getInt(1),MitarbeiterStatus.Bereit, rs.getString(3), FahrzeugTyp.PKW, rs.getInt(5));
				}else if(rs.getString(2)=="Urlaub"&&rs.getString(4)=="PKW") {
					fw = new Feuerwehrmensch(rs.getInt(1),MitarbeiterStatus.Urlaub, rs.getString(3), FahrzeugTyp.PKW, rs.getInt(5));
				}else if(rs.getString(2)=="Krank"&&rs.getString(4)=="PKW") {
					fw = new Feuerwehrmensch(rs.getInt(1),MitarbeiterStatus.Krank, rs.getString(3), FahrzeugTyp.PKW, rs.getInt(5));
				}else if(rs.getString(2)=="Einsatz"&&rs.getString(4)=="LKW") {
					fw = new Feuerwehrmensch(rs.getInt(1),MitarbeiterStatus.Einsatz, rs.getString(3), FahrzeugTyp.LKW);
				}else if(rs.getString(2)=="Bereit"&&rs.getString(4)=="LKW") {
					fw = new Feuerwehrmensch(rs.getInt(1),MitarbeiterStatus.Bereit, rs.getString(3), FahrzeugTyp.LKW, rs.getInt(5));
				}else if(rs.getString(2)=="Urlaub"&&rs.getString(4)=="LKW") {
					fw = new Feuerwehrmensch(rs.getInt(1),MitarbeiterStatus.Urlaub, rs.getString(3), FahrzeugTyp.LKW, rs.getInt(5));
				}else if(rs.getString(2)=="Krank"&&rs.getString(4)=="LKW") {
					fw = new Feuerwehrmensch(rs.getInt(1),MitarbeiterStatus.Krank, rs.getString(3), FahrzeugTyp.LKW, rs.getInt(5));
				}else {
					fw=new Feuerwehrmensch(0, null, rs.getString(3), null);
				}
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
				if(rs.getString(4).equals("Einsatz")) {
					lw = new Leiterwagen(rs.getInt(1),rs.getInt(2),FahrzeugTyp.LKW, FahrzeugStatus.Einsatz, rs.getString(5), rs.getInt(6), rs.getInt(7));
				}else if(rs.getString(4).equals("Bereit")) {
					lw = new Leiterwagen(rs.getInt(1),rs.getInt(2),FahrzeugTyp.LKW, FahrzeugStatus.Bereit, rs.getString(5), rs.getInt(6), rs.getInt(7));
				}else if(rs.getString(4).equals("Wartung")) {
					lw = new Leiterwagen(rs.getInt(1),rs.getInt(2),FahrzeugTyp.LKW, FahrzeugStatus.Wartung, rs.getString(5), rs.getInt(6), rs.getInt(7));
				}else {
					lw=new Leiterwagen(0, 0, null, null, null, 0);
				}
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
				if(rs.getString(4).equals("Einsatz")) {
					lw = new TankLoeschfahrzeug(rs.getInt(1),rs.getInt(2),FahrzeugTyp.LKW, FahrzeugStatus.Einsatz, rs.getString(5), rs.getInt(6), rs.getInt(7));
				}else if(rs.getString(4).equals("Bereit")) {
					lw = new TankLoeschfahrzeug(rs.getInt(1),rs.getInt(2),FahrzeugTyp.LKW, FahrzeugStatus.Bereit, rs.getString(5), rs.getInt(6), rs.getInt(7));
				}else if(rs.getString(4).equals("Wartung")) {
					lw = new TankLoeschfahrzeug(rs.getInt(1),rs.getInt(2),FahrzeugTyp.LKW, FahrzeugStatus.Wartung, rs.getString(5), rs.getInt(6), rs.getInt(7));
				}else {
					lw=new TankLoeschfahrzeug(0, 0, null, null, null, 0);
				}
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
				if(rs.getString(4).equals("Einsatz")) {
					lw = new Mannschaftstransporter(rs.getInt(1),rs.getInt(2),FahrzeugTyp.LKW, FahrzeugStatus.Einsatz, rs.getString(5), rs.getInt(6), rs.getInt(7));
				}else if(rs.getString(4).equals("Bereit")) {
					lw = new Mannschaftstransporter(rs.getInt(1),rs.getInt(2),FahrzeugTyp.LKW, FahrzeugStatus.Bereit, rs.getString(5), rs.getInt(6), rs.getInt(7));
				}else if(rs.getString(4).equals("Wartung")) {
					lw = new Mannschaftstransporter(rs.getInt(1),rs.getInt(2),FahrzeugTyp.LKW, FahrzeugStatus.Wartung, rs.getString(5), rs.getInt(6), rs.getInt(7));
				}else {
					lw=new Mannschaftstransporter(0, 0, null, null, null, 0);
				}
				fwList.add(lw);
				}
			return fwList;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return fwList;
	}
	//Morgen weitermachen mit Einsatzleitfahrzeug
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

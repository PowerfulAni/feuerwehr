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
 * Datenbank Verwaltungs Klasse
 * How To: MySQL Connection mit dem connect aufbauen, danach daraus entstandenes objekt für andere Methoden nutzen
 *
 */
public class Datenbank {

    public Datenbank() {
    	
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
	public static Feuerwehrmensch updateEinsatz(Feuerwehrmensch fw, Connection con, int id) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `emp` SET `missionID`='"+id+"' WHERE id="+fw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		fw.setEinsatzID(id);
		return fw;
	}
	
	public static void updateEinsatz(Fahrzeug fg, Connection con, int id) {
		PreparedStatement bs;
		String cmd="";
		switch(fg.getClass().toString()) {
		case "class fahrzeuge.EinsatzLeitfahrzeug":
			cmd= "UPDATE `commandVehicle` SET `missionID`='"+id+"' WHERE id="+fg.getID()+";";
			break;
		case "class fahrzeuge.Leiterwagen":
			cmd="UPDATE `turntableLadder` SET `missionID`='"+id+"' WHERE id="+fg.getID()+";";
			break;
		case "class fahrzeuge.Mannschaftstransporter":
			cmd = "UPDATE `crewTransport` SET `missionID`='"+id+"' WHERE id="+fg.getID()+";";
			break;
		case "class fahrzeuge.TankLoeschfahrzeug":
			cmd = "UPDATE `fireEngine` SET `missionID`='"+id+"' WHERE id="+fg.getID()+";";
			break;
		}
		try {
			bs = con.prepareStatement(cmd);
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateStatus(Fahrzeug fg, Connection con, FahrzeugStatus status) {
		PreparedStatement bs;
		String cmd="";
		System.out.println(fg.getClass().toString());
		switch(fg.getClass().toString()) {
		case "class fahrzeuge.EinsatzLeitfahrzeug":
			cmd= "UPDATE `commandVehicle` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+fg.getID()+";";
			break;
		case "class fahrzeuge.Leiterwagen":
			cmd="UPDATE `turntableLadder` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+fg.getID()+";";
			break;
		case "class fahrzeuge.Mannschaftstransporter":
			cmd = "UPDATE `crewTransport` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+fg.getID()+";";
			break;
		case "class fahrzeuge.TankLoeschfahrzeug":
			cmd = "UPDATE `commandVehicle` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+fg.getID()+";";
			break;
		}
		try {
			bs = con.prepareStatement(cmd);
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	//Ab hier Insert Methode
	/**
	 * Fügt Einsatz in die DB ein und updated zugehörige ArrayLists in DB
	 * @param con MySQL Connect Objekt
	 * @param fwList ArrayList der zugehörigen Feuerwehrmenschen
	 * @param fList ArrayList der zugehörigen Fahrzeuge
	 * @param einsatzTyp EinsatzTyp
	 * @return ID des einsatzes in DB
	 */
	public static int insertEinsatz(Connection con, ArrayList<Feuerwehrmensch> fwList, ArrayList<Fahrzeug> fList, EinsatzTyp einsatzTyp) {
		PreparedStatement bs;
		int id=0;
		try {
			bs = con.prepareStatement("INSERT INTO `PowerfulAni-906454`.`mission` (`name`) VALUES ('"+einsatzTyp.getName()+"');");
			bs.executeUpdate();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT LAST_INSERT_ID();");
			while(rs.next()){
				Feuerwehrmensch fw;
				System.out.println("ID: "+rs.getInt(1));
				id=rs.getInt(1);
				}
			int count=0;
			while(fwList.size() > count) {
				Feuerwehrmensch fw = updateStatus(fwList.get(count), con, MitarbeiterStatus.Einsatz);
				fw= updateEinsatz(fwList.get(count), con,id);
				fwList.get(count).setMitarbeiterStatus(fw.getMitarbeiterStatus());
				fwList.get(count).setEinsatzID(id);
				count++;
			}
			count=0;
			while(fList.size()>count) {
				updateStatus(fList.get(count), con, FahrzeugStatus.Einsatz);
				updateEinsatz(fList.get(count), con, id);
				count++;
			}
			return id;
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return id;
	}

	//Ab hier DELETE Methode
	/**
	 * Löscht Einsatz aus DB und stellt zugehörige 
	 * @param con  MySQL Connect Objekt
	 * @param einsatz Einsatz welcher aus DB gelöscht werden soll
	 */
	public static void deleteEinsatz(Connection con,Einsatz einsatz) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("DELETE FROM `PowerfulAni-906454`.`mission` WHERE  `id`="+einsatz.getID()+";");
			bs.executeUpdate();
			Statement stm = con.createStatement();
			int count=0;
			while(einsatz.getMitarbeiter().size() > count) {
				Feuerwehrmensch fw = updateStatus(einsatz.getMitarbeiter().get(count), con, MitarbeiterStatus.Bereit);
				fw= updateEinsatz(einsatz.getMitarbeiter().get(count), con,0);
				einsatz.getMitarbeiter().get(count).setMitarbeiterStatus(fw.getMitarbeiterStatus());
				einsatz.getMitarbeiter().get(count).setEinsatzID(0);
				count++;
			}
			count=0;
			while(einsatz.getFahrzeuge().size()>count) {
				updateStatus(einsatz.getFahrzeuge().get(count), con, FahrzeugStatus.Bereit);
				updateEinsatz(einsatz.getFahrzeuge().get(count), con, 0);
				count++;
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	//Ab hier Init Methoden
	/**
	 * Initialisiert Fahrzeuge aus der DB
	 * @param con MySQL Connection Object
	 * @return ArrayList mit ALLEN Fahrzeugen aus der DB
	 */
	public static ArrayList<Fahrzeug> initFahrzeug(Connection con){
		ArrayList<Fahrzeug> fList = new ArrayList<Fahrzeug>();
		fList.addAll(initLeiterwagen(con));
		fList.addAll(initTankLoeschfahrzeug(con));
		fList.addAll(initMannschaftstransporter(con));
		fList.addAll(initEinsatzLeitfahrzeug(con));
		fList.forEach((n) -> System.out.println(n.getKennzeichen()));
		return fList;
	}
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
	
	public static ArrayList<EinsatzDaten> initEinsatz(Connection con){
		ArrayList<EinsatzDaten> edList = new ArrayList<EinsatzDaten>();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM mission;");
			while(rs.next()){
				System.out.println("ID: "+rs.getInt(1) + " istVorschlag: " +
		                   rs.getString(2));
				EinsatzDaten ed = new EinsatzDaten(rs.getInt(1),rs.getString(2));
				edList.add(ed);
			}
			return edList;
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
		String url = "jdbc:mysql://134.255.253.141/PowerfulAni-906454";
	    String user = "PowerfulAni-906454";
	    String pass = "PeuDrMdjpmjFdfmnKBuymrJ6K";
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

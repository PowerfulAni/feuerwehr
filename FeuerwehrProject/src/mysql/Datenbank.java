package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

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
 * Datenbank-Verwaltungsklasse
 * How To: MySQL Connection mit dem connect aufbauen, danach daraus entstandenes objekt für andere Methoden nutzen
 *
 */
public class Datenbank {
	private static Connection con;

    public Datenbank() {
    	
    }
	//Ab hier Abwechselnd Status und Einsatz updater der verschiedenen Datentypen
	/**
	 * Updated den Status vom Feuerwehrmenschen in der DB und im Programm
	 * @param fw Feuerwehrmensch welcher geupdated werden soll
	 * @param status neuer MitarbeiterStatus
	 * @return Feuerwehrmensch mit neuem Status
	 */
	public static Feuerwehrmensch updateStatus(Feuerwehrmensch fw, MitarbeiterStatus status) {
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
	 * @param einsatz Einsatz dem der Feuerwehrmensch zugeordnet werden soll
	 * @return Feuerwehrmensch mit neuer Einsatz ID
	 */
	public static Feuerwehrmensch updateEinsatz(Feuerwehrmensch fw, int id) {
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
	
	public static void updateEinsatz(Fahrzeug fg, int id) {
		PreparedStatement bs;
		String cmd="";
		switch(fg.getFahrzeugName()) {
			case "Einsatz-Leitfahrzeug":
				cmd= "UPDATE `commandVehicle` SET `missionID`='"+id+"' WHERE id="+fg.getID()+";";
				break;
			case "Leiterwagen":
				cmd="UPDATE `turntableLadder` SET `missionID`='"+id+"' WHERE id="+fg.getID()+";";
				break;
			case "Mannschaftstransporter":
				cmd = "UPDATE `crewTransport` SET `missionID`='"+id+"' WHERE id="+fg.getID()+";";
				break;
			case "Tank-Löschfahrzeug":
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
	
	public static void updateStatus(Fahrzeug fg, FahrzeugStatus status) {
		PreparedStatement bs;
		String cmd="";
		switch(fg.getFahrzeugName()) {
		case "Einsatz-Leitfahrzeug":
			cmd= "UPDATE `commandVehicle` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+fg.getID()+";";
			break;
		case "Leiterwagen":
			cmd="UPDATE `turntableLadder` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+fg.getID()+";";
			break;
		case "Mannschaftstransporter":
			cmd = "UPDATE `crewTransport` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+fg.getID()+";";
			break;
		case "Tank-Löschfahrzeug":
			cmd = "UPDATE `fireEngine` SET `vehicleStatus`='"+status.toString()+"' WHERE id="+fg.getID()+";";
			break;
		}
		try {
			bs = con.prepareStatement(cmd);
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Fügt Einsatz in die DB ein und updated zugehörige ArrayLists in DB
	 * @param con MySQL Connect Objekt
	 * @param fwList ArrayList der zugehörigen Feuerwehrmenschen
	 * @param fList ArrayList der zugehörigen Fahrzeuge
	 * @param einsatzTyp EinsatzTyp
	 * @return ID des einsatzes in DB
	 */
	public static int insertEinsatz(ArrayList<Feuerwehrmensch> fwList, ArrayList<Fahrzeug> fList, EinsatzTyp einsatzTyp) {
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
			/*int count=0;
			while(fwList.size() > count) {
				Feuerwehrmensch fw = updateStatus(fwList.get(count), MitarbeiterStatus.Einsatz);
				fw= updateEinsatz(fwList.get(count),id);
				fwList.get(count).setMitarbeiterStatus(fw.getMitarbeiterStatus());
				fwList.get(count).setEinsatzID(id);
				count++;
			}
			count=0;
			while(fList.size()>count) {
				updateStatus(fList.get(count), FahrzeugStatus.Einsatz);
				updateEinsatz(fList.get(count), id);
				count++;
			}*/
			return id;
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	/**
	 * Löscht Einsatz aus DB und stellt zugehörige 
	 * @param con  MySQL Connect Objekt
	 * @param einsatz Einsatz welcher aus DB gelöscht werden soll
	 */
	public static void deleteEinsatz(Einsatz einsatz) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("DELETE FROM `PowerfulAni-906454`.`mission` WHERE  `id`="+einsatz.getID()+";");
			bs.executeUpdate();
			Statement stm = con.createStatement();
			/*int count=0;
			while(einsatz.getMitarbeiter().size() > count) {
				Feuerwehrmensch fw = updateStatus(einsatz.getMitarbeiter().get(count), MitarbeiterStatus.Bereit);
				fw= updateEinsatz(einsatz.getMitarbeiter().get(count),0);
				einsatz.getMitarbeiter().get(count).setMitarbeiterStatus(fw.getMitarbeiterStatus());
				einsatz.getMitarbeiter().get(count).setEinsatzID(0);
				count++;
			}
			count=0;
			while(einsatz.getFahrzeuge().size()>count) {
				updateStatus(einsatz.getFahrzeuge().get(count), FahrzeugStatus.Bereit);
				updateEinsatz(einsatz.getFahrzeuge().get(count), 0);
				count++;
			}*/
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
	public static ArrayList<Fahrzeug> initFahrzeug(){
		ArrayList<Fahrzeug> fList = new ArrayList<Fahrzeug>();
		fList.addAll(initLeiterwagen());
		fList.addAll(initTankLoeschfahrzeug());
		fList.addAll(initMannschaftstransporter());
		fList.addAll(initEinsatzLeitfahrzeug());
		fList.forEach((n) -> System.out.println(n.getKennzeichen()));
		return fList;
	}
	
	/**
	 * Initialisiert Feuerwehrmenschen aus DB
	 * @param con MySQL Connection Object
	 * @return Arraylist der Initialisierten Feuerwehrmenschen
	 */
	public static ArrayList<Feuerwehrmensch> initFeuerwehrmensch() {
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
	 * @return Arraylist der Initialisierten Leiterwagen
	 */
	public static ArrayList<Leiterwagen> initLeiterwagen() {
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
	 * @return Arraylist der Initialisierten TankLoeschfahrzeug
	 */
	public static ArrayList<TankLoeschfahrzeug> initTankLoeschfahrzeug() {
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
	 * @return Arraylist der Initialisierten Mannschaftstransporter
	 */
	public static ArrayList<Mannschaftstransporter> initMannschaftstransporter() {
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
	 * @return Arraylist der Initialisierten EinsatzLeitfahrzeug
	 */
	public static ArrayList<EinsatzLeitfahrzeug> initEinsatzLeitfahrzeug() {
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
	
	public static ArrayList<EinsatzDaten> initEinsatz(){
		ArrayList<EinsatzDaten> edList = new ArrayList<EinsatzDaten>();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM mission;");
			while(rs.next()){
				System.out.println("ID: "+rs.getInt(1) + " Einsatz: " +
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
	 * @return Status ob verbunden wererden konnte
	 */
	public static Boolean connect() {
		String url = "jdbc:mysql://134.255.253.141/PowerfulAni-906454";
	    String user = "PowerfulAni-906454";
	    String pass = "PeuDrMdjpmjFdfmnKBuymrJ6K";
		try {
		    con = DriverManager.getConnection(url, user, pass);
		    System.out.println("Verbindung erfolgreich hergestellt");
		    return true;
		    } catch (SQLException e) {
		    System.out.println(e.getMessage());
		    }
		return false;
	}

}

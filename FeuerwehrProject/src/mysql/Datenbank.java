package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import fahrzeuge.Leiterwagen;
import menschen.Feuerwehrmensch;
import util.FahrzeugStatus;
import util.FahrzeugTyp;
import util.MitarbeiterStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Datenbank {

	//Nicht Anfassen, Anisa fragen, dann machen
    
	public static void main(String[] args) { //main methode nur temporär zum testen meiner sql methodik
			Connection con = connect();
			
			Feuerwehrmensch fw = new Feuerwehrmensch(1, null, null, null);
			fw = updateFeuerwehrmenschEinsatz(fw, con, 0);
			ArrayList<Feuerwehrmensch> fwList = initFeuerwehrmensch(con);
			System.out.println("Name: "+fwList.get(2).getName());
			ArrayList<Leiterwagen> lwList = initLeiterwagen(con);
			System.out.println(lwList.get(0).getKennzeichen());
	}
	
	public static Feuerwehrmensch updateFeuerwehrmenschStatus(Feuerwehrmensch fw, Connection con, MitarbeiterStatus status) {
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
	
	public static Feuerwehrmensch updateFeuerwehrmenschEinsatz(Feuerwehrmensch fw, Connection con, int einsatz) {
		PreparedStatement bs;
		try {
			bs = con.prepareStatement("UPDATE `emp` SET `missionID`='"+einsatz+"' WHERE id="+fw.getID()+";");
			bs.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		fw.setEinsatzID(einsatz);
		return fw;
	}
	
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
			System.out.println("Zeichen: "+fwList.get(0).getKennzeichen());
			return fwList;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return fwList;
	}
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

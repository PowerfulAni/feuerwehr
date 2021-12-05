package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import menschen.Feuerwehrmensch;
import util.FahrzeugStatus;
import util.FahrzeugTyp;
import util.MitarbeiterStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Datenbank {

	//Nicht Anfassen, Anisa fragen, dann machen
    
	public static void main(String[] args) {
			Connection con = connect();
			
			Feuerwehrmensch fw = new Feuerwehrmensch(1, null, null, null);
			fw = updateFeuerwehrmenschStatus(fw, con, MitarbeiterStatus.Einsatz);
			ArrayList<Feuerwehrmensch> fwList = initFeuerwehrmensch(con);
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

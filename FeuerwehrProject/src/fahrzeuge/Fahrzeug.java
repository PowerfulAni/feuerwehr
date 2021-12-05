package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public abstract class Fahrzeug {
	
	private int sitzplaetze;
	private boolean inWartung;
	private FahrzeugTyp fahrzeugTyp;
	private FahrzeugStatus fahrzeugStatus;
	private String kennzeichen;
	private int id;
	private int einsatzID=0;
	
	public Fahrzeug(int id, int sitzplaetze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, String kennzeichen ) {
		
		this.id = id;
		this.sitzplaetze = sitzplaetze;
		this.fahrzeugTyp = fahrzeugTyp;
		this.inWartung = inWartung;
		this.fahrzeugStatus = fahrzeugstatus;
		this.kennzeichen = kennzeichen;
	}
	public Fahrzeug(int id, int sitzplaetze, FahrzeugTyp fahrzeugTyp, Boolean inWartung, FahrzeugStatus fahrzeugstatus, String kennzeichen, int einsatzID ) {
		
		this.id = id;
		this.sitzplaetze = sitzplaetze;
		this.fahrzeugTyp = fahrzeugTyp;
		this.inWartung = inWartung;
		this.fahrzeugStatus = fahrzeugstatus;
		this.kennzeichen = kennzeichen;
		this.einsatzID = einsatzID;
	}
	public int getID() {
		return id;
	}
	public int getEinsatzID() {
		return einsatzID;
	}
	public void setEinsatzID(int einsatzID) {
		this.einsatzID=einsatzID;
	}
	public int getSitzplaetze () {
		
		return this.sitzplaetze;
	}
	public FahrzeugTyp getFahrzeugTyp () {
		
		return this.fahrzeugTyp;
	}
	public boolean getInWartung () {
		
		return this.inWartung; 
	}
	public FahrzeugStatus getFahrzeugStatus () {
		
		return this.fahrzeugStatus;
	}
	
	public void setInWartung (boolean inWartung) {
		this.inWartung = inWartung;
	}
	public void setFahrzeugStatus (FahrzeugStatus fahrzeugStatus) {
		this.fahrzeugStatus = fahrzeugStatus;
	}
	public String getKennzeichenString () {
		
		return this.kennzeichen;
	}
	
}

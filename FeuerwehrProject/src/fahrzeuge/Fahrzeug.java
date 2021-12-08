package fahrzeuge;

import util.FahrzeugStatus;
import util.FahrzeugTyp;

public abstract class Fahrzeug {
	
	private int sitzplaetze;
	private FahrzeugTyp fahrzeugTyp;
	private FahrzeugStatus fahrzeugStatus;
	private String kennzeichen;
	private int id;
	private int einsatzID=0;
	
	public Fahrzeug(int id, int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen ) {
		
		this.id = id;
		this.sitzplaetze = sitzplaetze;
		this.fahrzeugTyp = fahrzeugTyp;
		this.fahrzeugStatus = fahrzeugstatus;
		this.kennzeichen = kennzeichen;
	}
	public Fahrzeug(int id, int sitzplaetze, FahrzeugTyp fahrzeugTyp, FahrzeugStatus fahrzeugstatus, String kennzeichen, int einsatzID ) {
		
		this.id = id;
		this.sitzplaetze = sitzplaetze;
		this.fahrzeugTyp = fahrzeugTyp;
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
	public FahrzeugStatus getFahrzeugStatus () {
		
		return this.fahrzeugStatus;
	}
	public void setFahrzeugStatus (FahrzeugStatus fahrzeugStatus) {
		this.fahrzeugStatus = fahrzeugStatus;
	}
	public String getKennzeichen () {
		
		return this.kennzeichen;
	}
	
	/**
	 * Eine methode um den Fahrzeug namen zu erhalten.
	 * @return
	 */
	public abstract String getFahrzeugName();
	
}
